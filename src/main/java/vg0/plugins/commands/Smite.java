package vg0.plugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Smite implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender,Command command,String s,String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only Players are allowed to run this command");
            return true;
        }
        Player player = (Player)sender;
        World world = player.getWorld();
        if (args.length == 0) {
            Block target = player.getTargetBlock(null,50);
            Location location = target.getLocation();
            world.strikeLightning(location);
            world.createExplosion(location,2.0F);
        } else if (args.length == 1) {
            if (player.getServer().getPlayer(args[0]) != null) {
                Player target = player.getServer().getPlayer(args[0]);
                Location location = target.getLocation();
                world.strikeLightning(location);
                player.sendMessage(ChatColor.GRAY + "Smiting: " + target.getDisplayName());
            } else {
                player.sendMessage(ChatColor.RED + "Error: That Player Is Offline.");
            }
        } else if (args.length > 1) {
            player.sendMessage(ChatColor.RED + "Too Many Arguments!");
        }
        return true;
    }
}
