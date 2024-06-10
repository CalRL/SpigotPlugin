package org.enderbow;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.enderbow.metrics.Metrics;
import org.jetbrains.annotations.NotNull;


public final class Enderbow extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new BowListener(this), this);
        getCommand("enderbow").setExecutor(new EnderBowCommand(this));
        System.out.print("Enderbow enabled");
        int pluginId = 22221;
        Metrics metrics = new Metrics(this, pluginId);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Enderbow disabled");
    }

    public static Enderbow getInstance() {
        return getPlugin(Enderbow.class);
    }
    @Override
    public  FileConfiguration getConfig() {
        return super.getConfig();
    }
}
