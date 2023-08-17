package vg0.plugins;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropCheck extends Check {
    private PlayerDropItemEvent event;
    private Integer countDown = 200; //20 ticks per second 20x10 = 200, preventing infinite loop when items merge

    public DropCheck(GodsAltar plugin, PlayerDropItemEvent event) {
        super(plugin);
        this.event = event;
    }

    @Override
    protected Location getLocation() {
        return event.getItemDrop().getLocation();
    }

    @Override
    protected boolean doCancel() {
        return countDown-- <= 0;
    }

    @Override
    protected boolean doSkip() {
        return !event.getItemDrop().isOnGround();
    }

    @Override
    protected void alterLogic() {
        new AltarLogicDrop(plugin, event.getPlayer(), event.getItemDrop());
    }
}
