package com.hms.model;

public class Guest {

	private int guestId;
	private String guestName;
	private String guestEmail;
	private String guestPhoneNumber;
	public Guest(int guestId, String guestName, String guestEmail, String guestPhoneNumber) {
		super();
		this.guestId = guestId;
		this.guestName = guestName;
		this.guestEmail = guestEmail;
		this.guestPhoneNumber = guestPhoneNumber;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	public String getGuestPhoneNumber() {
		return guestPhoneNumber;
	}
	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
	}


}
