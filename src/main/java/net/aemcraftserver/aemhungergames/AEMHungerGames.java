package main.java.net.aemcraftserver.aemhungergames;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AEMHungerGames extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	PluginDescriptionFile pdfile;
	
	/**
	 * Shows a message in the console with a prefix tag
	 * @param msg The message to be displayed on the console
	 */
	public void lm(String msg){
		log.info("[AEMHungerGames] "+msg);
	}
	@Override
	public void onEnable() {
		lm("Enabled.");
	}
	@Override
	public void onDisable() {
		lm("Enabled.");
	}
}