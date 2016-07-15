package me.cluter.fruittrees;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.cluter.lib.SkullManager;
import net.md_5.bungee.api.ChatColor;

public class eventCaller implements Listener {
	Main pl;

	public eventCaller(Main ins) {
		pl = ins;
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlock().getType() != Material.LEAVES)
			return;
		for (BushType bush : BushType.values()) {
			if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(bush.getType() + " Bush")) {
				if (pl.getWorldGuard().canBuild(e.getPlayer(), e.getBlock())) {
					TreeManagement.spawnTree(e.getPlayer(), e.getBlock().getLocation(), bush, e);
					break;
				}
				break;
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.FENCE) {
			if (pl.getWorldGuard().canBuild(e.getPlayer(), e.getBlock())) {
				TreeManagement.isTree(e.getBlock(), e);
			}
		}
		if (e.getBlock().getType() == Material.LEAVES) {
			Location loc1 = e.getBlock().getLocation().clone().add(0, -1, 0);
			if (loc1.getBlock().getType() == Material.FENCE) {
				if (pl.getWorldGuard().canBuild(e.getPlayer(), e.getBlock())) {
					TreeManagement.isTree(loc1.getBlock(), e);
					Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
						public void run() {
							if (TreeManagement.breaked) {
								loc1.getBlock().setType(Material.AIR);
								TreeManagement.breaked = false;
							}
						}
					}, 10);
					return;
				}
			}
			Location loc2 = e.getBlock().getLocation().clone().add(0, -2, 0);
			if (loc2.getBlock().getType() == Material.FENCE) {
				if (pl.getWorldGuard().canBuild(e.getPlayer(), e.getBlock())) {
					TreeManagement.isTree(loc2.getBlock(), e);
					Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
						public void run() {
							if (TreeManagement.breaked) {
								loc2.getBlock().setType(Material.AIR);
								TreeManagement.breaked = false;
							}
						}
					}, 10);
					return;
				}
			}
		}
		if (e.getBlock().getType() == Material.SKULL) {
			Skull skull = (Skull) e.getBlock().getState();
			if (skull.getSkullType() == SkullType.PLAYER) {
				if (pl.getWorldGuard().canBuild(e.getPlayer(), e.getBlock())) {
					for (BushType bt : BushType.values()) {
						if (SkullManager.getSkullProfileUUID(skull).equals(bt.getUUID())) {
							Collection<ItemStack> items = e.getBlock().getDrops();
							e.setCancelled(true);
							e.getBlock().setType(Material.AIR);
							for (ItemStack is : items) {
								ItemMeta im = is.getItemMeta();
								im.setDisplayName(ChatColor.GREEN + bt.getType());
								is.setItemMeta(im);
								e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), is);
							}
							break;
						}
					}
				}
			}
		}
	}
}
