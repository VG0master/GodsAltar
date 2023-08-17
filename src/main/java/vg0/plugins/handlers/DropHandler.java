package vg0.plugins.handlers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import vg0.plugins.Check;
import vg0.plugins.DropCheck;
import vg0.plugins.GodsAltar;


public class DropHandler implements Listener {
    private GodsAltar plugin;
    public DropHandler(GodsAltar plugin) {
        this.plugin = plugin;

    }
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        new DropCheck(plugin, event).runTaskTimer(plugin,0,0);

    }

}
