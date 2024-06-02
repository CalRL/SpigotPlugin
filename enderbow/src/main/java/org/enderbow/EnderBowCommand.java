package org.enderbow;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class EnderBowCommand implements CommandExecutor {

    private final Enderbow plugin;
    private final NamespacedKey enderpearlBowKey;

    public EnderBowCommand(Enderbow plugin) {
        this.plugin = plugin;
        this.enderpearlBowKey = new NamespacedKey(plugin, "enderpearl_bow");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("enderpearl_bow.messages.no_console"));
            return true;
        }

        Player player = (Player) sender;
        ItemStack enderBow = new ItemStack(Material.BOW);
        ItemMeta meta = enderBow.getItemMeta();
        if (meta != null) {

            meta.setDisplayName(ChatColor.valueOf(plugin.getConfig().getString("enderpearl_bow.name_color")) + plugin.getConfig().getString("enderpearl_bow.name"));// Adding Infinity enchantment to the bow
            meta.getPersistentDataContainer().set(enderpearlBowKey, PersistentDataType.BYTE, (byte) 1);
            enderBow.setItemMeta(meta);
        }

        player.getInventory().addItem(enderBow);
        player.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("enderpearl_bow.messages.bow_give"));
        return true;
    }
}
