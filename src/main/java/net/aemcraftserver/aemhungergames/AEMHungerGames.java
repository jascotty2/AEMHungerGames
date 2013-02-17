package main.java.net.aemcraftserver.aemhungergames;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class AEMHungerGames extends JavaPlugin{
	Logger logger;

	@Override
	public void onEnable(){
		logger = Logger.getLogger("Minecraft");
		logger.log(Level.INFO, "[AEMHungerGames] Enabled.");
	}

	public void onDisable(){
		logger.log(Level.INFO, "[AEMHungerGames] Disabled.");
	}
}