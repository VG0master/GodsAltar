package vg0.plugins;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class AltarLogicDrop extends AltarLogic {
    private Item item;

    public AltarLogicDrop(GodsAltar plugin, Player player, Item item) {
        this.item = item;
        runLogic(plugin, player, item.getLocation());
    }

    @Override
    protected void preSparkles() {
        item.remove();
    }

    @Override
    protected Material getReward() {
        return GodsAltar.lt.getRewardFromMaterial(item.getItemStack().getType());
    }
}
