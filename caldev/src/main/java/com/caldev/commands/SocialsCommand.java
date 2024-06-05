package com.caldev.commands;

import com.caldev.items.SocialsItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class SocialsCommand implements CommandExecutor {

    private final FileConfiguration config;
    private final Logger logger;

    public SocialsCommand(Logger logger, FileConfiguration config) {
        this.config = config;
        this.logger = logger;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        Player player = (Player) sender;
        try {
            SocialsItem socialsItem = new SocialsItem(config);
            player.getInventory().addItem(socialsItem.socialsItem());
            player.sendMessage("You have received a Socials Head!");
        } catch (Exception e) {
            logger.severe("An error occurred while executing the command: " + e.getMessage());
            player.sendMessage("An error occurred while trying to give you the item. Please check the server logs.");
        }


        return true;
    }
}
