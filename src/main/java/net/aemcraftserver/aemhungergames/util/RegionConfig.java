package main.java.net.aemcraftserver.aemhungergames.util;

import java.io.File;
import java.io.IOException;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class RegionConfig {
	
	static File regionsFile = new File("plugins/AEMHungerGames", "regions.yml");
	public static FileConfiguration regions = YamlConfiguration.loadConfiguration(regionsFile);
	
	public static void createConfig() {
		if(!regionsFile.exists()) {
			try {
				regions.save(regionsFile);
			} catch (IOException e) {
				// Oops something went wrong!
				e.printStackTrace();
				AEMHungerGames.lm("Cannot save the regions file!");
			}
		}
	}
	public static void save() {
		try {
			regions.save(regionsFile);
		} catch (IOException e) {
			// Oops something went wrong!
			e.printStackTrace();
			AEMHungerGames.lm("Cannot save the regions file!");
		}
	}
}
