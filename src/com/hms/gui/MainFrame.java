package com.hms.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JButton btnAddHotel;
    private JButton btnAddRoom;
    private JButton btnAddGuest;
    private JButton btnAddReservation;
    private JButton btnRetrieveData;

    public MainFrame() {
        setTitle("Hotel Reservation System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        btnAddHotel = new JButton("Add Hotel");
        btnAddRoom = new JButton("Add Room");
        btnAddGuest = new JButton("Add Guest");
        btnAddReservation = new JButton("Add Reservation");
        btnRetrieveData = new JButton("Retrieve Data");

        add(btnAddHotel);
        add(btnAddRoom);
        add(btnAddGuest);
        add(btnAddReservation);
        add(btnRetrieveData);

        btnRetrieveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RetrieveDataFrame().setVisible(true);
            }
        });

        btnAddHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddHotelFrame().setVisible(true);
            }
        });

        btnAddRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRoomFrame().setVisible(true);
            }
        });

        btnAddGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddGuestFrame().setVisible(true);
            }
        });

        btnAddReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddReservationFrame().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
