package vg0.plugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Curse implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player)sender;
        World world = player.getWorld();
        Random r = new Random();
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
        PotionEffectType[] effectTypes = {PotionEffectType.BLINDNESS,PotionEffectType.DARKNESS,PotionEffectType.WEAKNESS,PotionEffectType.WITHER,PotionEffectType.POISON,PotionEffectType.GLOWING,PotionEffectType.HUNGER};
        PotionEffect potionEffect = new PotionEffect(effectTypes[r.nextInt(effectTypes.length)],PotionEffect.INFINITE_DURATION,r.nextInt(2),true,false);
        reciver.addPotionEffect(potionEffect);
        reciver.sendMessage(ChatColor.DARK_RED + "You have been CURSED by the god! You now have permanent "+potionEffect.getType().getName().toLowerCase()+" "+(potionEffect.getAmplifier()+1)+" until you die.");
        return true;
    }
}
