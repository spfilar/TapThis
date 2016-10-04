package com.tapthis.dao;

import java.sql.*;
import java.util.ArrayList;

public class DAO {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";
	
	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET_BEER = null;
	static ResultSet RES_SET_BREWERY = null;
	static ResultSet RES_SET_USER = null;
	static ResultSet RES_SET_REVIEW = null;
	
	public static final ArrayList<BeerInfo> myBeers = new ArrayList<>();
	public static final ArrayList<BreweryInfo> myBreweries = new ArrayList<>();
	public static final ArrayList<UserInfo> myUsers = new ArrayList<>();
	public static final ArrayList<ReviewInfo> myReviews = new ArrayList<>();
	
	public static void connectToSQL() {
		
		try {
			
			Class.forName(JDBC_DRIVER);
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	} //connectToDB method

	public static void readFromBeerTable() {
		
		connectToSQL();
		
		try {
			STMT = CONN.createStatement();
			RES_SET_BEER = STMT.executeQuery("SELECT * FROM greg_and_the_beards.beer_table;");
			
			
			while (RES_SET_BEER.next()) {
				
				BeerInfo beerInDB = new BeerInfo();
				
				beerInDB.setBeerID(RES_SET_BEER.getInt("bid"));
				beerInDB.setBeerName(RES_SET_BEER.getString("beer_name"));
				beerInDB.setBreweryName(RES_SET_BEER.getString("brewery_name"));
				beerInDB.setBeerDescription(RES_SET_BEER.getString("beer_description"));
				beerInDB.setBeerStyle(RES_SET_BEER.getString("beer_style"));
				beerInDB.setBeerABV(RES_SET_BEER.getFloat("beer_abv"));
				beerInDB.setHaveHad(RES_SET_BEER.getInt("have_had"));
				beerInDB.setWishList(RES_SET_BEER.getInt("wish_list"));
				beerInDB.setBeerAddedDate(RES_SET_BEER.getString("created_at"));
				
				myBeers.add(beerInDB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //readFromBeerTable 
	
	public static void readFromBreweryTable() {
		
		try {
			STMT = CONN.createStatement();
			RES_SET_BREWERY = STMT.executeQuery("SELECT * FROM greg_and_the_beards.brewery_table;");
			
			while (RES_SET_BREWERY.next()) {
				
				BreweryInfo breweryInDB = new BreweryInfo();
				
				breweryInDB.setBreweryID(RES_SET_BREWERY.getInt("brewery_id"));
				breweryInDB.setBreweryName(RES_SET_BREWERY.getString("brewery_id"));
				breweryInDB.setBreweryStreetAddress(RES_SET_BREWERY.getString("street_address"));
				breweryInDB.setBreweryCity(RES_SET_BREWERY.getString("brewery_city"));
				breweryInDB.setBreweryState(RES_SET_BREWERY.getString("brewery_state"));
				breweryInDB.setBreweryCountry(RES_SET_BREWERY.getString("country_name"));
				breweryInDB.setBreweryActive(RES_SET_BREWERY.getInt("brewery_active"));
				
				myBreweries.add(breweryInDB);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //readFromBreweryTable method
	
	public static void readFromUserTable() {
		
		connectToSQL();
		
		try {
			STMT = CONN.createStatement();
			RES_SET_USER = STMT.executeQuery("SELECT * FROM greg_and_the_beards.user_table;");
			
			while (RES_SET_USER.next()) {
				
				UserInfo userInDB = new UserInfo();
				
				userInDB.setUserID(RES_SET_USER.getInt("idusers"));
				userInDB.setUserName(RES_SET_USER.getString("userName"));
				userInDB.setFirstName(RES_SET_USER.getString("firstName"));
				userInDB.setLastName(RES_SET_USER.getString("lastName"));
				userInDB.setEmail(RES_SET_USER.getString("email"));
				userInDB.setPhoneNumber(RES_SET_USER.getString("phone"));
				userInDB.setDateOfBirth(RES_SET_USER.getString("date_of_birth"));
				userInDB.setPassword(RES_SET_USER.getString("password"));
				
				myUsers.add(userInDB); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //readFromUserTable method
	
	public static void readFromReviewTable() {
		
		connectToSQL();
		
		try {
			STMT = CONN.createStatement();
			RES_SET_REVIEW = STMT.executeQuery("SELECT * FROM greg_and_the_beards.beer_review");
			
			while (RES_SET_REVIEW.next()) {
				
				ReviewInfo reviewInDB = new ReviewInfo();
				
				reviewInDB.setUserID(RES_SET_REVIEW.getInt("id_review"));
				reviewInDB.setBeerID(RES_SET_REVIEW.getInt("beer_id"));
				reviewInDB.setUserID(RES_SET_REVIEW.getInt("user_id"));
				reviewInDB.setOverallRating(RES_SET_REVIEW.getInt("beer_rating_overall_quality"));
				reviewInDB.setHopsRating(RES_SET_REVIEW.getInt("hops_rating"));
				reviewInDB.setMaltRating(RES_SET_REVIEW.getInt("malt_rating"));
				reviewInDB.setReviewComment(RES_SET_REVIEW.getString("review_comment"));
				reviewInDB.setReviewAddedDate(RES_SET_REVIEW.getString("review_date_time_added"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //readFromReviewTable
	
	public static void writeToBeerTable(BeerInfo beer) {
		
		try {
			connectToSQL();
						
			PREP_STMT = CONN.prepareStatement("INSERT INTO `greg_and_the_beards`.`beer_table`"
					+ "(`beer_name`, `brewery_name`, `beer_description`, `beer_abv`, `beer_style`,"
					+ " `have_had`, `wish_list`, `created_at`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
			PREP_STMT.setString(1, beer.getBeerName());
			PREP_STMT.setString(2, beer.getBreweryName());
			PREP_STMT.setString(3, beer.getBeerDescription());
			PREP_STMT.setFloat(4, beer.getBeerABV());
			PREP_STMT.setString(5, beer.getBeerStyle());
			PREP_STMT.setInt(6, beer.getHaveHad());
			PREP_STMT.setInt(7, beer.getWishList());
			PREP_STMT.setString(8, beer.getBeerAddedDate());
			
			PREP_STMT.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //writeToBeerTable method
	
	public static void writeToBreweryTable(BreweryInfo brewery) {
		
		try {
			connectToSQL();
			
			PREP_STMT = CONN.prepareStatement("INSERT INTO `greg_and_the_beards`.`brewery_table`"
					+ "(`brewery_name`, `street_address`, `brewery_city`, `brewery_state`, `country_name`,"
					+ " `brewery_active`) VALUES (?, ?, ?, ?, ?, ?)");
			
			PREP_STMT.setString(1, brewery.getBreweryName());
			PREP_STMT.setString(2, brewery.getBreweryStreetAddress());
			PREP_STMT.setString(3, brewery.getBreweryCity());
			PREP_STMT.setString(4, brewery.getBreweryState());
			PREP_STMT.setString(5, brewery.getBreweryCountry());
			PREP_STMT.setInt(6, brewery.getBreweryActive());
			
			PREP_STMT.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //writeToBreweryTable method
	
	public static void writeToUserTable(UserInfo user) {
		
		try {
			connectToSQL();
			
			PREP_STMT = CONN.prepareStatement("INSERT INTO `greg_and_the_beards`.`users`"
					+ "(`userName`, `firstName`, `lastName`, `email`, `phone`, `date_of_birth`,"
					+ " `password`) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			PREP_STMT.setString(1, user.getUserName());
			PREP_STMT.setString(2, user.getFirstName());
			PREP_STMT.setString(3, user.getLastName());
			PREP_STMT.setString(4, user.getEmail());
			PREP_STMT.setString(5, user.getPhoneNumber());
			PREP_STMT.setString(6, user.getDateOfBirth());
			PREP_STMT.setString(7, user.getPassword());
			
			PREP_STMT.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //writeToUserTable method
	
	public static void writeToReviewTable(ReviewInfo review) {
		
		try {
			connectToSQL();
			
			PREP_STMT = CONN.prepareStatement("INSERT INTO `greg_and_the_beards`.`beer_review`"
					+ "(`beer_id`, `user_id`, `beer_rating_overall_quality`, `hops_rating`, `malt_rating`,"
					+ " `review_comment`, `review_date_time_added`) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			PREP_STMT.setInt(1, review.getBeerID());
			PREP_STMT.setInt(2, review.getUserID());
			PREP_STMT.setInt(3, review.getOverallRating());
			PREP_STMT.setInt(4, review.getHopsRating());
			PREP_STMT.setInt(5, review.getMaltRating());
			PREP_STMT.setString(6, review.getReviewComment());
			PREP_STMT.setString(7, review.getReviewAddedDate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	} //writeToReviewTable
	
	public static void updateUserFromTable(UserInfo user) {
		
		try {
			connectToSQL();
			
			PREP_STMT = CONN.prepareStatement("UPDATE `greg_and_the_beards`.`users` SET "
					+ "`userName` = ?, `firstName` = ?, `lastName` = ?, `email` = ?, `phone` = ?, `date_of_birth` = ?,"
					+ " `password` = ?");
			
			PREP_STMT.setString(1, user.getUserName());
			PREP_STMT.setString(2, user.getFirstName());
			PREP_STMT.setString(3, user.getLastName());
			PREP_STMT.setString(4, user.getEmail());
			PREP_STMT.setString(5, user.getPhoneNumber());
			PREP_STMT.setString(5, user.getDateOfBirth());
			PREP_STMT.setString(7, user.getPassword());
			
			PREP_STMT.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //updateUserTable method
	
	public static void updateReviewFromTable(ReviewInfo review) {
		
		try {
			connectToSQL();
			
			PREP_STMT = CONN.prepareStatement("UPDATE `greg_and_the_beards`.`beer_review` SET "
					+ "`beer_rating_overall_quality` = ?, `hops_rating` = ?, `malt_rating` = ?, `review_comment` = ?");
			
			PREP_STMT.setInt(1, review.getOverallRating());
			PREP_STMT.setInt(2, review.getHopsRating());
			PREP_STMT.setInt(3, review.getMaltRating());
			PREP_STMT.setString(4, review.getReviewComment());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} //updateReviewTable method
	
	public static void deleteUserFromTable(UserInfo user) {
		
		try {
			connectToSQL();
			
			PREP_STMT = CONN.prepareStatement("DELETE FROM `greg_and_the_beards`.`users`"
					+ "WHERE `userName` = ?");
			PREP_STMT.setString(1, user.getUserName());
			PREP_STMT.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //deleteUserFromTable method
	
	public static void deleteReviewFromTable(ReviewInfo review) {
		
		try {
			connectToSQL();
			
			PREP_STMT = CONN.prepareStatement("DELETE FROM `greg_and_the_beards`.`beer_review`"
						+ "WHERE `id_review` = ?");
			PREP_STMT.setInt(1, review.getReviewID());
			PREP_STMT.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //deleteReviewFromTable method
}
