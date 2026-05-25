package com.hms.gui;

import com.hms.doa.ReservationDoa;
import com.hms.model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditReservationFrame extends JFrame {

    private JTextField txtReservationId;
    private JTextField txtGuestId;
    private JTextField txtRoomId;
    private JTextField txtCheckInDate;
    private JTextField txtCheckOutDate;
    private JButton btnSave;
    private int reservationId;

    private ReservationDoa reservationDoa = new ReservationDoa();

    public EditReservationFrame(int reservationId) {
        this.reservationId = reservationId;
        setTitle("Edit Reservation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Reservation ID:"));
        txtReservationId = new JTextField();
        txtReservationId.setEditable(false);
        add(txtReservationId);

        add(new JLabel("Guest ID:"));
        txtGuestId = new JTextField();
        add(txtGuestId);

        add(new JLabel("Room ID:"));
        txtRoomId = new JTextField();
        add(txtRoomId);

        add(new JLabel("Check-In Date (yyyy-mm-dd):"));
        txtCheckInDate = new JTextField();
        add(txtCheckInDate);

        add(new JLabel("Check-Out Date (yyyy-mm-dd):"));
        txtCheckOutDate = new JTextField();
        add(txtCheckOutDate);

        btnSave = new JButton("Save Changes");
        add(btnSave);

        loadReservationData();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveReservation();
            }
        });
    }

    private void loadReservationData() {
        try {
            Reservation reservation = reservationDoa.getReservation(reservationId);
            if (reservation != null) {
                txtReservationId.setText(String.valueOf(reservation.getReservationId()));
                txtGuestId.setText(String.valueOf(reservation.getGuestId()));
                txtRoomId.setText(String.valueOf(reservation.getRoomId()));
                txtCheckInDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(reservation.getCheckInDate()));
                txtCheckOutDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(reservation.getCheckOutDate()));
            } else {
                JOptionPane.showMessageDialog(this, "Reservation not found.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading reservation data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveReservation() {
        try {
            int guestId = Integer.parseInt(txtGuestId.getText());
            int roomId = Integer.parseInt(txtRoomId.getText());
            Date checkInDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(txtCheckInDate.getText()).getTime());
            Date checkOutDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(txtCheckOutDate.getText()).getTime());

            Reservation reservation = new Reservation(reservationId, guestId, roomId, checkInDate, checkOutDate);
            reservationDoa.updateReservation(reservation);
            JOptionPane.showMessageDialog(this, "Reservation updated successfully!");
            dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating reservation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
