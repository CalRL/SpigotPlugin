package com.caldev.commands;

import com.caldev.Hubbly;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
            sender.sendMessage(ChatColor.RED + config.getString("compass.messages.no_console"));
            return true;
        }
        Player player = (Player) sender;
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.valueOf(config.getString("compass.name_color")) + config.getString("compass.name"));
            compass.setItemMeta(meta);
        }
        player.getInventory().addItem(compass);
        player.sendMessage(ChatColor.GREEN + config.getString("compass.messages.compass_give"));


        return false;
    }
}
