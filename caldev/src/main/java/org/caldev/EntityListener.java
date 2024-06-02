package org.caldev;

import org.bukkit.Material;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityListener implements Listener {
    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if(entity instanceof Cow && entity.hasMetadata("SpigotPlugin") && player.getInventory().getItemInMainHand().getType() == Material.BUCKET) {
            event.setCancelled(true);
            Cow cow = (Cow) entity;
            cow.getWorld().createExplosion(cow.getLocation(), 4.0f);
        }
    }
}
