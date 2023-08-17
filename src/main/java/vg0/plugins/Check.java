package vg0.plugins;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static org.bukkit.Bukkit.getServer;

public abstract class Check extends BukkitRunnable {
    //private PlayerDropItemEvent playerDropItemEvent;

    //private EntityDeathEvent entityDeathEvent;
    protected GodsAltar plugin;
    //private Integer countDown = 200; //20 ticks per second 20x10 = 200, preventing infinite loop when items merge
    /*public Check(GodsAltar plugin, PlayerDropItemEvent event) {
        this.playerDropItemEvent = event;
        this.plugin = plugin;
    }*/

    public Check(GodsAltar plugin) {
        this.plugin = plugin;
    }

    /*public Check(GodsAltar plugin, EntityDeathEvent event) {
        this.entityDeathEvent = event;
        this.plugin = plugin;
    }*/
    @Override
    public void run() {
        if (check()) {
            cancel();
        }
    }

    public boolean check() {
        if (doCancel()) { return true;}
        if (doSkip()) { return false; }
        if (isOnAltar(getLocation())) {
            alterLogic();
        }
        return true;
        /*if (entityDeathEvent != null) {
            if (isOnAltar(entityDeathEvent.getEntity().getLocation())) {
                new AltarLogic(plugin, entityDeathEvent);
            }
            cancel();
        }
        if (countDown-- <= 0) {cancel();}
        if(location.isOnGround()) {
            if (isOnAltar(location.getLocation())) {
                new AltarLogic(plugin, playerDropItemEvent);
            }
            cancel();
        }*/
    }

    abstract protected void alterLogic();

    abstract protected Location getLocation();
    
    protected boolean doCancel() { return false; }

    protected boolean doSkip() { return false; }

    private boolean isOnAltar(Location location) {
        return  getMatFromCords(location, new Vector(-1,-2,1), Material.GOLD_BLOCK) &&
                getMatFromCords(location,new Vector(0,-2,1),Material.GOLD_BLOCK) &&
                getMatFromCords(location,new Vector(1,-2,1),Material.GOLD_BLOCK) &&
                getMatFromCords(location,new Vector(1,-2,0),Material.GOLD_BLOCK) &&
                getMatFromCords(location,new Vector(1,-2,-1),Material.GOLD_BLOCK)&&
                getMatFromCords(location,new Vector(0,-2,-1),Material.GOLD_BLOCK) &&
                getMatFromCords(location,new Vector(-1,-2,-1),Material.GOLD_BLOCK) &&
                getMatFromCords(location,new Vector(-1,-2,0),Material.GOLD_BLOCK) &&
                getMatFromCords(location,new Vector(0,-2,0),Material.MOSSY_COBBLESTONE) &&
                getMatFromCords(location,new Vector(1,-1,1),Material.REDSTONE_TORCH) &&
                getMatFromCords(location,new Vector(1,-1,-1),Material.REDSTONE_TORCH) &&
                getMatFromCords(location,new Vector(-1,-1,-1),Material.REDSTONE_TORCH) &&
                getMatFromCords(location,new Vector(-1,-1,1),Material.REDSTONE_TORCH) &&
                getMatFromCords(location,new Vector(0,-1,0),Material.NETHERRACK);
    }

    private boolean getMatFromCords(Location location, Vector vector, Material material) {
        return(location.toVector().add(vector).toLocation(getServer().getWorlds().get(0)).getBlock().getType() == material);
    }
}

