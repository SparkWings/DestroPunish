package org.jbltd.punish.util;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class LoginHandler implements Listener{

    private DatabaseManager _manager;

    private String base = ChatColor.BLUE+""+ChatColor.BOLD+"Punish "+ChatColor.GRAY+""+ChatColor.BOLD+"> ";
    
    public LoginHandler(DatabaseManager manager)
    {
	
	this._manager = manager;
	
    }
    
    
    @EventHandler
    public void handle(AsyncPlayerPreLoginEvent e)
    {
	
	String uuid = e.getUniqueId().toString();
	
	try {
	    if(_manager.isBanned(uuid))
	    {
	        e.disallow(Result.KICK_OTHER, base+ChatColor.RED+""+ChatColor.BOLD+"You were banned on "+_manager.getBanDate(uuid)+" for "+_manager.getBanReason(uuid)+" for "+UtilTime.convert(_manager.getBanDuration(uuid)));
	    }
	    if(_manager.isGBanned(uuid))
	    {
	        e.disallow(Result.KICK_OTHER, base+ChatColor.RED+""+ChatColor.BOLD+"You were banned on "+_manager.getGBanDate(uuid)+" for "+_manager.getGBanReason(uuid)+" for "+UtilTime.convert(_manager.getGBanDuration(uuid)));
	    }
	} catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	
    }
    
}
