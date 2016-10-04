package com.tapthis.dao;

import java.time.LocalDate;

public class ReviewInfo {
	
	private LocalDate currentDate = LocalDate.now();
	private int reviewID = 0;
	private int beerID = 0;
	private int userID = 0;
	private int overallRating = 0;
	private int hopsRating = 0;
	private int maltRating = 0;
	private String reviewComment = null;
	private String reviewAddedDate = currentDate.toString();
	
	public String getReviewAddedDate() {
		return reviewAddedDate;
	}

	public void setReviewAddedDate(String reviewAddedDate) {
		this.reviewAddedDate = reviewAddedDate;
	}

	public ReviewInfo() {
		super();
	}

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public int getBeerID() {
		return beerID;
	}

	public void setBeerID(int beerID) {
		this.beerID = beerID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public int getHopsRating() {
		return hopsRating;
	}

	public void setHopsRating(int hopsRating) {
		this.hopsRating = hopsRating;
	}

	public int getMaltRating() {
		return maltRating;
	}

	public void setMaltRating(int maltRating) {
		this.maltRating = maltRating;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
}
