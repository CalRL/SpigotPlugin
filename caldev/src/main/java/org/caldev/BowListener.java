package org.caldev;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class BowListener implements Listener {

    private final NamespacedKey enderpearlBowKey;
    private final Map<Arrow, Float[]> arrowDirections = new HashMap<>();

    public BowListener(SpigotPlugin plugin) {
        this.enderpearlBowKey = new NamespacedKey(plugin, "enderpearl_bow");
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack bow = event.getBow();
            if (bow != null && bow.getType() == Material.BOW && bow.hasItemMeta()) {
                ItemMeta meta = bow.getItemMeta();
                if (meta.getPersistentDataContainer().has(enderpearlBowKey, PersistentDataType.BYTE)) {
                    // The bow has the custom attribute, so we can let the arrow shoot normally
                    Arrow arrow = (Arrow) event.getProjectile();
                    arrow.setCustomName("EnderpearlArrow");

                    // Store the player's yaw and pitch
                    arrowDirections.put(arrow, new Float[]{player.getLocation().getYaw(), player.getLocation().getPitch()});

                    // Debugging: Print to console
                    Bukkit.getLogger().info("Enderpearl Bow used by: " + player.getName());
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
                if ("EnderpearlArrow".equals(arrow.getCustomName())) {
                    // Retrieve and remove the stored yaw and pitch
                    Float[] direction = arrowDirections.remove(arrow);
                    if (direction != null) {
                        Location targetLocation = arrow.getLocation();
                        targetLocation.setYaw(direction[0]);
                        targetLocation.setPitch(direction[1]);

                        // Teleport the player to where the arrow lands with the stored yaw and pitch
                        player.teleport(targetLocation);
                        arrow.remove();
                    }

                    // Debugging: Print to console
                    Bukkit.getLogger().info("Teleporting player: " + player.getName() + " to location: " + arrow.getLocation());
                }
            }
        }
    }
}
