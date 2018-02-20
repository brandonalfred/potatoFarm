package com.kasten.chess.views;

import com.kasten.chess.containers.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.util.HashMap;

public class MenuWindow extends Window {
    private JButton startButton;
    private JButton optionsButton;

    public MenuWindow(GUI container, HashMap<String, String> state) {
        super(container, state);
        setTitle("Griffin Chess");

        /* Title */
        JLabel title = new JLabel("Griffin Chess");
        title.setFont(new Font("Courier", Font.BOLD, 32));
        title.setBounds(70,80,250,100);
        title.setForeground(textColor);
        add(title);

        /* 'Start' and 'Options' Buttons */
        optionsButton = new JButton("Options");
        optionsButton.setBounds(LEFT_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionsButton.addActionListener(this);
        applyButtonTheme(optionsButton);
        add(optionsButton);
        startButton = new JButton("Start");
        startButton.setBounds(RIGHT_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        startButton.addActionListener(this);
        applyButtonTheme(startButton);
        add(startButton);
        getRootPane().setDefaultButton(startButton);
        startButton.requestFocus();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String buttonType = e.getActionCommand();
        setVisible(false);
        switch (buttonType) {
            case "Start":
                myGUI.setView("board");
                break;
            case "Options":
                myGUI.setView("options");
                break;
        }
    }
}
