package vg0.plugins;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;

public class MaterialLootTable {
    private HashMap<Material, ArrayList<OddsMaterial>> lootTable;

    public boolean has(Material m) {
        return lootTable.containsKey(m);
    }

    public ArrayList<OddsMaterial>  get(Material m ) {
        return lootTable.get(m);
    }

}
