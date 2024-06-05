package com.caldev.commands;

import com.caldev.Hubbly;
import com.caldev.items.CompassItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class CompassCommand implements CommandExecutor {
    private final Hubbly plugin;
    private final FileConfiguration config;

    public CompassCommand(Hubbly plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Code here
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("messages.no_console"))));
            return true;
        }
        Player player = (Player) sender;

        player.getInventory().addItem(new CompassItem(config).compassItem());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("compass.messages.compass_give"))));


        return true;
    }
}
