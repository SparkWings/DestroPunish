package org.jbltd.punish.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatHandler implements Listener{
    
    private DatabaseManager _manager;
    
    private String base = ChatColor.BLUE+""+ChatColor.BOLD+"Punish "+ChatColor.GRAY+""+ChatColor.BOLD+"> ";
    
    public ChatHandler(DatabaseManager manager)
    {
	this._manager = manager;
    }
    
    
    @EventHandler
    public void handle(AsyncPlayerChatEvent e)
    {
	
	Player player = e.getPlayer();
	String uuid = player.getUniqueId().toString();
	
	try {
	    if(_manager.isPunished(uuid))
	    {
	        
	        e.setCancelled(true);
	        player.sendMessage(base+ChatColor.RED+"You are unable to speak because you were muted on "+_manager.getPunishmentDate(uuid)+" for \""+_manager.getPunishmentReason(uuid)+"\" for "+
	        UtilTime.convert(_manager.getPunishmentDuration(uuid)));
	        
	    }
	} catch (Exception e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	
	
    }

}
