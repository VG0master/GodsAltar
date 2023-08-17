package vg0.plugins;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.Random;

public class LootTables {
    private MaterialLootTable materialLoot;
    private EntityLootTable entityLoot;
    public Material getRewardFromMaterial(Material droppedItem) {
        if (!materialLoot.has(droppedItem)) {
            //If this happens just return the original item, because something wrong happened
            return droppedItem;
        }
        int totalOdds = materialLoot.get(droppedItem).stream().mapToInt(OddsMaterial::getOdds).sum();
        Random random = new Random();
        int randOdds = random.nextInt(totalOdds);
        for (OddsMaterial option : materialLoot.get(droppedItem)) {
            randOdds -= option.getOdds();
            if (randOdds < 0) {
                return option.getMaterial();
            }
        }
        //If this happens just return the original item, because something wrong happened
        return droppedItem;
    }

    public Material getRewardFromDeath(EntityType type) {
        if (entityLoot == null || !entityLoot.has(type)) {
            //If this happens just return the original item, because something wrong happened
            return Material.DIRT;
        }
        int totalOdds = entityLoot.get(type).stream().mapToInt(OddsMaterial::getOdds).sum();
        Random random = new Random();
        int randOdds = random.nextInt(totalOdds);
        for (OddsMaterial option : entityLoot.get(type)) {
            randOdds -= option.getOdds();
            if (randOdds < 0) {
                return option.getMaterial();
            }
        }
        //If this happens just return the original item, because something wrong happened
        return Material.DIRT;
    }
}

