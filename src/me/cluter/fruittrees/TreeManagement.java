package me.cluter.fruittrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.cluter.lib.ParticleEffect;
import me.cluter.lib.SkullManager;
import me.cluter.lib.Util;
import me.cluter.lib.blockManager;

public class TreeManagement {

	public static boolean breaked = false;
	static int id1;
	static int id2;
	static int id3;
	static int id4;

	@SuppressWarnings("deprecation")
	public static void spawnTree(Player player, Location location, BushType bushType, BlockPlaceEvent e) {
		Block there = location.getBlock();
		Block up1 = location.clone().add(0, 1, 0).getBlock();
		Block up2 = location.clone().add(0, 2, 0).getBlock();
		if (up1.getType() == Material.AIR) {
			if (up2.getType() == Material.AIR) {
				if (e.getPlayer().getItemInHand().getAmount() > 1) {
					e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
				} else {
					e.getPlayer().getItemInHand().setType(Material.AIR);
				}
				up1.setType(Material.LEAVES);
				up1.setData((byte) 4);
				up2.setType(Material.LEAVES);
				up2.setData((byte) 4);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					public void run() {
						there.setType(Material.FENCE);
					}
				}, 1);
				ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 20, location, 20);
				List<String> loc = Main.getInstance().getConfig().getStringList("locations");
				loc.add("X:" + location.getBlockX() + " Y:" + location.getBlockY() + " Z:" + location.getBlockZ() + ";"
						+ bushType.toString());
				Main.getInstance().getConfig().set("locations", loc);
				Main.getInstance().saveConfig();
				fruitAdd(location, bushType);
				return;
			}
			player.sendMessage("§8[§3Bushes§8] §cThere is no room to spawn a tree! (You need a 2x1 space!)");
			e.setCancelled(true);
			return;
		}
		player.sendMessage("§8[§3Bushes§8] §cThere is no room to spawn a tree! (You need a 2x1 space!)");
		e.setCancelled(true);
		return;
	}

	public static void fruitAdd(Location loc, BushType bt) {
		int t1 = Main.randomGen() * 20;
		int t2 = t1 + (Main.randomGen() * 20);
		int t3 = t2 + (Main.randomGen() * 20);
		int t4 = t3 + (Main.randomGen() * 20);
		/* int t1 = 20; int t2 = 40; int t3 = 60; int t4 = 80; */
		Block up1 = loc.clone().add(0, 1, 0).getBlock();
		Block up2 = loc.clone().add(0, 2, 0).getBlock();
		id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				if (loc.getBlock().getType() == Material.FENCE && up1.getType() == Material.LEAVES
						&& up2.getType() == Material.LEAVES) {
					if (up1.getRelative(BlockFace.NORTH).getType() == Material.AIR) {
						up1.getRelative(BlockFace.NORTH).setType(Material.SKULL);
						Skull s = (Skull) up1.getRelative(BlockFace.NORTH).getState();
						SkullManager.setCustomSkull(bt.uuid, bt.url, s);
						ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 10,
								up1.getRelative(BlockFace.NORTH).getLocation(), 20);
					} else
						return;
				} else {
					Bukkit.getScheduler().cancelTask(id2);
					Bukkit.getScheduler().cancelTask(id3);
					Bukkit.getScheduler().cancelTask(id4);
					return;
				}
			}
		}, t1);

		id2 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				if (loc.getBlock().getType() == Material.FENCE && up1.getType() == Material.LEAVES
						&& up2.getType() == Material.LEAVES) {
					if (up2.getRelative(BlockFace.EAST).getType() == Material.AIR) {
						up2.getRelative(BlockFace.EAST).setType(Material.SKULL);
						up2.getRelative(BlockFace.EAST).setData((byte) 5);
						Skull s = (Skull) up2.getRelative(BlockFace.EAST).getState();
						s.setRotation(BlockFace.EAST);
						SkullManager.setCustomSkull(bt.uuid, bt.url, s);
						ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 10,
								up2.getRelative(BlockFace.EAST).getLocation(), 20);
					} else
						return;
				} else {
					Bukkit.getScheduler().cancelTask(id3);
					Bukkit.getScheduler().cancelTask(id4);
					return;
				}
			}
		}, t2);
		id3 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				if (loc.getBlock().getType() == Material.FENCE && up1.getType() == Material.LEAVES
						&& up2.getType() == Material.LEAVES) {
					if (up1.getRelative(BlockFace.SOUTH).getType() == Material.AIR) {
						up1.getRelative(BlockFace.SOUTH).setType(Material.SKULL);
						up1.getRelative(BlockFace.SOUTH).setData((byte) 3);
						Skull s = (Skull) up1.getRelative(BlockFace.SOUTH).getState();
						s.setRotation(BlockFace.SOUTH);
						SkullManager.setCustomSkull(bt.uuid, bt.url, s);
						ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 10,
								up1.getRelative(BlockFace.SOUTH).getLocation(), 20);
					} else
						return;
				} else {
					Bukkit.getScheduler().cancelTask(id4);
					return;
				}
			}
		}, t3);
		id4 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				if (loc.getBlock().getType() == Material.FENCE && up1.getType() == Material.LEAVES
						&& up2.getType() == Material.LEAVES) {
					if (up2.getRelative(BlockFace.WEST).getType() == Material.AIR) {
						up2.getRelative(BlockFace.WEST).setType(Material.SKULL);
						up2.getRelative(BlockFace.WEST).setData((byte) 4);
						Skull s = (Skull) up2.getRelative(BlockFace.WEST).getState();
						s.setRotation(BlockFace.WEST);
						SkullManager.setCustomSkull(bt.uuid, bt.url, s);
						ParticleEffect.VILLAGER_HAPPY.display(1, 1, 1, 1, 10,
								up2.getRelative(BlockFace.WEST).getLocation(), 20);

					} else
						return;
				} else
					return;
			}
		}, t4);

	}

	public static void breakTree(Location loc, BushType bt) {
		Location pos1 = loc.clone().add(1, 1, 1);
		Location pos2 = loc.clone().add(-1, 2, -1);
		List<Block> blocks = blockManager.getBlocks(pos1, pos2);
		int num = 0;
		for (Block b : blocks) {
			if (b.getType() == Material.SKULL) {
				Skull skull = (Skull)b.getState();
				if(SkullManager.getSkullProfileUUID(skull).equals(bt.getUUID())) {
				num++;
				b.setType(Material.AIR);
				}
			}
			if (b.getType() == Material.LEAVES || b.getType() == Material.FENCE) {
				b.setType(Material.AIR);
			}
		}
		ItemStack skull = SkullManager.getCustomSkull(bt.getUUID(), bt.getURL(), num);
		ItemStack seed = new ItemStack(Material.LEAVES, 1);
		ItemMeta im = seed.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + bt.getType() + " Bush");
		seed.setItemMeta(im);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setDisplayName(ChatColor.GREEN + bt.getType());
		skull.setItemMeta(sm);
		loc.getWorld().dropItemNaturally(loc, seed);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			public void run() {
				if(loc.getBlock().getType() == Material.FENCE) {
				loc.getBlock().setType(Material.AIR);
			}
			}
		}, 20);
		if (num > 0) {
			if(bt == BushType.DONUT1 || bt == BushType.TACO) return;
			loc.getWorld().dropItemNaturally(loc, skull);
			if(num == 1) {
				if(Util.percentChance(0.005)) {
					loc.getWorld().dropItemNaturally(loc, seed);
				}
				return;
			}
			if(num == 2) {
				if(Util.percentChance(0.01)) {
					loc.getWorld().dropItemNaturally(loc, seed);
				}
				return;
			}
			if(num == 3) {
				if(Util.percentChance(0.015)) {
					loc.getWorld().dropItemNaturally(loc, seed);
				}
				return;
			}
			if(num == 4) {
				if(Util.percentChance(0.02)) {
					loc.getWorld().dropItemNaturally(loc, seed);
					return;
				}
			}
		}
	}

	public static void isTree(Block b, BlockBreakEvent e) {
		String locc = "X:" + b.getX() + " Y:" + b.getY() + " Z:" + b.getZ();
		HashMap<String, BushType> loc = new HashMap<String, BushType>();
		List<String> s = Main.getInstance().getConfig().getStringList("locations");
		for (String str : s) {
			String[] words = str.split(";");
			loc.put(words[0], BushType.fromString(words[1]));
		}
		for (String ss : loc.keySet()) {
			if (locc.equals(ss)) {
				e.setCancelled(true);
				e.getBlock().getDrops().clear();
				e.getBlock().setType(Material.AIR);
				breakTree(b.getLocation(), loc.get(ss));
				loc.remove(locc);
				List<String> sss = new ArrayList<String>();
				for (String stt : loc.keySet()) {
					sss.add(stt + ";" + loc.get(stt));
				}
				Main.getInstance().getConfig().set("locations", sss);
				Main.getInstance().saveConfig();
				breaked = true;
				break;
			}

		}
	}
}
