package net.aemcraftserver.aemhungergames.listeners;

import java.util.Set;

import net.aemcraftserver.aemhungergames.AEMHungerGames;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

	protected AEMHungerGames plugin;

	public BlockListener(AEMHungerGames plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void BlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		Set<String> regions = plugin.regions.getRegionNames();
		for (String region : regions) {
			Location p1 = plugin.regions.getPos1(region);
			Location p2 = plugin.regions.getPos2(region);

			double maxX = (p1.getX() > p2.getX()) ? p1.getX() : p2.getX();
			double minX = (p1.getX() < p2.getX()) ? p1.getX() : p2.getX();
			if (loc.getX() <= maxX && loc.getX() >= minX) {
				double maxY = (p1.getY() > p2.getY()) ? p1.getY() : p2.getY();
				double minY = (p1.getY() < p2.getY()) ? p1.getY() : p2.getY();
				if (loc.getY() <= maxY && loc.getY() >= minY) {
					double maxZ = (p1.getZ() > p2.getZ()) ? p1.getZ() : p2.getZ();
					double minZ = (p1.getZ() < p2.getZ()) ? p1.getZ() : p2.getZ();
					if (loc.getZ() <= maxZ && loc.getZ() >= minZ) {
						if (player.hasPermission("aemhg.placeblock") || player.isOp()) {
							event.setCancelled(true);
							player.sendMessage(ChatColor.RED + "Invalid action: you are inside a protected zone");
						}
					}
				}
			}
		}
	}
}
