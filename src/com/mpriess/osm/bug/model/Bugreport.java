package com.mpriess.osm.bug.model;

public class Bugreport {
	
	private long id;
	private double lon;
	private double lat;
	private String timestamp;
	private String user;
	private String bugDescription;
	
	public Bugreport(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getBugDescription() {
		return bugDescription;
	}
	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}
	
	@Override
	public String toString() {
		return "ID:" + id + " Timestamp:" + timestamp + " User:" + user + " Bug:" + bugDescription;
	}
}
