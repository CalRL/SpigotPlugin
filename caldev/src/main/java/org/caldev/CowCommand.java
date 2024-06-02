package org.caldev;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CowCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length > 1) {
            return false;
        }
        boolean isBaby = false;

        Player player = (Player) sender;
        Cow cow = (Cow) player.getWorld().spawnEntity(player.getLocation(), EntityType.COW);

        if (args.length == 1 && args[0].equalsIgnoreCase("baby")) {
            isBaby = true;
            cow.setBaby(); // Setting the cow as a baby if the "baby" argument is provided
        }

        cow.setMetadata("SpigotPlugin", new FixedMetadataValue(SpigotPlugin.getInstance(), true));
        cow.setCustomName(ChatColor.RED + "Milk me");
        cow.setCustomNameVisible(true);

        return true;  // Return true to indicate that the command was handled
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        System.out.println("args size: " + args.length);
        if (args.length == 1) {
            return Arrays.asList("baby");
        }
        return new ArrayList<>();
    }
}
