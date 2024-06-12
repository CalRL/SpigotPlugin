package com.movementitems.items;

import com.movementitems.interfaces.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Enderbow implements CustomItem {
    private final NamespacedKey enderBowKey;
    private final JavaPlugin plugin;
    private final FileConfiguration config;
    public Enderbow(JavaPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.enderBowKey = new NamespacedKey(plugin, "ender_bow");
        this.config = config;
    }
    @Override
    public ItemStack createItem() {


        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        List<String> loreList = new ArrayList<String>();
        loreList.add(ChatColor.translateAlternateColorCodes('&', "&7Teleports you where the arrow lands."));
        if (meta != null) {

            meta.setDisplayName(ChatColor.valueOf(config.getString("items.ender_bow.name_color")) + config.getString("items.ender_bow.name"));
            meta.getPersistentDataContainer().set(enderBowKey, PersistentDataType.BYTE, (byte) 1);
            meta.setLore(loreList);
            item.setItemMeta(meta);
        }
        return item;
    }
}
