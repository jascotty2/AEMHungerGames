package net.aemcraftserver.aemhungergames.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.util.Vector;

public class Arena {

	private World world = null;
	private List<Vector> spawnpoints = null;

	public Arena() {
	}

	public Arena(World world) {
		this.world = world;
	}

	public Arena(String world) {
		this.world = Bukkit.getServer().getWorld(world);
	}
	
	public World getWorld() {
		return this.world;
	}

	public Vector getSpawn(int id) {
		return this.spawnpoints.get(id);
	}

	public void setSpawn(int id, Vector v) {
		this.spawnpoints.set(id, v);
	}

	public void setSpawn(List<Vector> v) {
		this.spawnpoints = v;
	}

	public World pickRandom() {
		this.world = null;
		Random r = new Random();
		List<String> worlds = new ArrayList<String>();
		World z;
		for (World w : Bukkit.getWorlds()) {
			worlds.add(w.getName());
		}
		do {
			z = Bukkit.getServer().getWorld(worlds.get(r.nextInt(worlds.size())));
		} while (z.getEnvironment() != Environment.NORMAL);
		this.world = z;
		return z;
	}

}