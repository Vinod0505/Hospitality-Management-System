package com.hms.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hms.db.DatabaseConnector;
import com.hms.model.Guest;

public class GuestDoa {

	Connection con = null;
	PreparedStatement pstmt=null;
	Statement stmt=null;

	public void addGuest(Guest guest) throws SQLException {
		String query = "INSERT INTO guests (guestId,guestName, guestEmail, guestPhoneNumber) VALUES (?,?, ?, ?)";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, guest.getGuestId());
			pstmt.setString(2, guest.getGuestName());
			pstmt.setString(3, guest.getGuestEmail());
			pstmt.setLong(4, guest.getGuestPhoneNumber());
			pstmt.executeUpdate();
		}finally {
			con.close();
			pstmt.close();
		}
	}

	public Guest getGuest(int guestId) throws SQLException {
		String query = "SELECT * FROM guests WHERE guestId = ?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, guestId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Guest(rs.getInt("guestId"), rs.getString("guestName"),
						rs.getString("guestEmail"), rs.getLong("guestPhoneNumber"));
			}
		}finally {
			con.close();
			pstmt.close();
		}
		return null;
	}

	public List<Guest> getAllGuests() throws SQLException {
		String query = "SELECT * FROM guests";
		List<Guest> guests = new ArrayList<>();
		try {
			con = DatabaseConnector.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				guests.add(new Guest(rs.getInt("guestId"), rs.getString("guestName"),
						rs.getString("guestEmail"), rs.getLong("guestPhoneNumber")));
			}
		}finally {
			con.close();
			stmt.close();
		}
		return guests;
	}

	public void updateGuest(Guest guest) throws SQLException {
		String query = "UPDATE guests SET guestName = ?, guestEmail = ?, guestPhoneNumber = ? WHERE guestId = ?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, guest.getGuestName());
			pstmt.setString(2, guest.getGuestEmail());
			pstmt.setLong(3, guest.getGuestPhoneNumber());
			pstmt.setInt(4, guest.getGuestId());
			pstmt.executeUpdate();
		}finally {
			con.close();
			pstmt.close();
		}
	}

	public void deleteGuest(int guestId) throws SQLException {
		String query = "DELETE FROM guests WHERE guestId = ?";
		try (Connection conn = DatabaseConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, guestId);
			pstmt.executeUpdate();
		}
	}
}
