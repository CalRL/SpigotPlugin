package com.skylegendsmc.listeners;

import com.skylegendsmc.SkyLegendsEnchants;
import com.skylegendsmc.enchants.ThunderlordEnchantment;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

public class ThunderlordListener implements Listener {

    private final SkyLegendsEnchants plugin;
    private final Logger logger;
    private final Map<Player, Queue<Double>> playerHits = new HashMap<>();
    private final Map<Player, Long> lastHitTime = new HashMap<>();
    private final Map<Player, Long> cooldowns = new HashMap<>();

    public ThunderlordListener(SkyLegendsEnchants plugin) {
        this.plugin = plugin;
        this.logger = SkyLegendsEnchants.getPluginLogger();
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //logger.info("Entity damage by entity event triggered");
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        //logger.info("Damager is a player");
        Player player = (Player) event.getDamager();
        ItemStack weapon = player.getInventory().getItemInMainHand();

        int level = ThunderlordEnchantment.getEnchantmentLevel(weapon);
        //logger.info("Player " + player.getName() + " has Thunderlord level " + level);
        if (level == 0) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        long lastUsedTime = cooldowns.getOrDefault(player, 0L);

        if (currentTime - lastUsedTime < 10000) { // 10 second cooldown
            long timeLeft = 10000 - (currentTime - lastUsedTime);
            //player.sendMessage("Thunderlord is on cooldown. Please wait " + (timeLeft / 1000) + " seconds.");
            return;
        }

        double damage = event.getDamage();

        long lastTime = lastHitTime.getOrDefault(player, 0L);

        if (currentTime - lastTime > 2000) {  // If time since last hit is more than 2 seconds, reset the queue
            playerHits.remove(player);
            //logger.info("Resetting hit queue for player " + player.getName() + " due to timeout");
        }

        lastHitTime.put(player, currentTime);

        Queue<Double> hits = playerHits.computeIfAbsent(player, k -> new LinkedList<>());

        //logger.info("Player " + player.getName() + " hit. Current hit queue size: " + hits.size());

        if (hits.size() == 3) {
            hits.poll();
        }
        hits.add(damage);

        //logger.info("Player " + player.getName() + " hit. New hit queue size: " + hits.size());

        if (hits.size() == 3) {
            double additionalDamage = damage * ((double) level /10); // Scale with level
            double totalDamage = damage + additionalDamage;
            event.setDamage(damage);

            Entity entity = event.getEntity();
            //entity.getWorld().strikeLightning(entity.getLocation());
            player.playSound(player.getLocation(), "entity.lightning_bolt.thunder", 1.0f, 1.0f);  // Play thunder sound

            //player.sendMessage("Thunderlord enchantment dealt additional damage: " + additionalDamage);
            logger.info(player.getName() + " activated Thunderlord on " + event.getEntity().getName() + " dealing additional damage: " + additionalDamage);
            hits.clear();

            // Set cooldown
            cooldowns.put(player, currentTime);
        }
    }
}
