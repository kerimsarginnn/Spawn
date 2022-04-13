package net.kerim.Spawn.Listeners;

import net.kerim.Spawn.API.SetSpawn;
import net.kerim.Spawn.API.TeleportSpawn;
import net.kerim.Spawn.API.TeleportSpawnOther;
import net.kerim.Spawn.Messages;
import net.kerim.Spawn.iskSpawn;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.HashMap;

public class APIListeners implements Listener {
    private HashMap<Player, Integer> status = new HashMap<>();
    private final iskSpawn spawn;

    public APIListeners(iskSpawn spawn) {
        this.spawn = spawn;
    }

    @EventHandler
    public void SetSpawn(SetSpawn event) {

        Location newSpawn = event.getLocation();
        Player player = event.getPlayer();

        String sx = String.valueOf(newSpawn.getBlockX());
        String sy = String.valueOf(newSpawn.getBlockY());
        String sz = String.valueOf(newSpawn.getBlockZ());

        int x = newSpawn.getBlockX();
        int y = newSpawn.getBlockY();
        int z = newSpawn.getBlockZ();
        String world = newSpawn.getWorld().getName();

        spawn.getConfig().set("Spawn.world",world);
        spawn.getConfig().set("Spawn.x",x);
        spawn.getConfig().set("Spawn.y",y);
        spawn.getConfig().set("Spawn.z",z);

        String loc = "Dünya, %x, %y, %z".replace("%x",sx).replace("%y",String.valueOf(y)).replace("%z",sz);

        Messages.SpawnSet(player,loc);

        spawn.saveDefaultConfig();

    }
    @EventHandler
    public void TeleportSpawn(TeleportSpawn event) {
        Player player = event.getPlayer();
        int x = spawn.getConfig().getInt("Spawn.x");
        int y = spawn.getConfig().getInt("Spawn.y");
        int z = spawn.getConfig().getInt("Spawn.z");
        World world = Bukkit.getWorld(spawn.getConfig().getString("Spawn.world"));
        Location spawnloc = new Location(world,x,y,z);
        status.put(player,0);
        new BukkitRunnable() {
            int i = 4;
            @Override
            public void run() {
                i--;
                Messages.Teleporting(player,i);
                if (status.get(player) == 1){
                    Messages.TpCancled(player,0);
                    status.remove(player);
                    cancel();
                } else if (status.get(player) == 2) {
                    Messages.TpCancled(player,1);
                    status.remove(player);
                    cancel();
                }
                if (i==0) {
                    Location location = player.getLocation().add(0,2,0);
                    new ParticleBuilder(ParticleEffect.REVERSE_PORTAL).setAmount(100).setLocation(location).display();
                    player.teleport(spawnloc);
                    status.remove(player);
                    cancel();
                }
            }
        }.runTaskTimer(spawn,0L,20L);
    }
    @EventHandler
    public void MoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (status.containsKey(player)) {
            status.replace(player,1);
        }
    }
    @EventHandler
    public void DamageEvent(EntityDamageEvent event) {
        if (event.getEntityType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();
            if (status.containsKey(player)) {
                status.replace(player,2);
            }
        }
    }
    @EventHandler
    public void TpOther(TeleportSpawnOther event) {
        Player target = event.getTarget();
        CommandSender sender = event.getSender();
        int x = spawn.getConfig().getInt("Spawn.x");
        int y = spawn.getConfig().getInt("Spawn.y");
        int z = spawn.getConfig().getInt("Spawn.z");
        World world = Bukkit.getWorld(spawn.getConfig().getString("Spawn.world"));
        Location spawnloc = new Location(world,x,y,z);
        target.teleport(spawnloc);
        Messages.TpOther(sender,target);
    }
}
