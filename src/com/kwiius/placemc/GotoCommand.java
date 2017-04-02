package com.kwiius.placemc;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GotoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String cmd, String[] args) {
        if (args.length == 2 && commandSender instanceof Player) {
            Player p = (Player) commandSender;
            int x, z;
            try {
                x = Integer.parseInt(args[0]);
                z = Integer.parseInt(args[1]);
            } catch (Exception e) {
                return false;
            }

            if (x >= 0 && x <= 1000 && z >= 0 && z <= 1000) {
                Location l = new Location(p.getWorld(), x, 120, z);
                l.setDirection(p.getLocation().getDirection());
                p.teleport(l);
                return true;
            } else {
                commandSender.sendMessage("x and y should be between 0 and 1000");
                return false;
            }

        }
        return false;
    }
}
