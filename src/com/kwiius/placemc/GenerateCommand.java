package com.kwiius.placemc;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GenerateCommand implements CommandExecutor{

    private World w;

    GenerateCommand(World w){
        this.w = w;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("placemc.generate")) return false;

        for (int x = 0; x < 1000; x++) {
            for (int z = 0; z < 1000; z++) {
                w.getBlockAt(x, 30, z).setType(Material.WOOL);
            }
        }
        return true;
    }
}
