package com.skylegendsmc.enchants;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import com.skylegendsmc.SkyLegendsEnchants;

import java.util.logging.Logger;

public class ThunderlordEnchantment {

    public static final NamespacedKey THUNDERLORD_KEY = new NamespacedKey("skylegendsmc", "thunderlord");
    private static final Logger logger = SkyLegendsEnchants.getPluginLogger();

    public static void addEnchantment(ItemStack item, int level) {
        var meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(THUNDERLORD_KEY, PersistentDataType.INTEGER, level);
            item.setItemMeta(meta);
            logger.info("Added Thunderlord enchantment level " + level + " to item.");
        }
    }

    public static int getEnchantmentLevel(ItemStack item) {
        var meta = item.getItemMeta();
        if (meta != null) {
            int level = meta.getPersistentDataContainer().getOrDefault(THUNDERLORD_KEY, PersistentDataType.INTEGER, 0);
            //logger.info("Retrieved Thunderlord enchantment level " + level + " from item.");
            return level;
        }
        logger.info("No item meta found, returning level 0.");
        return 0;
    }

    public static boolean hasEnchantment(ItemStack item) {
        return getEnchantmentLevel(item) > 0;
    }
}
