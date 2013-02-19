package main.java.net.aemcraftserver.aemhungergames.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;

public class Game{
	AEMHungerGames mainInstance = null;
	List<String> players = new ArrayList<String>();
	HashMap<String, Integer> points = new HashMap<String, Integer>();
	Arena map = new Arena();

	public void addPlayer(Player p){
		addPlayer(p.getName());
	}

	public void addPlayer(String p){
		this.players.add(p);
	}

	public void startGame(){
		int id = 0;
		for(String z: players){
			Player p = Bukkit.getServer().getPlayer(z);
			p.teleport(new Location(map.getWorld(), map.getSpawn(id).getX(), map.getSpawn(id).getY(), map.getSpawn(id).getZ()));
			id++;
		}
	}

	public void setSpawn(int id, Vector v){
		this.map.setSpawn(id, v);
	}

	public void endGame(){

	}

	public Game(AEMHungerGames instance){
		this.map.pickRandom();
	}
	public Game(AEMHungerGames instance, Arena a){
		this.map = a;
	}
}