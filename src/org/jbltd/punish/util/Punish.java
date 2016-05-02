package org.jbltd.punish.util;

public class Punish {

    private String uuid;
    private String punisherName;
    private String date;
    private int duration;
    private String reason;
    private String type;

    public Punish(String uuid, String punisherName, String date, int duration, String reason, String type) {
	this.uuid = uuid;
	this.punisherName = punisherName;
	this.date = date;
	this.duration = duration;
	this.reason = reason;
	this.type = type;
    }

    public String getUUID() {
	return uuid;
    }

    public String punisherName() {
	return punisherName;
    }

    public String getDate() {
	return date;
    }

    public int getDuration() {
	return duration;
    }

    public String getReason() {
	return reason;
    }

    public String getPunishType() {
	return type;
    }

}
