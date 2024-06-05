package com.caldev.commands;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.bukkit.inventory.ItemStack;


import java.util.Objects;
import java.util.logging.Logger;

public class PlayerVisibilityCommand implements CommandExecutor {

    private final Logger logger;
    private final FileConfiguration config;

    public PlayerVisibilityCommand(Logger logger, FileConfiguration config) {
        this.logger = logger;
        this.config = config;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("messages.no_console"))));
            return true;
        }
        ItemStack item = new ItemStack(Material.LIME_DYE);
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            String itemName = ChatColor.translateAlternateColorCodes('&', "&rPlayers: &aVisible&r");
            meta.setDisplayName(itemName);
            item.setItemMeta(meta);
        }
        Player player = (Player) sender;
        player.getInventory().addItem(item);


        return true;
    }
}
