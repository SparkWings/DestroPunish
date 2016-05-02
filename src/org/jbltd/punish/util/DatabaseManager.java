package org.jbltd.punish.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jbltd.punish.Main;

public class DatabaseManager {

    private JavaPlugin plugin;
    public MySQL DB;

    public DatabaseManager(JavaPlugin plugin) {
	this.plugin = plugin;
    }

    public void setupDB() throws SQLException, ClassNotFoundException {
	DB = new MySQL(plugin, Main.getConfiguration().getString("db.host"), "3306", Main.getConfiguration().getString("db.dbname"), Main.getConfiguration().getString("db.user"), Main.getConfiguration().getString("db.password"));
	DB.openConnection();

	Statement a = DB.getConnection().createStatement();
	a.executeUpdate(
		"CREATE TABLE IF NOT EXISTS `Punishments` (`id` INT NOT NULL AUTO_INCREMENT, `UUID` MEDIUMTEXT, `Punisher` MEDIUMTEXT, `Reason` MEDIUMTEXT, `Date` MEDIUMTEXT, `Time` INT, `Type` MEDIUMTEXT, PRIMARY KEY (`id`));");

	a.executeUpdate(
		"CREATE TABLE IF NOT EXISTS `PastPunishments` (`id` INT NOT NULL AUTO_INCREMENT, `UUID` MEDIUMTEXT, `Punisher` MEDIUMTEXT, `Reason` MEDIUMTEXT, `Date` MEDIUMTEXT, `Time` INT, `Type` MEDIUMTEXT, PRIMARY KEY (`id`));");

	a.executeUpdate("SET GLOBAL event_scheduler='ON'");
	
    }

    private String formatDate() {

	SimpleDateFormat sd = new SimpleDateFormat("EEE, MMM d");
	Date d = new Date();

	String s = sd.format(d);

	return s;

    }

    public void addPastPunishment(String uuid, String punisherName, int hourDuration, String punishType, String reason)
	    throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	s.executeUpdate("INSERT INTO `PastPunishments` (`UUID`,`Punisher`, `Reason`, `Date`,`Time`, `Type`) VALUES ('"
		+ uuid + "', '" + punisherName + "', '" + reason + "', '" + formatDate() + "', '" + hourDuration
		+ "', '" + punishType + "');");

	return;
    }

    public void addPunishment(String uuid, String punisherName, int hourDuration, String punishType, String reason)
	    throws Exception {

	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	s.executeUpdate("INSERT INTO `Punishments` (`UUID`,`Punisher`, `Reason`, `Date`,`Time`, `Type`) VALUES ('"
		+ uuid + "', '" + punisherName + "', '" + reason + "', '" + formatDate() + "', '" + hourDuration
		+ "', '" + punishType + "');");

	executeEvent(hourDuration, uuid, reason);
	addPastPunishment(uuid, punisherName, hourDuration, punishType, reason);

	return;

    }

    public void removePunishment(String uuid, String type) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	s.executeUpdate("DELETE FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='" + type + "';");
    }

    public void addPermPunishment(String uuid, String punisherName, String reason, String type) throws Exception {

	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	s.executeUpdate("INSERT INTO `Punishments` (`UUID`,`Punisher`, `Reason`, `Date`,`Time`, `Type`) VALUES ('"
		+ uuid + "', '" + punisherName + "', '" + reason + "', '" + formatDate() + "', '" + 9999 + "', '" + type
		+ "');");

	addPastPunishment(uuid, punisherName, 9999, type, reason);
	return;

    }

    public void addWarning(String uuid, String punisherName, String reason) throws Exception {

	addPastPunishment(uuid, punisherName, 0, "Warning", reason);
	return;

    }

    public void executeEvent(int duration, String uuid, String reason) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	s.executeUpdate("CREATE EVENT punishevent" + new Random().nextInt(1000)
		+ " ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL " + duration
		+ " HOUR DO DELETE FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Reason`='" + reason + "';");
    }

    public Punish getPunishment(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "';");

	if (rs.next()) {
	    Punish p = new Punish(uuid, rs.getString("Punisher"), rs.getString("Date"), rs.getInt("Time"),
		    rs.getString("Reason"), rs.getString("Type"));
	    return p;
	}

	return null;
    }

    public boolean isPunished(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='Mute';");

	if (!rs.next()) {
	    return false;
	} else {
	    return true;
	}

    }

    public boolean isBanned(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='Ban';");

	if (!rs.next()) {
	    return false;
	} else {
	    return true;
	}

    }

    public boolean isGBanned(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='GBan';");

	if (!rs.next()) {
	    return false;
	} else {
	    return true;
	}

    }

    public String getPunishmentReason(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "';");

	if (rs.next()) {
	    return rs.getString("Reason");
	} else {
	    return null;
	}
    }

    public int getPunishmentDuration(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "';");

	if (rs.next()) {
	    return rs.getInt("Time");
	} else {
	    return 0;
	}
    }

    public String getPunishmentDate(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "';");

	if (rs.next()) {
	    return rs.getString("Date");
	} else {
	    return "";
	}
    }

    public String getPunisher(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "';");

	if (rs.next()) {
	    return rs.getString("Punisher");
	} else {
	    return "";
	}
    }

    public String getBanReason(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='Ban';");

	if (rs.next()) {
	    return rs.getString("Reason");
	} else {
	    return null;
	}
    }

    public int getBanDuration(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='Ban';");

	if (rs.next()) {
	    return rs.getInt("Time");
	} else {
	    return 0;
	}
    }

    public String getBanDate(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='Ban';");

	if (rs.next()) {
	    return rs.getString("Date");
	} else {
	    return "";
	}
    }

    public String getBPunisher(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='Ban';");

	if (rs.next()) {
	    return rs.getString("Punisher");
	} else {
	    return "";
	}
    }

    public String getGBanReason(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='GBan';");

	if (rs.next()) {
	    return rs.getString("Reason");
	} else {
	    return null;
	}
    }

    public int getGBanDuration(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='GBan';");

	if (rs.next()) {
	    return rs.getInt("Time");
	} else {
	    return 0;
	}
    }

    public String getGBanDate(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='GBan';");

	if (rs.next()) {
	    return rs.getString("Date");
	} else {
	    return "";
	}
    }

    public String getGPunisher(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "' AND `Type`='GBan';");

	if (rs.next()) {
	    return rs.getString("Punisher");
	} else {
	    return "";
	}
    }

    public String getPunishType(String uuid) throws Exception {
	if (!DB.checkConnection()) {
	    DB.openConnection();
	}

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `Punishments` WHERE `UUID`='" + uuid + "';");

	if (rs.next()) {
	    return rs.getString("Type");
	} else {
	    return "";
	}
    }

    public ArrayList<Punish> getPastPunishments(OfflinePlayer player) throws Exception {
	ArrayList<Punish> punishments = new ArrayList<>();

	String uuid = player.getUniqueId().toString();

	Statement s = DB.getConnection().createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM `PastPunishments` WHERE `UUID`='" + uuid + "';");

	while (rs.next()) {
	    punishments.add(new Punish(uuid, rs.getString("Punisher"), rs.getString("Date"), rs.getInt("Time"),
		    rs.getString("Reason"), rs.getString("Type")));
	    continue;
	}

	return punishments;
    }

}
