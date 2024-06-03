package com.caldev;

import com.caldev.commands.CompassCommand;
import com.caldev.listeners.CompassListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class Hubbly extends JavaPlugin {

    private Logger logger = getLogger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        logger.info("Hubbly has been enabled!");
        getServer().getPluginManager().registerEvents(new CompassListener(logger, getConfig(), this), this);
        getCommand("compass").setExecutor(new CompassCommand(this, getConfig()));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Hubbly has been disabled!");
    }

    public static Hubbly getInstance() {
        return getPlugin(Hubbly.class);
    }
    @Override
    public @NotNull FileConfiguration getConfig() {
        return super.getConfig();
    }
}
