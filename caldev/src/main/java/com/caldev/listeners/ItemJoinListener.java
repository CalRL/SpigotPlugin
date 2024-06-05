package com.caldev.listeners;

import java.util.Objects;
import java.util.logging.Logger;

import com.caldev.items.CompassItem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.caldev.commands.CompassCommand;
import org.bukkit.inventory.ItemStack;

public class ItemJoinListener implements Listener {
    private final Logger logger;
    private final FileConfiguration config;
    private final ItemStack compassItem;



    public ItemJoinListener(Logger logger, FileConfiguration config) {
        this.logger = logger;
        this.config = config;
        this.compassItem = new CompassItem(config);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Hello!");
        if(Objects.equals(config.getBoolean("item_on_join.enabled"), true)) {
            logger.info("1");
            if(Objects.equals(config.getBoolean("item_on_join.features.compass.enabled"), true)) {
                ItemStack compass = new CompassItem(config).compassItem();
                logger.info("2");
                event.getPlayer().getInventory().setItem(config.getInt("item_on_join.features.compass.slot"), compass);
                logger.info("3");
                logger.info(new CompassItem(config).toString());
            }


        }
    }
}
