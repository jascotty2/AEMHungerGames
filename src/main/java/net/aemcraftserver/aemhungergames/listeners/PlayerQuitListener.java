package main.java.net.aemcraftserver.aemhungergames.listeners;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;
import main.java.net.aemcraftserver.aemhungergames.util.Game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener{
	AEMHungerGames plugin = null;

	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent evt){
		int i = 0;
		for(Game g: this.plugin.activeGames){
			if(g.getPlayers().contains(evt.getPlayer().getName())){
				this.plugin.activeGames[i].quit(evt.getPlayer().getName());
			}
		}
	}

	public PlayerQuitListener(AEMHungerGames plugin){
		this.plugin = plugin;
	}
}