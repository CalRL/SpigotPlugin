package com.caldev.commands;

import com.caldev.Hubbly;
import net.kyori.adventure.key.Namespaced;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.xml.stream.events.Namespace;
import java.util.Objects;
import java.util.logging.Logger;

public class LaunchpadCommand implements CommandExecutor {
    public final Logger logger;
    public final FileConfiguration config;
    public final Hubbly plugin;

    public LaunchpadCommand(Logger logger, FileConfiguration config, Hubbly plugin) {
        this.logger = logger;
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("messages.no_console"))));
            return true;
        }
        Player player = (Player) sender;
        ItemStack item = new ItemStack(Material.valueOf(config.getString("launchpad.type")));
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            String itemName = "Launchpad";
            meta.setDisplayName(itemName);
            item.setItemMeta(meta);
            player.getInventory().addItem(item);
        }


        return true;
    }
}
