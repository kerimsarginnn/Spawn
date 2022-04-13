package net.kerim.Spawn.Listeners;

import net.kerim.Spawn.iskSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OtherListener implements Listener {
    private final iskSpawn spawn;

    public OtherListener(iskSpawn spawn) {
        this.spawn = spawn;
    }

    @EventHandler
    public void RespawnListener(PlayerRespawnEvent event) {
        if ((!event.isBedSpawn()) && (!event.isAnchorSpawn()) && (spawn.getConfig().get("Spawn") != null)&&
                (event.getPlayer().hasPermission("Spawn.Respawn"))) {
            int x = spawn.getConfig().getInt("Spawn.x");
            int y = spawn.getConfig().getInt("Spawn.y");
            int z = spawn.getConfig().getInt("Spawn.z");
            World world = Bukkit.getWorld(spawn.getConfig().getString("Spawn.world"));
            Location spawnloc = new Location(world,x,y,z);
            event.setRespawnLocation(spawnloc);
        }
    }
}
