package vg0.plugins;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import vg0.plugins.commands.Bless;
import vg0.plugins.commands.Cleanse;
import vg0.plugins.commands.Curse;
import vg0.plugins.commands.Smite;
import vg0.plugins.handlers.DeathHandler;
import vg0.plugins.handlers.DropHandler;

import java.io.*;
public final class GodsAltar extends JavaPlugin {
    public static LootTables lt;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting GodsAltar");
        getCommand("smite").setExecutor(new Smite());
        getCommand("bless").setExecutor(new Bless());
        getCommand("curse").setExecutor(new Curse());
        getCommand("cleanse").setExecutor(new Cleanse());
        Gson gson = new Gson();
        try {
            lt = gson.fromJson(new FileReader("itemData.json"),LootTables.class);
        } catch (FileNotFoundException e) {
            Bukkit.getLogger().info("Counld't Parse json");
            throw new RuntimeException(e);
        }
        Bukkit.getPluginManager().registerEvents(new DropHandler(this),this);
        Bukkit.getPluginManager().registerEvents(new DeathHandler(this),this);

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutting down GodsAltar");
    }
}
