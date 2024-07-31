package com.hms.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hms.db.DatabaseConnector;
import com.hms.model.Hotel;

public class HotelDoa {

	Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt =null;

	public void addHotel(Hotel hotel) throws SQLException {
		String query ="Insert into hotels (hotelId,hotelName,hotelLocation,hotelAmenities) values(?,?,?,?)";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hotel.getHotelId());
			pstmt.setString(2, hotel.getHotelName());
			pstmt.setString(3, hotel.getHotelLocation());
			pstmt.setString(4, hotel.getHotelAmenities());
			pstmt.executeUpdate();
		}finally {
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public Hotel getHotel(int hotelId) throws SQLException {

		String query = "Select * from hotels where hotelId =?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hotelId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Hotel(rs.getInt("hotelId"), rs.getString("hotelname"),
						rs.getString("hotelLocation"), rs.getString("hotelAmenities"));
			}
		}finally {
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return null;
	}

	public List<Hotel> getAllHotels() throws SQLException {
		String query = "SELECT * FROM hotels";
		List<Hotel> hotels = new ArrayList<>();
		try {
			con = DatabaseConnector.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			while (rs.next()) {
				hotels.add(new Hotel(rs.getInt("hotelId"), rs.getString("hotelName"), rs.getString("hotelLocation"), rs.getString("hotelAmenities")));

			}
		}finally {
			// Ensure pstmt and con are closed
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return hotels;
	}

	public void updateHotel(Hotel hotel) throws SQLException {
		String query = "UPDATE hotels SET hotelName = ?, hotelLocation = ?, hotelAmenities = ? WHERE hotelId = ?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, hotel.getHotelName());
			pstmt.setString(2, hotel.getHotelLocation());
			pstmt.setString(3, hotel.getHotelAmenities());
			pstmt.setInt(4, hotel.getHotelId());
			pstmt.executeUpdate();
		}finally {
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public void deleteHotel(int hotelId) throws SQLException {
		String query = "DELETE FROM hotels WHERE hotelId = ?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hotelId);
			pstmt.executeUpdate();
		}finally {
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

}
