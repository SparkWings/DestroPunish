package org.jbltd.punish.util;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Dye;
import org.jbltd.punish.Main;
import org.jbltd.punish.command.PunishCommand;

import net.md_5.bungee.api.ChatColor;

public class PunishmentGUI implements Listener {

    private static DatabaseManager _manager;

    private static OfflinePlayer targett;
    private String base = ChatColor.BLUE + "" + ChatColor.BOLD + "Punish " + ChatColor.GRAY + "" + ChatColor.BOLD
	    + "> ";

    public PunishmentGUI(DatabaseManager manager) {

	_manager = manager;

    }

    public static void openInventory(Player caller, OfflinePlayer target, String reason) {
	Inventory inv = Bukkit.createInventory(caller, 54, ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Punish");

	String[] design = new String[] {
		"o", "o", "o", "o", "h", "o", "o", "o", "o",
		"o", "o", "o", "o", "o", "o", "o", "o", "o",
		"o", "k", "o", "a", "d", "g", "o", "n", "o",
		"o", "l", "o", "b", "e", "i", "o", "p", "o",
		"o", "m", "o", "c", "f", "j", "o", "q", "o",
		"o", "o", "o", "o", "o", "o", "o", "o", "o"
	};

	for (int i = 0; i < design.length; i++) {
	    String current = design[i];

	    switch (current) {

	    case "o":
		continue;

	    case "h":
		SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);

		targett = target;
		
		meta.setOwner(targett.getName());
		meta.setLore(Arrays.asList(reason));

		ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		stack.setItemMeta(meta);

		inv.setItem(i, stack);
		continue;

	    case "k":
		
		try {
		    if(_manager.isPunished(targett.getUniqueId().toString()))
		    {
			Punish p = _manager.getPunishment(targett.getUniqueId().toString());
			
		        if(_manager.getPunishType(targett.getUniqueId().toString()).equals("Mute"))
		        {
		            ItemStack book = new ItemStack(Material.BOOK);
				ItemMeta m = book.getItemMeta();
				m.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "CHAT OFFENSES");
				m.setLore(Arrays.asList("-=-=Current Punishment-=-=",ChatColor.BLUE + "Punisher: " + ChatColor.GOLD + p.punisherName(),
					ChatColor.BLUE + "Date: " + ChatColor.GOLD + p.getDate(),
					ChatColor.BLUE + "Duration: " + ChatColor.GOLD + UtilTime.convert(p.getDuration()),
					ChatColor.BLUE + "Reason: " + ChatColor.GOLD + p.getReason()));
				m.addEnchant(Enchantment.DURABILITY, 1, false);
				book.setItemMeta(m);

				inv.setItem(i, book);
				continue;
		        }
		    }
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		ItemStack book = new ItemStack(Material.BOOK);
		ItemMeta m = book.getItemMeta();
		m.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "CHAT OFFENSES");
		m.setLore(Arrays.asList(ChatColor.GRAY+""+ChatColor.ITALIC+"No current punishment"));
		book.setItemMeta(m);

		inv.setItem(i, book);
		continue;

	    case "l":
		
		try {
		    if(_manager.isGBanned(targett.getUniqueId().toString()))
		    {

			Punish p = _manager.getPunishment(targett.getUniqueId().toString());
			
			ItemStack hopper = new ItemStack(Material.HOPPER);
			ItemMeta hm = hopper.getItemMeta();
			hm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "GAMEPLAY OFFENSES");
			hm.setLore(Arrays.asList("-=-=Current Punishment-=-=",ChatColor.BLUE + "Punisher: " + ChatColor.GOLD + p.punisherName(),
				ChatColor.BLUE + "Date: " + ChatColor.GOLD + p.getDate(),
				ChatColor.BLUE + "Duration: " + ChatColor.GOLD + UtilTime.convert(p.getDuration()),
				ChatColor.BLUE + "Reason: " + ChatColor.GOLD + p.getReason()));
			hm.addEnchant(Enchantment.DURABILITY, 1, false);
			hopper.setItemMeta(hm);

			inv.setItem(i, hopper);
			continue;
		      
		    }
		}catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		ItemStack hopper = new ItemStack(Material.HOPPER);
		ItemMeta hm = hopper.getItemMeta();
		hm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "GAMEPLAY OFFENSES");
		hm.setLore(Arrays.asList(ChatColor.GRAY+""+ChatColor.ITALIC+"No current punishment"));
		hopper.setItemMeta(hm);

		inv.setItem(i, hopper);
		continue;
	    case "m":

		try{
		 if(_manager.isBanned(targett.getUniqueId().toString()))
		    {

		     Punish p = _manager.getPunishment(targett.getUniqueId().toString());
			
		     ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta dm = dsword.getItemMeta();
			dm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "HACKING OFFENSES");
			dm.setLore(Arrays.asList("-=-=Current Punishment-=-=",ChatColor.BLUE + "Punisher: " + ChatColor.GOLD + p.punisherName(),
				ChatColor.BLUE + "Date: " + ChatColor.GOLD + p.getDate(),
				ChatColor.BLUE + "Duration: " + ChatColor.GOLD + UtilTime.convert(p.getDuration()),
				ChatColor.BLUE + "Reason: " + ChatColor.GOLD + p.getReason()));
			dm.addEnchant(Enchantment.DURABILITY, 1, false);
			dsword.setItemMeta(dm);
			inv.setItem(i, dsword);

			continue;
		      
		    }
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta dm = dsword.getItemMeta();
		dm.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "HACKING OFFENSES");
		dm.setLore(Arrays.asList(ChatColor.GRAY+""+ChatColor.ITALIC+"No current punishment"));
		dsword.setItemMeta(dm);
		inv.setItem(i, dsword);

		continue;
	    case "a":

		ItemStack fgreen = new ItemStack(Material.INK_SACK, 1, (byte) 10);
		ItemMeta fgm = fgreen.getItemMeta();
		fgm.setDisplayName(ChatColor.GREEN + "Severity 1 Mute");
		fgreen.setItemMeta(fgm);
		inv.setItem(i, fgreen);
		continue;
	    case "b":

		ItemStack fgreen2 = new ItemStack(Material.INK_SACK, 1, (byte) 10);
		ItemMeta fgm2 = fgreen2.getItemMeta();
		fgm2.setDisplayName(ChatColor.GREEN + "Severity 1 Gameplay");
		fgreen2.setItemMeta(fgm2);
		inv.setItem(i, fgreen2);
		continue;
	    case "c":

		ItemStack fgreen3 = new ItemStack(Material.INK_SACK, 1, (byte) 10);
		ItemMeta fgm3 = fgreen3.getItemMeta();
		fgm3.setDisplayName(ChatColor.GREEN + "Severity 1 Hacking");
		fgreen3.setItemMeta(fgm3);
		inv.setItem(i, fgreen3);
		continue;
	    case "d":

		ItemStack fye = new ItemStack(Material.INK_SACK, 1, (byte) 11);
		ItemMeta fyem = fye.getItemMeta();
		fyem.setDisplayName(ChatColor.YELLOW + "Severity 2 Mute");
		fye.setItemMeta(fyem);
		inv.setItem(i, fye);
		continue;
	    case "e":

		ItemStack fye2 = new ItemStack(Material.INK_SACK, 1, (byte) 11);
		ItemMeta fyem2 = fye2.getItemMeta();
		fyem2.setDisplayName(ChatColor.YELLOW + "Severity 2 Gameplay");
		fye2.setItemMeta(fyem2);
		inv.setItem(i, fye2);
		continue;

	    case "f":

		ItemStack fye3 = new ItemStack(Material.INK_SACK, 1, (byte) 11);
		ItemMeta fyem3 = fye3.getItemMeta();
		fyem3.setDisplayName(ChatColor.YELLOW + "Severity 2 Hacking");
		fye3.setItemMeta(fyem3);
		inv.setItem(i, fye3);
		continue;
	    case "g":

		ItemStack fred = new ItemStack(Material.INK_SACK, 1, (byte) 1);
		ItemMeta fredm = fred.getItemMeta();
		fredm.setDisplayName(ChatColor.RED + "Severity 3 Mute");
		fred.setItemMeta(fredm);
		inv.setItem(i, fred);
		continue;
	    case "i":

		ItemStack fred2 = new ItemStack(Material.INK_SACK, 1, (byte) 1);
		ItemMeta fredm2 = fred2.getItemMeta();
		fredm2.setDisplayName(ChatColor.RED + "Severity 3 Gameplay");
		fred2.setItemMeta(fredm2);
		inv.setItem(i, fred2);
		continue;
	    case "j":

		ItemStack fred3 = new ItemStack(Material.INK_SACK, 1, (byte) 1);
		ItemMeta fredm3 = fred3.getItemMeta();
		fredm3.setDisplayName(ChatColor.RED + "Severity 3 Hacking");
		fred3.setItemMeta(fredm3);
		inv.setItem(i, fred3);
		continue;
	    case "n":

		ItemStack paper = new ItemStack(Material.PAPER);
		ItemMeta paperm = paper.getItemMeta();
		paperm.setDisplayName(ChatColor.RED + "Warning");
		paper.setItemMeta(paperm);
		inv.setItem(i, paper);
		continue;
	    case "p":

		ItemStack bg = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta bgm = bg.getItemMeta();
		bgm.setDisplayName(ChatColor.DARK_RED + "Permanent Mute");
		bg.setItemMeta(bgm);
		inv.setItem(i, bg);
		continue;
	    case "q":

		ItemStack ban = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta banm = ban.getItemMeta();
		banm.setDisplayName(ChatColor.DARK_RED + "Permanent Ban");
		ban.setItemMeta(banm);
		inv.setItem(i, ban);
		continue;

	    }

	}

	caller.openInventory(inv);

    }

    @EventHandler
    public void handle(InventoryClickEvent e) {
	
	if (e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Punish")) {

	    e.setCancelled(true);
	    
	    Player player = (Player) e.getWhoClicked();
	    ItemStack i = e.getCurrentItem();

	    String reason = PunishCommand.reason;

	    if (i.getType() == Material.SKULL_ITEM) {
		try {
		    new PastGUI(_manager).openInventory(player, targett, reason);
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }

	    if (i.getType() == Material.BOOK)
	    {
		try {
		    if(_manager.isPunished(targett.getUniqueId().toString()))
		    {
		        _manager.removePunishment(targett.getUniqueId().toString(), "Mute"); 
		        player.closeInventory();
		    }
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }

	    if (i.getType() == Material.HOPPER)
	    {
		try {
		    if(_manager.isGBanned(targett.getUniqueId().toString()))
		    {
		        _manager.removePunishment(targett.getUniqueId().toString(), "GBan"); 
		        player.closeInventory();
		    }
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }

	    if (i.getType() == Material.DIAMOND_SWORD)
	    {
		try {
		    if(_manager.isBanned(targett.getUniqueId().toString()))
		    {
		        _manager.removePunishment(targett.getUniqueId().toString(), "Ban");
		        player.closeInventory();
		    }
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }

	    if (i.getType() == Material.PAPER) {
		try {
		    _manager.addWarning(PunishCommand.target.getUniqueId().toString(), player.getName(), reason);
		    UtilServer.broadcastPunishment(targett, reason, true);
		    
		    if(targett.isOnline())
		    {
			Player ot = Bukkit.getPlayer(targett.getUniqueId());
			ot.sendMessage(base+ChatColor.RED+"You were warned for: "+reason+". Please note that continuation of this behavior could result in punishment.");
		    }
		    player.closeInventory();
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }

	    if (i.getType() == Material.BOOK_AND_QUILL) {
		try {
		    _manager.addPermPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(), reason,
			    "Mute");
		    UtilServer.broadcastPunishment(targett, reason, false);
		    player.closeInventory();
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }

	    if (i.getType() == Material.REDSTONE_BLOCK) {
		try {
		    _manager.addPermPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(), reason,
			    "Ban");
		    if (targett.isOnline()) {
			Player ot = Bukkit.getPlayer(targett.getUniqueId());
			ot.kickPlayer(base + "You were banned for \"" + reason + "\" for Permanent.");
		    }
		    UtilServer.broadcastPunishment(targett, reason, false);
		    player.closeInventory();
		} catch (Exception e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }

	    if (i.getType() == Material.INK_SACK) {
		Dye dye = (Dye) i.getData();

		if (dye.getColor() == DyeColor.LIME) {

		    if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 1 Mute"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.mute.sev1"), "Mute", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    } else if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 1 Gameplay"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.gameplay.sev1"), "GBan", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			   
			    if (targett.isOnline()) {
				Player ot = Bukkit.getPlayer(targett.getUniqueId());
				ot.kickPlayer(base + "You were banned for \"" + reason + "\" for "
					+ UtilTime.convert(Main.getConfiguration().getInt("punish.gameplay.sev1")));
			    }
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    } else if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 1 Hacking"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.hacking.sev1"), "Ban", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    if (targett.isOnline()) {
				Player ot = Bukkit.getPlayer(targett.getUniqueId());
				ot.kickPlayer(base + "You were banned for \"" + reason + "\" for "
					+ UtilTime.convert(Main.getConfiguration().getInt("punish.hacking.sev1")));
			    }
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		}
		if (dye.getColor() == DyeColor.YELLOW) {
		    if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 2 Mute"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.mute.sev2"), "Mute", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    } else if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 2 Gameplay"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.gameplay.sev2"), "GBan", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    if (targett.isOnline()) {
				Player ot = Bukkit.getPlayer(targett.getUniqueId());
				ot.kickPlayer(base + "You were banned for \"" + reason + "\" for "
					+ UtilTime.convert(Main.getConfiguration().getInt("punish.gameplay.sev2")));
			    }
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    } else if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 2 Hacking"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.hacking.sev2"), "GBan", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    if (targett.isOnline()) {
				Player ot = Bukkit.getPlayer(targett.getUniqueId());
				ot.kickPlayer(base + "You were banned for \"" + reason + "\" for "
					+ UtilTime.convert(Main.getConfiguration().getInt("punish.hacking.sev2")));
			    }
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    }
		}
		if (dye.getColor() == DyeColor.RED) {
		    if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 3 Mute"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.mute.sev3"), "Mute", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    } else if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 3 Gameplay"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.gameplay.sev3"), "GBan", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    if (targett.isOnline()) {
				Player ot = Bukkit.getPlayer(targett.getUniqueId());
				ot.kickPlayer(base + "You were banned for \"" + reason + "\" for "
					+ UtilTime.convert(Main.getConfiguration().getInt("punish.gameplay.sev3")));
			    }
			    player.closeInventory();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
		    } else if (i.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Severity 3 Hacking"))) {
			try {
			    _manager.addPunishment(PunishCommand.target.getUniqueId().toString(), player.getName(),
				    Main.getConfiguration().getInt("punish.hacking.sev3"), "Ban", reason);
			    UtilServer.broadcastPunishment(targett, reason, false);
			    if (targett.isOnline()) {
				Player ot = Bukkit.getPlayer(targett.getUniqueId());
				ot.kickPlayer(base + "You were banned for \"" + reason + "\" for "
					+ UtilTime.convert(Main.getConfiguration().getInt("punish.hacking.sev3")));
			    }
			    player.closeInventory();
			} catch (Exception e1) {

			}
		    }
		}
	    }
	} else if (e.getClickedInventory().getName()
		.equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Previous Punishments")) {


	    e.setCancelled(true);
	    
	    if (e.getCurrentItem().getType() == Material.BARRIER) {
		PunishmentGUI.openInventory((Player) e.getWhoClicked(), PunishCommand.target, PunishCommand.reason);
	    }

	}
    }

}
