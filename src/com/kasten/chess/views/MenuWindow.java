package com.kasten.chess.views;

import com.kasten.chess.containers.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.awt.Color.*;

// should we make a window interface?? idk if its worth it for only two...

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
        optionsButton.setBounds(20, 380, 100, 40);
        optionsButton.addActionListener(this);
        applyButtonTheme(optionsButton);
        add(optionsButton);
        startButton = new JButton("Start");
        startButton.setBounds(280, 380, 100, 40);
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
