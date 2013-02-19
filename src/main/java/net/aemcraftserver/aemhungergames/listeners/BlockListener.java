package main.java.net.aemcraftserver.aemhungergames.listeners;

import java.util.Set;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;
import main.java.net.aemcraftserver.aemhungergames.util.RegionConfig;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
	
	AEMHungerGames plugin;
	
	public BlockListener(AEMHungerGames plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void BlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		Set<String> regions = RegionConfig.regions.getConfigurationSection("Regions").getKeys(false);
		for(String region : regions) {
			Location p1 = toLocation(RegionConfig.regions.getString("regions." + region + ".P1"));
			Location p2 = toLocation(RegionConfig.regions.getString("regions." + region + ".P2"));
			
			double maxX = (p1.getX() > p2.getX()) ? p1.getX() : p2.getX();
			double minX = (p1.getX() < p2.getX()) ? p1.getX() : p2.getX();
			if(loc.getX() <= maxX && loc.getX() >= minX) {
				double maxY = (p1.getY() > p2.getY()) ? p1.getY() : p2.getY();
				double minY = (p1.getY() < p2.getY()) ? p1.getY() : p2.getY();
				if(loc.getY() <= maxY && loc.getY() >= minY) {
					double maxZ = (p1.getZ() > p2.getZ()) ? p1.getZ() : p2.getZ();
					double minZ = (p1.getZ() < p2.getZ()) ? p1.getZ() : p2.getZ();
					if(loc.getZ() <= maxZ && loc.getZ() >= minZ) {
						if(player.hasPermission("aemhg.placeblock") || player.isOp()) {
							event.setCancelled(true);
							cm(player, "&c - Invalid action, you are inside a protected zone");
						}
					}
					continue;
				}
				continue;
			}
			continue;
		}
	}
	public void cm(Player player, String string) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', string));
	}
	public Location toLocation(String location) {
		String[] loc = location.split(" ");
		try
		{	
			World world = Bukkit.getServer().getWorld(loc[0]);
			int x = Integer.parseInt(loc[1]);
			int y = Integer.parseInt(loc[2]);
			int z = Integer.parseInt(loc[3]);
			return new Location(world, x, y, z);
		}
		catch(NumberFormatException err)
		{
			// Something went wrong!
			return null;
		}
	}
}
