package org.caldev;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpigotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Hello, Spigot!");
        getServer().getPluginManager().registerEvents(new EntityListener(), this);
        getServer().getPluginManager().registerEvents(new BowListener(this), this);
        getCommand("cow").setExecutor(new CowCommand());
        getCommand("ebow").setExecutor(new EnderBowCommand(this));
    }

    @Override
    public void onDisable() {
        System.out.println("Goodbye, Spigot!");
    }

    public static SpigotPlugin getInstance() {
        return getPlugin(SpigotPlugin.class);
    }
}
