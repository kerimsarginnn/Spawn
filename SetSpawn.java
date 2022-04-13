package net.kerim.Spawn.Commands;

import net.kerim.Spawn.Messages;
import net.kerim.Spawn.iskSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawn implements CommandExecutor {
    private final iskSpawn spawn;

    public SetSpawn(iskSpawn spawn) {
        this.spawn = spawn;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equalsIgnoreCase("SetSpawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("Spawn.SetSpawn")){
                    Location spawn = player.getLocation();
                    Bukkit.getPluginManager().callEvent(new net.kerim.Spawn.API.SetSpawn(player,spawn));
                } else {
                    Messages.noPerm(sender);
                }
            } else {
                Messages.noPlayer(sender);
            }
        }

        return true;
    }
}