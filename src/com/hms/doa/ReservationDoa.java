package com.hms.doa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hms.db.DatabaseConnector;
import com.hms.model.Reservation;

public class ReservationDoa {

	Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt =null;

	public void addReservation(Reservation reservation) throws SQLException {

		String query = "INSERT INTO reservations (guestId, roomId, checkInDate, checkOutDate) VALUES (?, ?, ?, ?)";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reservation.getGuestId());
			pstmt.setInt(2, reservation.getRoomId());
			pstmt.setDate(3, new Date(reservation.getCheckInDate().getDate()));
			pstmt.setDate(4, new Date(reservation.getCheckOutDate().getDate()));
			pstmt.executeUpdate(); 
		}finally {
			con.close();
			pstmt.close();
		}
	}

	public Reservation getReservation(int reservationId) throws SQLException {
		String query = "SELECT * FROM reservations WHERE reservationId=?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, reservationId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Reservation(rs.getInt("reservaionId"), rs.getInt("guestId"), rs.getInt("roomId"), rs.getDate("checkInDate"), rs.getDate("checkOutDate"));
			}
		}finally {
			con.close();
			pstmt.close();
		}
		return null;
	}

	public List<Reservation> getAllReservations() throws SQLException {
		String query = "SELECT * FROM reservations";
		List<Reservation> reservations = new ArrayList<>();
		try {
			con = DatabaseConnector.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query); 
			while (rs.next()) {
				reservations.add(new Reservation(rs.getInt("reservationId"), rs.getInt("guestId"), 
						rs.getInt("roomId"), rs.getDate("checkInDate"), rs.getDate("checkOutDate")));
			}
		}finally {
			con.close();
			stmt.close();
		}
		return reservations;
	}

	public void updateReservation(Reservation reservation) throws SQLException {
		String query = "UPDATE reservations SET guestId = ?, roomId = ?, checkInDate = ?, checkOutDate = ? WHERE reservationId = ?";
		try (Connection conn = DatabaseConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, reservation.getGuestId());
			pstmt.setInt(2, reservation.getRoomId());
			pstmt.setDate(3, new Date(reservation.getCheckInDate(). getDate()));
			pstmt.setDate(4, new Date(reservation.getCheckOutDate().getDate()));
			pstmt.setInt(5, reservation.getReservationId());
			pstmt.executeUpdate();
		}
	}

	public void deleteReservation(int reservationId) throws SQLException {
		String query = "DELETE FROM reservations WHERE reservationId = ?";
		try (Connection conn = DatabaseConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, reservationId);
			pstmt.executeUpdate();
		}
	}
}
