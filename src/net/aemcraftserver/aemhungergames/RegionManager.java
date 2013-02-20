package net.aemcraftserver.aemhungergames;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class RegionManager {
	
	protected File regionsFile = new File("plugins/AEMHungerGames", "regions.yml");
	protected FileConfiguration regionConfig;
	protected AEMHungerGames plugin;
	
	protected RegionManager(AEMHungerGames plugin) {
		this.plugin = plugin;
		this.regionConfig = YamlConfiguration.loadConfiguration(regionsFile);
		if(!regionsFile.exists()) {
			save();
		}
	}
	
	public void save() {
		try {
			regionConfig.save(regionsFile);
		} catch (IOException e) {
			// Oops something went wrong!
			plugin.getLogger().log(Level.WARNING, "Cannot save the regions file!", e);
		}
	}
	
	public void setRegion(String name, String p1, String p2) {
		regionConfig.set("regions." + name + ".P1", p1);
		regionConfig.set("regions." + name + ".P2", p2);
	}
	
	public void setRegionPos1(String name, String p1) {
		regionConfig.set("regions." + name + ".P1", p1);
	}
	
	public void setRegionPos2(String name, String p2) {
		regionConfig.set("regions." + name + ".P2", p2);
	}
	
	public Set<String> getRegionNames() {
		return regionConfig.getConfigurationSection("Regions").getKeys(false);
	}
	
	public Location getPos1(String name) {
		return toLocation(regionConfig.getString("regions." + name + ".P1"));
	}
	
	public Location getPos2(String name) {
		return toLocation(regionConfig.getString("regions." + name + ".P2"));
	}
	
	
	public static Location toLocation(String location) {
		String[] loc = location.split(" ");
		try {
			World world = Bukkit.getServer().getWorld(loc[0]);
			int x = Integer.parseInt(loc[1]);
			int y = Integer.parseInt(loc[2]);
			int z = Integer.parseInt(loc[3]);
			return new Location(world, x, y, z);
		} catch (NumberFormatException err) {
			// Something went wrong!
			return null;
		}
	}
}
