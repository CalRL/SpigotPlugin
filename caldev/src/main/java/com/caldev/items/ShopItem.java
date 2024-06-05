package com.caldev.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class ShopItem extends ItemStack {

    private final FileConfiguration config;
    public ShopItem(FileConfiguration config) {
        this.config = config;
    }

    public final ItemStack shopItem() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            String itemName = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("shop.item.name")));
            meta.setDisplayName(itemName);
            item.setItemMeta(meta);
        }
        return item;
    }
}
