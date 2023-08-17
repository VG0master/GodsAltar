package vg0.plugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Cleanse implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player)sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Missing Player");
            return true;
        }
        if (args.length > 1) {
            player.sendMessage(ChatColor.RED + "Too many arguments");
            return true;
        }
        if (player.getServer().getPlayer(args[0]) == null) {
            player.sendMessage(ChatColor.RED + "Player is offline or doesnt exist");
            return true;
        }
        Player reciver = player.getServer().getPlayer(args[0]);
        for (PotionEffect effect : reciver.getActivePotionEffects())
            reciver.removePotionEffect(effect.getType());
        reciver.sendMessage(ChatColor.GREEN+"You have been cleansed by god! All potion effects have been removed.");
        return true;
    }
}
