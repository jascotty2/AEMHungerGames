package main.java.net.aemcraftserver.aemhungergames;

import java.util.logging.Logger;

import main.java.net.aemcraftserver.aemhungergames.commands.CreateRegionCommand;
import main.java.net.aemcraftserver.aemhungergames.listeners.BlockListener;
import main.java.net.aemcraftserver.aemhungergames.listeners.PlayerQuitListener;
import main.java.net.aemcraftserver.aemhungergames.util.ConfigurationHandler;
import main.java.net.aemcraftserver.aemhungergames.util.Game;
import main.java.net.aemcraftserver.aemhungergames.util.RegionConfig;
import main.java.net.aemcraftserver.aemhungergames.util.SQLConnection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AEMHungerGames extends JavaPlugin{
	protected final static Logger log = Logger.getLogger("Minecraft");
	protected PluginDescriptionFile pdfile = null;
	protected SQLConnection connection = null;
	protected String[] rewardCommands = null;
	protected Location spawnLocation = null;
	public Game[] activeGames = null;
	public String spawnWorldName = null;
	public String[] arenaWorlds = null;

	@Override
	public void onEnable(){
		new ConfigurationHandler(this).loadConfig();
		connection = new SQLConnection();
		getCommand("aemregion").setExecutor(new CreateRegionCommand());
		RegionConfig.createConfig();
		getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		//connect to mysql db
		//create tables if they don't already exist
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

	public String[] getRewardCommands(){
		return this.rewardCommands;
	}

	public int searchGames(String p){
		int i = 0;
		for(Game g: this.activeGames){
			if(g.getPlayers().contains(p)){
				return i;
			}
			i++;
		}
		return -1;
	}

	public void setSpawnLocation(String s1, String s2, String s3){
		double[] d = {0,0,0};
		try{
			d[0] = Double.parseDouble(s1);
			d[1] = Double.parseDouble(s2);
			d[2] = Double.parseDouble(s3);
			if(Bukkit.getServer().getWorld(this.spawnWorldName) != null)
				this.spawnLocation = new Location(Bukkit.getServer().getWorld(this.spawnWorldName), d[0], d[1], d[2]);
			else
				throw new NumberFormatException(); //too lazy to rewrite line from catch block
		}catch(NumberFormatException e){
			lm("Something is wrong with the configuration - the spawnpoint is not correctly configured. Should be like world,x,y,z!");
			this.spawnLocation = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
		}
	}
}