package me.cluter.fruittrees;

import java.util.Random;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.cluter.fruittrees.commands.consolegivebush;
import me.cluter.fruittrees.commands.sbush;

public class Main extends JavaPlugin {
	static Main ins;

	public Main() {
		ins = this;
	}

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new eventCaller(this), this);
		getCommand("sbush").setExecutor(new sbush());
		getCommand("consolegivebush").setExecutor(new consolegivebush());
		saveDefaultConfig();
	}

	public static Main getInstance() {
		return ins;
	}

	public static int randomGen() {
		Random r = new Random();
		int ret = r.nextInt(60) + 120;
		return ret;
	}
	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

}
