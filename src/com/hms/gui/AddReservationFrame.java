package com.hms.gui;

import com.hms.doa.ReservationDoa;
import com.hms.model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddReservationFrame extends JFrame {

    private JTextField txtReservationId;
    private JTextField txtGuestId;
    private JTextField txtRoomId;
    private JTextField txtCheckInDate;
    private JTextField txtCheckOutDate;
    private JButton btnAdd;

    private ReservationDoa reservationDoa = new ReservationDoa();

    public AddReservationFrame() {
        setTitle("Add Reservation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Reservation ID:"));
        txtReservationId = new JTextField();
        add(txtReservationId);

        add(new JLabel("Guest ID:"));
        txtGuestId = new JTextField();
        add(txtGuestId);

        add(new JLabel("Room ID:"));
        txtRoomId = new JTextField();
        add(txtRoomId);

        add(new JLabel("Check-In Date (yyyy-MM-dd):"));
        txtCheckInDate = new JTextField();
        add(txtCheckInDate);

        add(new JLabel("Check-Out Date (yyyy-MM-dd):"));
        txtCheckOutDate = new JTextField();
        add(txtCheckOutDate);

        btnAdd = new JButton("Add Reservation");
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation();
            }
        });
    }

    private void addReservation() {
        try {
            int reservationId = Integer.parseInt(txtReservationId.getText());
            int guestId = Integer.parseInt(txtGuestId.getText());
            int roomId = Integer.parseInt(txtRoomId.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = dateFormat.parse(txtCheckInDate.getText());
            Date checkOutDate = dateFormat.parse(txtCheckOutDate.getText());
            
            Reservation reservation = new Reservation(reservationId, guestId, roomId, checkInDate, checkOutDate);
            reservationDoa.addReservation(reservation);
            JOptionPane.showMessageDialog(this, "Reservation added successfully!");
            dispose();
        } catch (SQLException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Error adding reservation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid format for numeric fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
