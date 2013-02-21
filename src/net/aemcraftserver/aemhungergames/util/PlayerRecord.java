package net.aemcraftserver.aemhungergames.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerRecord {

	private String name;
	private int gamesPlayed;
	private int kills;
	private int deaths;
	private int points;
	private int gameID;

	public String getPlayerName() {
		return this.name;
	}

	public Player getAsPlayer() {
		return Bukkit.getServer().getPlayer(this.name);
	}

	public int getGamesPlayed() {
		return this.gamesPlayed;
	}

	public int getKills() {
		return this.kills;
	}

	public int getDeaths() {
		return this.deaths;
	}

	public double getKDRatio() {
		return (this.kills / this.deaths);
	}

	public int getPoints() {
		return this.points;
	}

	public void setGameID(int id) {
		this.gameID = id;
	}

	public int getGameID() {
		return this.gameID;
	}
}