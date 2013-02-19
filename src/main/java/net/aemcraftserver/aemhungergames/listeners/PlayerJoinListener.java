package main.java.net.aemcraftserver.aemhungergames.listeners;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{
	AEMHungerGames plugin = null;

	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerJoinEvent(PlayerJoinEvent evt){
	}

	public PlayerJoinListener(AEMHungerGames main){
		this.plugin = main;
	}
}