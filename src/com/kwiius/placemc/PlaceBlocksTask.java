package com.kwiius.placemc;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Queue;

public class PlaceBlocksTask extends BukkitRunnable {
    private final JavaPlugin plugin;
    private final Queue<BlockPlacement> queue;
    private final World world;



    public PlaceBlocksTask(JavaPlugin plugin, Queue<BlockPlacement> queue, World world) {
        this.plugin = plugin;
        this.queue = queue;
        this.world = world;
    }


    @Override
    public void run() {
        int updated = 0;

        while (!queue.isEmpty()) {
            BlockPlacement p = queue.remove();

            Block b = world.getBlockAt(p.x, 30, p.y);

            if (p.definitelyNew || b.getData() != p.blockType) {
                b.setData(p.blockType);
                updated++;
                Firework f = (Firework) world.spawn(new Location(world, p.x, 30, p.y), Firework.class);
                FireworkMeta fm = f.getFireworkMeta();
                fm.addEffect(FireworkEffect.builder()
                        .flicker(false)
                        .trail(true)
                        .with(FireworkEffect.Type.BALL)
                        .withColor(DyeColor.getByWoolData(p.blockType).getColor())
                        .withFade(DyeColor.getByWoolData(p.blockType).getColor())
                        .build());
                fm.setPower(3);
                f.setFireworkMeta(fm);
            }
        }

        if (updated > 0) {
            plugin.getServer().broadcastMessage(ChatColor.DARK_GREEN + "Updated " + ChatColor.GREEN + updated + ChatColor.DARK_GREEN + " blocks!");
        }
    }
}
