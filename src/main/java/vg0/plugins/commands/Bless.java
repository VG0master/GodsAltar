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

public class Bless implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player)sender;
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
        PotionEffectType[] effectTypes = {PotionEffectType.CONDUIT_POWER,PotionEffectType.DAMAGE_RESISTANCE,PotionEffectType.INCREASE_DAMAGE,PotionEffectType.FAST_DIGGING,PotionEffectType.FIRE_RESISTANCE,PotionEffectType.WATER_BREATHING,PotionEffectType.REGENERATION,PotionEffectType.NIGHT_VISION};
        PotionEffect potionEffect = new PotionEffect(effectTypes[r.nextInt(effectTypes.length)],PotionEffect.INFINITE_DURATION,r.nextInt(2),true,false);
        reciver.addPotionEffect(potionEffect);
        reciver.sendMessage(ChatColor.GREEN + "You have been blessed by the god! You now have permanent "+potionEffect.getType().getName().toLowerCase()+" "+(potionEffect.getAmplifier()+1)+" until you die.");
        return true;
    }
}
