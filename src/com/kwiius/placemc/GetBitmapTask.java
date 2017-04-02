package com.kwiius.placemc;

import org.apache.http.client.fluent.Request;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Queue;

public class GetBitmapTask extends BukkitRunnable {
    Queue<BlockPlacement> queue;
    JavaPlugin plugin;

    public GetBitmapTask(JavaPlugin plugin, Queue<BlockPlacement> queue) {
        this.plugin = plugin;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            plugin.getLogger().info("Fetching bitmap...");

            byte[] bs = Request.Get("https://www.reddit.com/api/place/board-bitmap")
                    .userAgent("PlaceMC 0.0.1 by /u/JJJollyjim")
                    .execute()
                    .returnContent()
                    .asBytes();

            plugin.getLogger().info("New bitmap!");

            for (int halfX = 0; halfX < 500; halfX++) {
                for (int y = 0; y < 1000; y++) {
                    byte b = bs[4 + (500*y + halfX)];

                    queue.add(new BlockPlacement(halfX * 2,     y, (byte) ((b & 0xF0) >> 4), false));
                    queue.add(new BlockPlacement(halfX * 2 + 1, y, (byte)  (b & 0x0F),       false));
                }
            }

            plugin.getLogger().info("Enqueued: " + queue.size());

        } catch (Exception e) {
            e.printStackTrace();
            plugin.getLogger().info("Error fetching bitmap :(");
        } finally {
//            runTaskLaterAsynchronously(plugin, 200);
        }
    }
}
