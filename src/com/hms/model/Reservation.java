package com.hms.model;

import java.util.Date;
import java.time.LocalDateTime;

public class Reservation {

	private int reservationId;
	private int guestId;
	private int roomId;
	private Date checkInDate;
	private Date checkOutDate;

	public Reservation() {}

	public Reservation(int reservationId, int guestId, int roomId, java.util.Date checkInDate2,
			java.util.Date checkOutDate2) {
		super();
		this.reservationId = reservationId;
		this.guestId = guestId;
		this.roomId = roomId;
		this.checkInDate = checkInDate2;
		this.checkOutDate = checkOutDate2;
	}

	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", guestId=" + guestId + ", roomId=" + roomId
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + "]";
	}



}
