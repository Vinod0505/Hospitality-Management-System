package com.hms.gui;

import com.hms.doa.GuestDoa;
import com.hms.model.Guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddGuestFrame extends JFrame {

	private JTextField txtGuestId;
	private JTextField txtGuestName;
	private JTextField txtGuestEmail;
	private JTextField txtGuestPhoneNumber;
	private JButton btnAdd;

	private GuestDoa guestDoa = new GuestDoa();

	public AddGuestFrame() {
		setTitle("Add Guest");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(5, 2, 10, 10));

		add(new JLabel("Guest ID:"));
		txtGuestId = new JTextField();
		add(txtGuestId);

		add(new JLabel("Guest Name:"));
		txtGuestName = new JTextField();
		add(txtGuestName);

		add(new JLabel("Guest Email:"));
		txtGuestEmail = new JTextField();
		add(txtGuestEmail);

		add(new JLabel("Guest Phone Number:"));
		txtGuestPhoneNumber = new JTextField();
		add(txtGuestPhoneNumber);

		btnAdd = new JButton("Add Guest");
		add(btnAdd);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addGuest();
			}
		});
	}

	private void addGuest() {
		try {
			int guestId = Integer.parseInt(txtGuestId.getText());
			String guestName = txtGuestName.getText();
			String guestEmail = txtGuestEmail.getText();
			long guestPhoneNumber = Long.parseLong(txtGuestPhoneNumber.getText());

			Guest guest = new Guest(guestId, guestName, guestEmail, guestPhoneNumber);
			guestDoa.addGuest(guest);
			JOptionPane.showMessageDialog(this, "Guest added successfully!");
			dispose();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Error adding guest: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Invalid format for numeric fields.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
