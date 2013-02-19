package main.java.net.aemcraftserver.aemhungergames;

import java.util.logging.Logger;

import main.java.net.aemcraftserver.aemhungergames.commands.CreateRegionCommand;
import main.java.net.aemcraftserver.aemhungergames.commands.JoinCommand;
import main.java.net.aemcraftserver.aemhungergames.listeners.BlockListener;
import main.java.net.aemcraftserver.aemhungergames.util.Game;
import main.java.net.aemcraftserver.aemhungergames.util.GameStatus;
import main.java.net.aemcraftserver.aemhungergames.util.RegionConfig;
import main.java.net.aemcraftserver.aemhungergames.util.SQLConnection;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AEMHungerGames extends JavaPlugin{
	protected final static Logger log = Logger.getLogger("Minecraft");
	protected PluginDescriptionFile pdfile;
	protected GameStatus status;
	protected SQLConnection connection;
	protected String[] rewardCommands = null;
	public Game activeGame = null;

	@Override
	public void onEnable(){
		connection = new SQLConnection();
		getServer().getPluginCommand("join").setExecutor(new JoinCommand());
		getCommand("aemregion").setExecutor(new CreateRegionCommand());
		RegionConfig.createConfig();
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		
		lm("Version "+pdfile.getVersion()+" enabled.");
	}

	@Override
	public void onDisable(){
		lm("Disabled.");
	}

	/**
	 * Shows a message in the console with a prefix tag
	 * @param msg The message to be displayed on the console
	 */
	public static void lm(String msg){
		log.info("[AEMHungerGames] "+msg);
	}

	public void setRewardCommands(String... commands){
		this.rewardCommands = commands;
	}
}