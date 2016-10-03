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
	
	public static final ArrayList<BeerInfo> myBeers = new ArrayList<>();
	public static final ArrayList<BreweryInfo> myBreweries = new ArrayList<>();
	public static final ArrayList<UserInfo> myUsers = new ArrayList<>();
	
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
	} //readFromBeerTable method
	
	public static void readFromUserTable() {
		
		connectToSQL();
		
		try {
			STMT = CONN.createStatement();
		//	RES_SET = STMT.executeQuery(SELECT statement from database);
			
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
	}
}
