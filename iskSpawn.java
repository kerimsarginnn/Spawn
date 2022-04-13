package net.kerim.Spawn;

import dev.perryplaysmc.dynamicjson.data.CColor;
import net.kerim.Spawn.Commands.Spawn;
import net.kerim.Spawn.Listeners.APIListeners;
import net.kerim.Spawn.Listeners.OtherListener;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.logging.Logger;

public final class iskSpawn extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        System.out.println();

        final PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new APIListeners(this),this);
        manager.registerEvents(new OtherListener(this),this);

        getCommand("SetSpawn").setExecutor(new net.kerim.Spawn.Commands.SetSpawn(this));
        getCommand("Spawn").setExecutor(new net.kerim.Spawn.Commands.Spawn(this));

        Bukkit.broadcastMessage(CColor.translateGradient("Eklenti aktif ediliyor!",CColor.RED,CColor.ORANGE));
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage(CColor.translateGradient("Eklenti deaktif ediliyor!",CColor.RED,CColor.ORANGE));
    }

    @Override
    public void saveDefaultConfig() {
        super.saveConfig();
        super.saveDefaultConfig();
    }
}
