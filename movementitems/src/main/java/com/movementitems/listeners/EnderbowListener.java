package com.movementitems.listeners;

import com.movementitems.items.Enderbow;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class EnderbowListener implements Listener {

    private final NamespacedKey enderBowKey;
    private final JavaPlugin plugin;
    private final FileConfiguration config;

    public EnderbowListener(JavaPlugin plugin, FileConfiguration config) {
        this.plugin = plugin;
        this.enderBowKey = new NamespacedKey(plugin, "ender_bow");
        this.config = config;
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent event) {
        if(!config.getBoolean("items.ender_bow.enabled")) {
            event.setCancelled(true);
            return;
        }
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack bow = event.getBow();
            if (bow != null && bow.getType() == Material.BOW && bow.hasItemMeta()) {
                ItemMeta meta = bow.getItemMeta();
                if (meta.getPersistentDataContainer().has(enderBowKey, PersistentDataType.BYTE)) {
                    if (player.hasPermission("mvitems.enderbow.use") || player.isOp()) {
                        // The bow has the custom attribute, so we can let the arrow shoot normally
                        Arrow arrow = (Arrow) event.getProjectile();
                        arrow.getPersistentDataContainer().set(enderBowKey, PersistentDataType.BYTE, (byte) 1);
                        // Debugging: Print to console
                        //Bukkit.getLogger().info("Enderpearl Bow used by: " + player.getName());
                    } else {
                        player.sendMessage(ChatColor.RED + config.getString("items.ender_bow.messages.no_permission_bow"));
                    }
                }
            }

        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            if (arrow.getShooter() instanceof Player) {
                Player player = (Player) arrow.getShooter();
                if (arrow.getPersistentDataContainer().has(enderBowKey, PersistentDataType.BYTE)) {
                    // Retrieve and remove the stored yaw and pitch
                    Location targetLocation = arrow.getLocation();
                    targetLocation.setYaw(player.getLocation().getYaw());
                    targetLocation.setPitch(player.getLocation().getPitch());
                    // Teleport the player to where the arrow lands with the stored yaw and pitch
                    player.teleport(targetLocation);
                    arrow.remove();
                    if (config.getBoolean("ender_bow.play_sound", true)) {
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    }
                    // Debugging: Print to console
                    //Bukkit.getLogger().info("Teleporting player: " + player.getName() + " to location: " + arrow.getLocation());
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Arrow) {
            Arrow arrow = (Arrow) damager;
            if (arrow.getPersistentDataContainer().has(enderBowKey, PersistentDataType.BYTE)) {
                // Cancel the damage if the arrow is from the Enderpearl Bow
                event.setCancelled(true);
            }
        }
    }
}
