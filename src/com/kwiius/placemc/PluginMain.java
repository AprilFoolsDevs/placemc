package com.kwiius.placemc;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PluginMain extends JavaPlugin {
    public Queue<BlockPlacement> placementsQueue = new ConcurrentLinkedQueue<BlockPlacement>();

    @Override
    public void onEnable() {
        World w = getServer().getWorld("world");

        for (int x = 0; x < 1000; x++) {
            for (int z = 0; z < 1000; z++) {
                w.getBlockAt(x, 30, z).setType(Material.WOOL);
            }
        }

        new PlaceBlocksTask(this, placementsQueue, w).runTaskTimer(this, 20, 20);

        new GetBitmapTask(this, placementsQueue).runTaskTimerAsynchronously(this, 20, 20*10);

        getCommand("goto").setExecutor(new GotoCommand());
    }

    @Override
    public void onDisable() {

    }
}
