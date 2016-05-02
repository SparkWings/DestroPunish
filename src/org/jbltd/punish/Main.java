package org.jbltd.punish;

import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.punish.command.PunishCommand;
import org.jbltd.punish.util.ChatHandler;
import org.jbltd.punish.util.DatabaseManager;
import org.jbltd.punish.util.LoginHandler;
import org.jbltd.punish.util.PunishmentGUI;

public class Main extends JavaPlugin {

    public static FileConfiguration config;

    public DatabaseManager MySQL = new DatabaseManager(this);

    public void onEnable() {
	config = getConfig();

	try
	{
		this.MySQL.setupDB();
		getLogger().info("[Punish] Connected to database");
	}
	catch (ClassNotFoundException | SQLException e)
	{
		getLogger().info("Unable to connect to MySQL database.");
		e.printStackTrace();
	}
	
	config.addDefault("punish.mute.sev1", 2);
	config.addDefault("punish.mute.sev2", 24);
	config.addDefault("punish.mute.sev3", 72);

	config.addDefault("punish.gameplay.sev1", 24);
	config.addDefault("punish.gameplay.sev2", 168);
	config.addDefault("punish.gameplay.sev3", 720);

	config.addDefault("punish.hack.sev1", 24);
	config.addDefault("punish.hack.sev2", 168);
	config.addDefault("punish.hack.sev3", 720);

	config.addDefault("db.host", "76.72.175.56");
	config.addDefault("db.dbname", "mcd_190");
	config.addDefault("db.user", "mcd_190");
	config.addDefault("db.password", "d191c7c12a");

	config.options().copyDefaults(true);
	saveConfig();

	getServer().getPluginManager().registerEvents(new PunishmentGUI(MySQL), this);
	getServer().getPluginManager().registerEvents(new ChatHandler(MySQL), this);
	getServer().getPluginManager().registerEvents(new LoginHandler(MySQL), this);
	getCommand("punish").setExecutor(new PunishCommand());
	
	System.out.println("[Punish] Enabled");

    }

    public void onDisable() {

	saveDefaultConfig();

    }

    public static FileConfiguration getConfiguration() {
	return config;
    }

}
