package com.skylegendsmc;

import com.skylegendsmc.SkyLegendsEnchants;
import com.skylegendsmc.enchants.ThunderlordEnchantment;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomEnchantCommand implements CommandExecutor {

    private final SkyLegendsEnchants plugin;
    private final Logger logger;

    public CustomEnchantCommand(SkyLegendsEnchants plugin) {
        this.plugin = plugin;
        this.logger = SkyLegendsEnchants.getPluginLogger();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            logger.warning("A non-player tried to use the customenchant command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Usage: /customenchant <enchantment> <level>");
            logger.warning("Incorrect usage of customenchant command by " + player.getName());
            return true;
        }

        String enchantmentName = args[0].toLowerCase();
        int level;
        try {
            level = Integer.parseInt(args[1]);
            if (level < 1 || level > 3) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "The level must be a valid number between 1 and 3.");
            logger.warning(player.getName() + " provided an invalid number for the enchantment level.");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "You must be holding an item to enchant it!");
            logger.warning(player.getName() + " tried to enchant an invalid item.");
            return true;
        }

        if (ThunderlordEnchantment.hasEnchantment(item)) {
            player.sendMessage(ChatColor.RED + "This item already has the Thunderlord enchantment!");
            logger.warning(player.getName() + " tried to enchant an item that already has Thunderlord.");
            return true;
        }

        if ("thunderlord".equals(enchantmentName)) {
            ThunderlordEnchantment.addEnchantment(item, level);
            setItemLore(item, "Thunderlord", level);
            player.sendMessage(ChatColor.GREEN + "Your item has been enchanted with Thunderlord level " + level + "!");
            logger.info(player.getName() + " enchanted an item with Thunderlord level " + level);
        } else {
            player.sendMessage(ChatColor.RED + "Unknown enchantment: " + enchantmentName);
            logger.warning(player.getName() + " tried to use an unknown enchantment: " + enchantmentName);
        }

        return true;
    }

    private void setItemLore(ItemStack item, String enchantmentName, int level) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            String enchantmentLore = ChatColor.translateAlternateColorCodes('&', "&r&7" + enchantmentName + " " + level);
            lore.add(enchantmentLore);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }
}
