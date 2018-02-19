package com.kasten.chess.views;

import com.kasten.chess.containers.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.awt.Color.*;

// should we make a window interface?? idk if its worth it for only two...

public class MenuWindow extends JFrame implements ActionListener {
    private GUI myGUI;
    private JButton startButton;
    private JButton optionsButton;
    private Color bgColor;
    private Color textColor;

    public MenuWindow(GUI container) {
        myGUI = container;
        HashMap<String, String> options = myGUI.getState();
        String theme = options.get("theme");
        System.out.println("loading with " + theme);
        applyTheme(theme);
        getContentPane().setBackground(bgColor);
        getContentPane().setForeground(textColor);

        /* Window Format/Size/Position settings */
        setTitle("Waterfall Chess");
        setSize(400, 500);
        setLocation(450, 100);
        applyTheme(theme);

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

        /* Layout/Visibility Window Options */
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

    private void applyButtonTheme(JButton button) {
        if (bgColor == darkGray) {
            button.setBackground(bgColor);
            button.setForeground(textColor);
            button.setOpaque(true);
            button.setBorderPainted(false);
        }
    }

    private void applyTheme(String theme) {
        if (theme.equals("night")) {
            bgColor = darkGray;
            textColor = lightGray;
        } else {
            bgColor = lightGray;
            textColor = black;
        }
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
