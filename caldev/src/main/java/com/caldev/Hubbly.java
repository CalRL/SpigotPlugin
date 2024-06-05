package com.caldev;

import com.caldev.commands.*;
import com.caldev.listeners.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class Hubbly extends JavaPlugin {

    private final Logger logger = getLogger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        logger.info("Hubbly has been enabled!");
        getServer().getPluginManager().registerEvents(new CompassListener(logger, getConfig(), this), this);
        getServer().getPluginManager().registerEvents(new PlayerVisibilityListener(getInstance(), getConfig()), this);
        getServer().getPluginManager().registerEvents(new LaunchpadListener(logger, getInstance(), getConfig()), this);
        getServer().getPluginManager().registerEvents(new ShopListener(logger, getConfig()), this);
        getServer().getPluginManager().registerEvents(new ItemJoinListener(logger, getConfig()), this);

        getCommand("compass").setExecutor(new CompassCommand(this, getConfig()));
        getCommand("playervisibility").setExecutor(new PlayerVisibilityCommand(logger, getConfig()));
        getCommand("launchpad").setExecutor(new LaunchpadCommand(logger, getConfig(), this));
        getCommand("shopitem").setExecutor(new ShopCommand(logger, getConfig()));
        getCommand("socialscommand").setExecutor(new SocialsCommand(logger, getConfig()));
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
