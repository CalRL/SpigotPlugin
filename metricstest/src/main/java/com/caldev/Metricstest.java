package com.caldev;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class Metricstest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("METRICS TEST WORKING");
        System.out.println("METRICS TEST WORKING");
        System.out.println("METRICS TEST WORKING");
        int pluginId = 22222;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Metricstest getInstance() {
        return getPlugin(Metricstest.class);
    }

}
