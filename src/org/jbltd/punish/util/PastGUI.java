package org.jbltd.punish.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PastGUI {

    private DatabaseManager _manager;

    public PastGUI(DatabaseManager manager) {

	_manager = manager;

    }

    public void openInventory(Player caller, OfflinePlayer target, String reason) throws Exception {

	Inventory inv = Bukkit.createInventory(caller, 36,
		ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Previous Punishments");

	ArrayList<Punish> punishments = _manager.getPastPunishments(target);

	int slot = 9;

	for (int is = 0; is < punishments.size(); is++) {
	    Punish p = punishments.get(is);

	    if (p.getPunishType().equals("Warning")) {
		ItemStack i = new ItemStack(Material.PAPER);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Warning");
		im.setLore(Arrays.asList(ChatColor.BLUE + "Punisher: " + ChatColor.GOLD + p.punisherName(),
			ChatColor.BLUE + "Date: " + ChatColor.GOLD + p.getDate(),
			ChatColor.BLUE + "Duration: " + ChatColor.GOLD + UtilTime.convert(p.getDuration()),
			ChatColor.BLUE + "Reason: " + ChatColor.GOLD + p.getReason()));
		i.setItemMeta(im);
		inv.setItem(slot++, i);
		continue;
	    }
	    if (p.getPunishType().equals("Mute")) {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Mute");
		im.setLore(Arrays.asList(ChatColor.BLUE + "Punisher: " + ChatColor.GOLD + p.punisherName(),
			ChatColor.BLUE + "Date: " + ChatColor.GOLD + p.getDate(),
			ChatColor.BLUE + "Duration: " + ChatColor.GOLD + UtilTime.convert(p.getDuration()),
			ChatColor.BLUE + "Reason: " + ChatColor.GOLD + p.getReason()));
		i.setItemMeta(im);
		inv.setItem(slot++, i);
		continue;
	    }
	    if (p.getPunishType().equals("Ban")) {
		ItemStack i = new ItemStack(Material.ANVIL);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Ban");
		im.setLore(Arrays.asList(ChatColor.BLUE + "Punisher: " + ChatColor.GOLD + p.punisherName(),
			ChatColor.BLUE + "Date: " + ChatColor.GOLD + p.getDate(),
			ChatColor.BLUE + "Duration: " + ChatColor.GOLD + UtilTime.convert(p.getDuration()),
			ChatColor.BLUE + "Reason: " + ChatColor.GOLD + p.getReason()));
		i.setItemMeta(im);
		inv.setItem(slot++, i);
		continue;
	    }
	}

	ItemStack goBack = new ItemStack(Material.BARRIER);
	ItemMeta m = goBack.getItemMeta();

	m.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "GO BACK");
	goBack.setItemMeta(m);

	inv.setItem(0, goBack);
	for(int i = 1; i < 9; i++)
	{
	    inv.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()));
	}

	caller.openInventory(inv);
    }

    
}
