package org.jbltd.punish.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class UtilServer {


    private static String base = ChatColor.BLUE+""+ChatColor.BOLD+"Punish "+ChatColor.GRAY+""+ChatColor.BOLD+"> ";
    
    public static void broadcastPunishment(OfflinePlayer punished, String reason, boolean warning) {

	if(punished.isOnline())
	{
	    Player o = Bukkit.getPlayer(punished.getUniqueId());
	    
	    if(!warning)
		{
		    o.sendMessage(base+ChatColor.RED+"You were punished for: "+reason);
		}
		
		else {
		    o.sendMessage(base+ChatColor.RED+"You were warned for: "+reason+". Please note that continuation of this behavior could result in punishment.");
		}
	}
	
	
	
	for (Player player : Bukkit.getOnlinePlayers()) {
	    if (!player.hasPermission("advancedpunishgui.notify")) {
		continue;
	    }

	    else {
		
		player.sendMessage(base + punished.getName()+" was punished for "+reason+".");
		
	    }

	}

    }

}
