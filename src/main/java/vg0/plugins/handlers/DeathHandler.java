package vg0.plugins.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import vg0.plugins.DeathCheck;
import vg0.plugins.GodsAltar;

public class DeathHandler implements Listener {
    private GodsAltar plugin;
    public DeathHandler(GodsAltar plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        //event.getDrops().clear();
        new DeathCheck(plugin,event).check();//runTaskTimer(plugin,0,0);
    }
}
