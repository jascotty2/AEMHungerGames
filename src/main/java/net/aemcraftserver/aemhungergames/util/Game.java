package main.java.net.aemcraftserver.aemhungergames.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import main.java.net.aemcraftserver.aemhungergames.AEMHungerGames;

public class Game{
	AEMHungerGames mainInstance = null;
	List<String> players = new ArrayList<String>();
	HashMap<String, Integer> points = new HashMap<String, Integer>();
	Arena map = new Arena();
	List<String> t = new ArrayList<String>();
	List<String> z = new ArrayList<String>();
	
	public void addPlayer(Player p){
		addPlayer(p.getName());
	}
	
	public void addPlayer(String p){
		this.players.add(p);
	}
	
	public void startGame(){
		
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