package com.kasten.chess.views;

import com.kasten.chess.containers.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.awt.Color.black;
import static java.awt.Color.darkGray;
import static java.awt.Color.lightGray;

public abstract class Window extends JFrame implements ActionListener {
    protected GUI myGUI;
    protected HashMap<String, String> state;
    protected Color bgColor;
    protected Color textColor;

    public Window(GUI container, HashMap<String, String> state) {
        myGUI = container;
        this.state = state;
        String theme = state.get("theme");
        applyTheme(theme);
        getContentPane().setBackground(bgColor);
        getContentPane().setForeground(textColor);

        /* Window Format/Size/Position settings */
        setSize(400, 500);
        setLocation(450, 100);
        applyTheme(theme);

        /* Layout/Visibility Window Options */
        setLayout(null);
        setResizable(false);
    }

    protected void applyButtonTheme(JButton button) {
        if (bgColor == darkGray) {
            button.setBackground(bgColor);
            button.setForeground(textColor);
            button.setOpaque(true);
            button.setBorderPainted(false);
        }
    }

    protected void applyTheme(String theme) {
        if (theme.equals("night")) {
            bgColor = darkGray;
            textColor = lightGray;
        } else {
            bgColor = lightGray;
            textColor = black;
        }
    }

    public abstract void actionPerformed(ActionEvent e);
}
