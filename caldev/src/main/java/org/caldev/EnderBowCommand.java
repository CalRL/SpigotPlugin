package org.caldev;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EnderBowCommand implements CommandExecutor {

    private final NamespacedKey enderpearlBowKey;

    public EnderBowCommand(SpigotPlugin plugin) {
        this.enderpearlBowKey = new NamespacedKey(plugin, "enderpearl_bow");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        ItemStack enderBow = new ItemStack(Material.BOW);
        ItemMeta meta = enderBow.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Enderpearl Bow");// Adding Infinity enchantment to the bow
            meta.getPersistentDataContainer().set(enderpearlBowKey, PersistentDataType.BYTE, (byte) 1);
            enderBow.setItemMeta(meta);
        }

        player.getInventory().addItem(enderBow);
        player.sendMessage(ChatColor.GREEN + "You have received an Enderpearl Bow!");
        return true;
    }
}
