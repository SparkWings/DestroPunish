package org.jbltd.punish.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jbltd.punish.util.PunishmentGUI;


public class PunishCommand implements CommandExecutor {
    
    public static OfflinePlayer target;
    public static String reason = "";
    
    private String messageBase = ChatColor.GRAY + "[" + ChatColor.BLUE + "Punish" + ChatColor.GRAY + "] "
	    + ChatColor.RED + "";

    public PunishCommand()
    {
	
	
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player))
	    return true;

	Player player = (Player) sender;

	
	if(!player.hasPermission("advancedpunishgui.open"))
	{
	    player.sendMessage(ChatColor.RED+"You don't have permission to do this.");
	    return true;
	}
	
	if (args.length < 2) {
	    player.sendMessage(messageBase + "Not enough arguments! Please use /punish <player> <reason here>");
	    return true;
	}

	OfflinePlayer targett = Bukkit.getOfflinePlayer(args[0]);
	target = targett;
	
	if(!targett.hasPlayedBefore())
	{
	    player.sendMessage(messageBase+"That player has never played before!");
	    return true;
	}
	
	
	if (cmd.getName().equalsIgnoreCase("punish")) {

	    StringBuilder sb = new StringBuilder();
	    
	    for(int i = 1; i < args.length; i++)
	    {
		sb.append(args[i] + " ");
	    }
	    
	    String reasonn = sb.toString().trim();
	    reason = reasonn;
	    
	    PunishmentGUI.openInventory(player, target, reason);
	    
	}

	return false;

    }

}
