package vg0.plugins;

import org.bukkit.Location;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathCheck extends Check {
    private EntityDeathEvent event;

    public DeathCheck(GodsAltar plugin, EntityDeathEvent event) {
        super(plugin);
        this.event = event;
    }

    @Override
    protected Location getLocation() {
        return event.getEntity().getLocation();
    }

    @Override
    protected void alterLogic() {
        new AltarLogicDeath(plugin, event);
    }
}
