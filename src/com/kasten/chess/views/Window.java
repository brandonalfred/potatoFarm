package com.kasten.chess.views;

import com.kasten.chess.utilities.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.awt.Color.black;
import static java.awt.Color.darkGray;
import static java.awt.Color.lightGray;

public abstract class Window extends JFrame implements ActionListener {
    protected static final int  BUTTON_Y = 400;
    protected static final int  LEFT_BUTTON_X = 20;
    protected static final int  RIGHT_BUTTON_X = 280;
    protected static final int  BUTTON_HEIGHT = 40;
    protected static final int  BUTTON_WIDTH = 100;

    protected GUI myGUI;
    protected HashMap<String, String> state;
    protected Color bgColor;
    protected Color textColor;

    public Window(GUI container, HashMap<String, String> state) {
        myGUI = container;
        this.state = state;
        applyTheme();
        getContentPane().setBackground(bgColor);
        getContentPane().setForeground(textColor);

        /* Window Format/Size/Position settings */
        setSize(400, 500);
        setLocation(450, 100);

        /* Layout/Visibility Window Options */
        setLayout(null);
        setResizable(false);
    }

    protected void applyButtonTheme(JButton button) {

        if (state.get("theme").equals("night")) {
            button.setBackground(bgColor);
            button.setForeground(textColor);
            button.setOpaque(true);
            button.setBorderPainted(false);
        }
    }

    protected void applyTheme() {
        if (state.get("theme").equals("night")) {
            bgColor = darkGray;
            textColor = lightGray;
        } else {
            bgColor = lightGray;
            textColor = black;
        }
    }

    public abstract void actionPerformed(ActionEvent e);
}
