package com.movementitems.commands;

import com.movementitems.interfaces.CustomItem;
import com.movementitems.interfaces.SubCommand;
import com.movementitems.items.Enderbow;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GiveCommand implements SubCommand {

    private final JavaPlugin plugin;
    private final FileConfiguration config;
    private final Map<String, CustomItem> items = new HashMap<>();

    public GiveCommand(JavaPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.config = config;
        registerItems();
    }

    private void registerItems() {
        items.put("enderbow", new Enderbow(plugin, config));
    }
    @Override
    public void execute(Player player, String[] args) {
        if (args.length < 2 && (player.hasPermission("mvitems.command.give") || player.isOp())) {
            player.sendMessage(ChatColor.YELLOW + "Usage: /mvitems give <item>");
            return;
        }
        if(player.hasPermission("mvitems.command.give") || player.isOp()) {
            String itemName = args[1].toLowerCase();
            CustomItem customItem = items.get(itemName);

            if (customItem != null) {
                ItemStack item = customItem.createItem();
                player.getInventory().addItem(item);
                player.sendMessage(ChatColor.YELLOW + "Given " + itemName);
            } else {
                player.sendMessage(ChatColor.RED + "Unknown item.");
            }
        } else {
            player.sendMessage(Objects.requireNonNull(config.getString("messages.no_permission_use/")));
        }

    }
}
