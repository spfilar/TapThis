package com.tapthis.dao;

public class BreweryInfo {

	private int breweryID = 0;
	private int breweryActive = 0;
	private String breweryName = null;
	private String breweryStreetAddress = null;
	private String breweryCity = null;
	private String breweryState = null;
	private String breweryCountry = null;

	public BreweryInfo() {
		super();
	}

	public int getBreweryID() {
		return breweryID;
	}

	public void setBreweryID(int breweryID) {
		this.breweryID = breweryID;
	}

	public int getBreweryActive() {
		return breweryActive;
	}

	public void setBreweryActive(int breweryActive) {
		this.breweryActive = breweryActive;
	}

	public String getBreweryName() {
		return breweryName;
	}

	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}

	public String getBreweryStreetAddress() {
		return breweryStreetAddress;
	}

	public void setBreweryStreetAddress(String breweryStreetAddress) {
		this.breweryStreetAddress = breweryStreetAddress;
	}

	public String getBreweryCity() {
		return breweryCity;
	}

	public void setBreweryCity(String breweryCity) {
		this.breweryCity = breweryCity;
	}

	public String getBreweryState() {
		return breweryState;
	}

	public void setBreweryState(String breweryState) {
		this.breweryState = breweryState;
	}

	public String getBreweryCountry() {
		return breweryCountry;
	}

	public void setBreweryCountry(String breweryCountry) {
		this.breweryCountry = breweryCountry;
	}
}
