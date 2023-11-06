package me.nikko.commanddisabler.listeners;

import me.nikko.commanddisabler.CommandDisabler;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandListener implements Listener {
    private final CommandDisabler plugin;

    public CommandListener(CommandDisabler plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        FileConfiguration config = this.getPlugin().getConfig();
        if (!config.getBoolean("command-blocking-enabled") || event.getPlayer().hasPermission(this.getPlugin().getBypass()))
            return;

        List<String> blockedCommands = config.getStringList("blocked-commands");
        if (!blockedCommands.contains(event.getMessage()) && !blockedCommands.contains(event.getMessage().substring(1)))
            return;

        String blockedMessage = config.getString("blocked-message");
        if (blockedMessage == null)
            return;

        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', blockedMessage));
        event.setCancelled(true);
    }

    private CommandDisabler getPlugin() {
        return plugin;
    }
}
