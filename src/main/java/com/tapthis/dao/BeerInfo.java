package com.tapthis.dao;

import java.time.LocalDate;

public class BeerInfo {
	
	private LocalDate currentDate = LocalDate.now();
	private int beerID = 0;
	private int haveHad = 0;
	private int wishList = 0;
	private float beerABV = 0.0f;
	private String beerName = null;
	private String breweryName = null;
	private String beerDescription = null;
	private String beerStyle = null;
	private String beerAddedDate = currentDate.toString();
	
	public BeerInfo() {
		super();
	}

	public int getBeerID() {
		return beerID;
	}

	public void setBeerID(int beerID) {
		this.beerID = beerID;
	}
	
	public String getBreweryName() {
		return breweryName;
	}

	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}

	public int getHaveHad() {
		return haveHad;
	}

	public void setHaveHad(int haveHad) {
		this.haveHad = haveHad;
	}

	public int getWishList() {
		return wishList;
	}

	public void setWishList(int wishList) {
		this.wishList = wishList;
	}

	public String getBeerAddedDate() {
		return beerAddedDate;
	}

	public void setBeerAddedDate(String beerAddedDate) {
		this.beerAddedDate = beerAddedDate;
	}

	public float getBeerABV() {
		return beerABV;
	}

	public void setBeerABV(float beerABV) {
		this.beerABV = beerABV;
	}

	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}

	public String getBeerDescription() {
		return beerDescription;
	}

	public void setBeerDescription(String beerDescription) {
		this.beerDescription = beerDescription;
	}

	public String getBeerStyle() {
		return beerStyle;
	}

	public void setBeerStyle(String beerStyle) {
		this.beerStyle = beerStyle;
	}
}
