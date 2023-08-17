package vg0.plugins;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public abstract class AltarLogic {
    private static HashMap<String, Long> lastSacrifice = new HashMap<>();
    private int tickCooldown = 0;

    protected void runLogic(GodsAltar plugin, Player player, Location location) {
        if (!lastSacrifice.containsKey(player.getName()) ||lastSacrifice.get(player.getName()) < player.getWorld().getFullTime()) {
            lastSacrifice.put(player.getName(), player.getWorld().getFullTime() + tickCooldown);
            preSparkles();
            //event.getItemDrop().remove();
            new BukkitRunnable() {
                private int time = 20;
                @Override
                public void run() {
                    time--;
                    if (time <= 0) {
                        cancel();}
                   player.getWorld().spawnParticle(Particle.DRAGON_BREATH, location,50,0,1,0,0.1);
                }
            }.runTaskTimer(plugin,0,0);
            //player.getWorld().dropItem(location, new ItemStack(GodsAltar.lt.getRewardFromMaterial(event.getItemDrop().getItemStack().getType())));
            player.getWorld().dropItem(location, new ItemStack(getReward()));
        }
        else {
            player.sendMessage(ChatColor.RED + "You cant sacrifice yet! Try in "+Math.floor(((lastSacrifice.get(player.getName()))-player.getWorld().getFullTime())/20)+" Seconds");
        }
    }

    abstract protected Material getReward();

    protected void preSparkles() {
    }
}

