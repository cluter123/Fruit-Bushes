package me.cluter.fruittrees.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.farmlifemc.api.bushFormat;

import me.cluter.fruittrees.BushType;
import me.cluter.fruittrees.Main;
import net.md_5.bungee.api.ChatColor;

public class sbush implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender.hasPermission("fruitbushes.spawn"))) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to do this!");
			return false;
		}
		
		if(args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Please specify a bush type! Usage: </sbush <bush> <amt> <player>");
			return true;
		}
		if(args.length == 1) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Invalid usage. Usage: </sbush <bush> <amt> <player>");
				return true;
			}
			Player p = (Player)sender;
			BushType bush = null;
			for(BushType bt : BushType.values()) {
				if(args[0].equalsIgnoreCase(bt.getType())) {
					bush = bt;
					break;
				}
			}
			if(bush == null) {
				p.sendMessage(ChatColor.RED + "Please specify a valid bush type!");
				return false;
			}
			p.getInventory().addItem(bushFormat.getBush(bush, 1));
			p.sendMessage(ChatColor.GREEN + "You have given yourself an " + bush.getType() + " bush.");
			return true;
		}
		if(args.length == 2) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Invalid usage. Usage: </sbush <bush> <amt> <player>");
				return true;
			}
			Player p = (Player)sender;
			BushType bush = null;
			for(BushType bt : BushType.values()) {
				if(args[0].equalsIgnoreCase(bt.getType())) {
					bush = bt;
					break;
				}
			}
			if(bush == null) {
				p.sendMessage(ChatColor.RED + "Please specify a valid bush type!");
				return false;
			}
			if(!(Main.isInt(args[1]))) {
				p.sendMessage(ChatColor.RED + "Please specify a valid amount!");
				return false;
			}
			p.getInventory().addItem(bushFormat.getBush(bush, Integer.parseInt(args[1])));
			p.sendMessage(ChatColor.GREEN + "You have given yourself " + args[1] + " " + bush.getType() + " bush(es).");
			return true;
		}
		if(args.length == 3) {
			Player p = Bukkit.getPlayer(args[2]);
			if(p == null) {
				sender.sendMessage(ChatColor.RED + "Please specify a valid player.");
				return false;
			}
			BushType bush = null;
			for(BushType bt : BushType.values()) {
				if(args[0].equalsIgnoreCase(bt.getType())) {
					bush = bt;
					break;
				}
			}
			if(bush == null) {
				sender.sendMessage(ChatColor.RED + "Please specify a valid bush type!");
				return false;
			}
			if(!(Main.isInt(args[1]))) {
				sender.sendMessage(ChatColor.RED + "Please specify a valid amount!");
				return false;
			}
			p.getInventory().addItem(bushFormat.getBush(bush, Integer.parseInt(args[1])));
			sender.sendMessage(ChatColor.GREEN + "You have given " + p.getName() + " " + args[1] + " " + bush.getType() + " bush(es).");
			return true;
		}
		sender.sendMessage(ChatColor.RED + "Please specify a bush type! Usage: </sbush <bush> <amt> <player>");
		return false;
	}

}
