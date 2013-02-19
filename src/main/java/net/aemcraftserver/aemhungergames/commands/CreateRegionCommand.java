package main.java.net.aemcraftserver.aemhungergames.commands;

import main.java.net.aemcraftserver.aemhungergames.util.RegionConfig;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateRegionCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 3) {
			Player player = ((Player)sender);
			if(args[0].equalsIgnoreCase("create") && player.hasPermission("aemhg.createregion")) {
				if(args[2].equalsIgnoreCase("pos1")) {
					RegionConfig.regions.set("regions." + args[1] + ".P1", player.getLocation().toString());
				}
				else if(args[2].equalsIgnoreCase("pos2")) {
					RegionConfig.regions.set("regions." + args[1] + ".P2", player.getLocation().toString());
				} else {
					sender.sendMessage("Incorrect arguments");
				}
			} else {
				sender.sendMessage("Incorrect arguments");
			}
		} else {
			sender.sendMessage("Incorrect arguments");
		}
		return true;
	}
}
