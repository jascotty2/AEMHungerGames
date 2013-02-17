package main.java.net.aemcraftserver.aemhungergames;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class AEMHungerGames extends JavaPlugin{
	Logger log;
	
	@Override
	public void onEnable(){
		log = Logger.getLogger("Minecraft");
		log.log(Level.INFO, "[AEMHungerGames] Enabled.");
	}
	public void onDisable(){
		log.log(Level.INFO, "[AEMHungerGames] Disabled.");
	}
}