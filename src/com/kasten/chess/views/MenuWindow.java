package com.kasten.chess.views;

import com.kasten.chess.containers.GUI;

import javax.swing.*;

public class MenuWindow extends JFrame {
    private GUI myGUI;
    private JButton startButton;
    private JButton optionsButton;

    public MenuWindow(GUI container) {
        myGUI = container;

        /* Window Size/Position settings */
        this.setTitle("Waterfall Chess");
        this.setSize(400, 500);
        this.setLocation(450, 100);

        /* 'Start' and 'Options' Buttons */
        optionsButton = new JButton("Options");
        optionsButton.setBounds(20, 380, 75, 60);
        optionsButton.addActionListener(e -> handleOptionsPress());
        this.add(optionsButton);
        startButton = new JButton("Start");
        startButton.setBounds(305, 380, 75, 60);
        startButton.addActionListener(e -> handleStartPress());
        this.add(startButton);
        this.getRootPane().setDefaultButton(startButton);
        startButton.requestFocus();

        /* Layout/Visibility Window Options */
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    /* Button Handlers */
    private void handleOptionsPress() {
        System.out.println("Options Pressed.");
        this.setVisible(false);
        myGUI.updateView("options");
    }

    private void handleStartPress() {
        System.out.println("Start Pressed.");
        this.setVisible(false);
        myGUI.updateView("board");
    }
}
