package com.kwiius.placemc;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PluginMain extends JavaPlugin {
    private Queue<BlockPlacement> placementsQueue = new ConcurrentLinkedQueue<BlockPlacement>();

    @Override
    public void onEnable() {
        World w = getServer().getWorld("world");

        new PlaceBlocksTask(this, placementsQueue, w).runTaskTimer(this, 20, 20);

        new GetBitmapTask(this, placementsQueue).runTaskTimerAsynchronously(this, 20, 20*10);

        getCommand("goto").setExecutor(new GotoCommand());
        getCommand("generate").setExecutor(new GenerateCommand(w));
    }

    @Override
    public void onDisable() {

    }
}
