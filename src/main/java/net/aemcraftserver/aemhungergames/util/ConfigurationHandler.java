package main.java.net.aemcraftserver.aemhungergames.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Handles the AEMHungerGames configuration.
 * @version 1.3.27.27 (Final? Unless more configuration nodes come to be.)
 * @category Configuration
 * @since 1.0.1.3 (Announced as 1.0)
 * @author Dan | TheLunarFrog
 */
public class ConfigurationHandler extends AEMHungerGames{

	protected static YamlConfiguration config;
	protected static File configFile;
	AEMHungerGames plugin;

	@Override
	public void saveConfig(){
		try{
			config.save(configFile);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void save(){
		try{
			config.save(configFile);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	protected void loadConfig(){
		configFile = new File(Bukkit.getServer().getPluginManager().getPlugin("AEMHungerGames").getDataFolder(), "config.yml");
		if(configFile.exists()){
			config = new YamlConfiguration();
			try{
				config.load(configFile);
				plugin.setRewardCommands(config.getStringList("rewardCommands").toArray(new String[config.getStringList("rewardCommands").size()]));
			}catch(FileNotFoundException ex){
				Logger.getLogger("Minecraft").severe("An exception has occurred while AEMHungerGames was loading the configuration.");
				ex.printStackTrace();
			}catch(IOException ex){
				Logger.getLogger("Minecraft").severe("An exception has occurred while AEMHungerGames was loading the configuration.");
				ex.printStackTrace();
			}catch(InvalidConfigurationException ex){
				Logger.getLogger("Minecraft").severe("An exception has occurred while AEMHungerGames was loading the configuration.");
				ex.printStackTrace();
			}
		}else{
			try{
				Bukkit.getServer().getPluginManager().getPlugin("AEMHungerGames").getDataFolder().mkdir();
				InputStream jarURL = ConfigurationHandler.class.getResourceAsStream("/main/resources/config.yml");
				copyFile(jarURL, configFile);
				config = new YamlConfiguration();
				config.load(configFile);
				Logger.getLogger("Minecraft").info("Configuration loaded successfully.");
			}catch(Exception e){
				Logger.getLogger("Minecraft").severe("Exception occurred while creating a new configuration file!");
				e.printStackTrace();
			}
		}
	}

	static private void copyFile(InputStream in, File out) throws Exception{
		InputStream fis = in;
		FileOutputStream fos = new FileOutputStream(out);
		try{
			byte[] buf = new byte[1024];
			int i = 0;
			while((i = fis.read(buf)) != -1){
				fos.write(buf, 0, i);
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(fis != null){
				fis.close();
			}
			if(fos != null){
				fos.close();
			}
		}
	}

	protected ConfigurationHandler(AEMHungerGames plugin){
		this.plugin = plugin;
	}
}