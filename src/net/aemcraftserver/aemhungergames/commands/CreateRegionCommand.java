package net.aemcraftserver.aemhungergames.commands;

import net.aemcraftserver.aemhungergames.AEMHungerGames;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateRegionCommand implements CommandExecutor {

	protected AEMHungerGames plugin;

	public CreateRegionCommand(AEMHungerGames plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Must be used as a Player");
			return true;
		}
		Player player = ((Player) sender);
		if (args.length == 3 && args[0].equalsIgnoreCase("create")) {
			if (!player.hasPermission("aemhg.createregion")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
			} else {
				if (args[2].equalsIgnoreCase("pos1")) {
					plugin.regions.setRegionPos1(args[1], player.getLocation().toString());
				} else if (args[2].equalsIgnoreCase("pos2")) {
					plugin.regions.setRegionPos2(args[1], player.getLocation().toString());
				} else {
					sender.sendMessage("Incorrect arguments");
					return false;
				}
			}
		} else {
			sender.sendMessage("Incorrect arguments");
			return false;
		}
		return true;
	}
}
