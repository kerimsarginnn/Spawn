package net.kerim.Spawn.API;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TeleportSpawnOther extends Event {
    private static final HandlerList handlers = new HandlerList();
    private CommandSender sender;
    private Player target;

    public TeleportSpawnOther(CommandSender sender, Player target) {
        this.sender = sender;
        this.target =  target;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public CommandSender getSender() {
        return sender;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }
}
