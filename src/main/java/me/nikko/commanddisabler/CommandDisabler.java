package me.nikko.commanddisabler;

import me.nikko.commanddisabler.listeners.CommandListener;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandDisabler extends JavaPlugin {
    private final Permission bypass = new Permission("commanddisabler.bypass", PermissionDefault.OP);

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new CommandListener(this), this);

        Bukkit.getPluginManager().removePermission(this.getBypass());
        Bukkit.getPluginManager().addPermission(this.getBypass());
    }

    public Permission getBypass() {
        return bypass;
    }
}
