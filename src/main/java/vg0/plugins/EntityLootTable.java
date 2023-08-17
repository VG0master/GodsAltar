package vg0.plugins;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityLootTable {
    private HashMap<EntityType, ArrayList<OddsMaterial>> lootTable;

    public boolean has(EntityType e) {
        return lootTable.containsKey(e);
    }

    public ArrayList<OddsMaterial>  get(EntityType e) {
        return lootTable.get(e);
    }
}
