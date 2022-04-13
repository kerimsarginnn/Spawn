package net.kerim.Spawn.Commands;

import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.Spawn.API.TeleportSpawn;
import net.kerim.Spawn.API.TeleportSpawnOther;
import net.kerim.Spawn.Messages;
import net.kerim.Spawn.iskSpawn;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Spawn implements CommandExecutor {
    private final iskSpawn spawn;

    public Spawn(iskSpawn spawn) {
        this.spawn = spawn;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("Spawn")) {
            if (spawn.getConfig().get("Spawn") != null) {
                if (args.length > 0) {
                    if (sender.hasPermission("Spawn.TPOther")) {
                        Player target = Bukkit.getPlayer(args[0]);
                        int i = 0;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (Objects.equals(player,target)) {
                                i = 1;
                            }
                        }
                        if (i == 1) {
                            Bukkit.getPluginManager().callEvent(new TeleportSpawnOther(sender,target));
                        } else {
                            sender.sendMessage(CColor.translateGradient("%s isimli bir oyuncu bulunamadı".replace("%s",args[0]),CColor.RED,CColor.ORANGE));
                        }
                    } else {
                        Messages.noPerm(sender);
                    }
                } else {
                    if (sender.hasPermission("Spawn.Teleport")) {
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            Bukkit.getPluginManager().callEvent(new TeleportSpawn(player));
                        } else {
                            Messages.noPlayer(sender);
                        }
                    }
                }
            } else {
                Messages.noDefined(sender);
            }
        }

        return true;
    }
}
