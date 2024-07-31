package com.hms.gui;

import com.hms.doa.GuestDoa;
import com.hms.model.Guest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditGuestFrame extends JFrame {

    private JTextField txtGuestId;
    private JTextField txtGuestName;
    private JTextField txtGuestEmail;
    private JTextField txtGuestPhoneNumber;
    private JButton btnSave;
    private int guestId;

    private GuestDoa guestDoa = new GuestDoa();

    public EditGuestFrame(int guestId) {
        this.guestId = guestId;
        setTitle("Edit Guest");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Guest ID:"));
        txtGuestId = new JTextField();
        txtGuestId.setEditable(false);
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

        btnSave = new JButton("Save Changes");
        add(btnSave);

        loadGuestData();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGuest();
            }
        });
    }

    private void loadGuestData() {
        try {
            Guest guest = guestDoa.getGuest(guestId);
            if (guest != null) {
                txtGuestId.setText(String.valueOf(guest.getGuestId()));
                txtGuestName.setText(guest.getGuestName());
                txtGuestEmail.setText(guest.getGuestEmail());
                txtGuestPhoneNumber.setText(String.valueOf(guest.getGuestPhoneNumber()));
            } else {
                JOptionPane.showMessageDialog(this, "Guest not found.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading guest data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveGuest() {
        try {
            String guestName = txtGuestName.getText();
            String guestEmail = txtGuestEmail.getText();
            long guestPhoneNumber = Long.parseLong(txtGuestPhoneNumber.getText());

            Guest guest = new Guest(guestId, guestName, guestEmail, guestPhoneNumber);
            guestDoa.updateGuest(guest);
            JOptionPane.showMessageDialog(this, "Guest updated successfully!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid phone number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating guest: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
