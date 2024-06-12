package com.movementitems;

import com.movementitems.commands.MVItemsCommand;
import com.movementitems.items.Enderbow;
import com.movementitems.listeners.EnderbowListener;
import com.movementitems.metrics.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class MovementItems extends JavaPlugin {

    private final Logger logger = getLogger();
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        int pluginId = 22222;
        Metrics metrics = new Metrics(this, pluginId);

        getServer().getPluginManager().registerEvents(new EnderbowListener(getInstance(), getConfig()), this);

        getCommand("mvitems").setExecutor(new MVItemsCommand(logger, getConfig(), getInstance()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Shutting down MovementItems");
    }

    public static MovementItems getInstance() {
        return getPlugin(MovementItems.class);
    }

    public @NotNull FileConfiguration getConfig() {
        return super.getConfig();
    }
}
