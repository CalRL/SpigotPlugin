package com.skylegendsmc;

import com.skylegendsmc.listeners.ThunderlordListener;
import org.bukkit.plugin.java.JavaPlugin;
import net.minecraft.*;

import java.util.logging.Logger;

public final class SkyLegendsEnchants extends JavaPlugin {
    private static Logger logger;
    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ThunderlordListener(this), this);

        // Register the command
        if (this.getCommand("customenchant") != null) {
            this.getCommand("customenchant").setExecutor(new CustomEnchantCommand(this));
        } else {
            getLogger().severe("Failed to register customenchant command. Is it defined in plugin.yml?");
        }

        getLogger().info("SkyLegendsEnchants has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SkyLegendsEnchants has been disabled!");
    }
    public static Logger getPluginLogger() {
        return logger;
    }
}
