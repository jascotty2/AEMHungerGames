package main.java.net.aemcraftserver.aemhungergames.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;

public class Game implements GameEvents{
	AEMHungerGames mainInstance = null;
	List<String> players = new ArrayList<String>();
	HashMap<String, Integer> points = new HashMap<String, Integer>(); //H,K:name,points
	Arena map = new Arena();
	int maxPlayers = 0;
	GameStatus status = null;

	@Override
	public void onGameStart(){
		int id = 0;
		for(String z: players){
			Player p = Bukkit.getServer().getPlayer(z);
			p.teleport(new Location(map.getWorld(), map.getSpawn(id).getX(), map.getSpawn(id).getY(), map.getSpawn(id).getZ()));
			id++;
		}
	}

	@Override
	public void onGameEnd(){
		//determine winner...
		String winnerName = null;
		int highestScore = -1;
		for(String name: this.players){
			int i = points.get(name);
			if(i > highestScore){
				highestScore = i;
				winnerName = name;
			}//else if(i == highestScore){
//				handle tie (but how?)
//			}
		}
		//reward winner...
		for(String z: this.mainInstance.getRewardCommands()){
			z = z.replaceFirst("/", "");//commands need to have no front / unless they're double slash commands like worldedit's //set, etc but then need one, not two
			this.mainInstance.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), z.replaceAll("%player%", winnerName));
		}
		//remove all players...
		for(String s: this.players){
			this.removePlayer(s);
		}
		//reset arena if needed(?)
	}

	public void addPlayer(Player p){
		addPlayer(p.getName());
	}

	public void addPlayer(String p){
		this.players.add(p);
	}

	public void removePlayer(Player p){
		this.removePlayer(p.getName());
	}

	public void removePlayer(String playerName){
		this.players.remove(this.players.indexOf(playerName));
	}

	public List<String> getPlayers(){
		return this.players;
	}
	public boolean join(Player p){
		if(players.size() < maxPlayers){
			this.addPlayer(p);
			int id = this.players.indexOf(p.getName());
			p.teleport(new Location(map.getWorld(), map.getSpawn(id).getX(), map.getSpawn(id).getY(), map.getSpawn(id).getZ()));
			return true;
		}
		return false;
	}

	public void quit(String playerName){
		this.removePlayer(playerName);
		//save stats
	}

	public void setMaxPlayers(int i){
		this.maxPlayers = i;
	}

	public int getMaxPlayers(){
		return this.maxPlayers;
	}

	public void setSpawn(int id, Vector v){
		this.map.setSpawn(id, v);
	}

	public Game(AEMHungerGames instance){
		this.map.pickRandom();
		this.status = GameStatus.READY;
	}
	public Game(AEMHungerGames instance, Arena a){
		this.map = a;
		this.status = GameStatus.READY;
	}
}