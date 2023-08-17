package vg0.plugins;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;

public class AltarLogicDeath extends AltarLogic {
    private LivingEntity entity;
    private EntityDeathEvent event;

    public AltarLogicDeath(GodsAltar plugin, EntityDeathEvent event) {
        this.event = event;
        this.entity = event.getEntity();
        runLogic(plugin, entity.getKiller(), entity.getLocation());
    }

    @Override
    protected Material getReward() {
        return GodsAltar.lt.getRewardFromDeath(entity.getType());
    }

    @Override
    protected void preSparkles() {
        event.getDrops().clear();
    }
}
