package com.caldev.commands;

import com.caldev.items.ShopItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Logger;

public class ShopCommand implements CommandExecutor {

    private final Logger logger;
    private final FileConfiguration config;

    public ShopCommand(Logger logger, FileConfiguration config) {
        this.logger = logger;
        this.config = config;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Objects.requireNonNull(config.getString("messages.no_console")));
            return true;
        }
        if(Objects.equals(config.getString("shop.enabled"), "false")) {
            logger.info("Shop is disabled.");
            return true;
        }
        Player player = (Player) sender;

        player.getInventory().addItem(new ShopItem(config).shopItem());
        return true;
    }

    //    @EventHandler
//    public void onPlayerClick(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//        if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND) {
//            player.performCommand(Objects.requireNonNull(config.getString("shop.command")));
//        }
//    }
}
