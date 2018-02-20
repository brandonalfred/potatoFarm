package com.kasten.chess.views;

import com.kasten.chess.containers.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.Color.*;

public class BoardWindow extends Window {
    private ArrayList<ArrayList<String>> boardState;
    private Color darkColor;
    private Color lightColor;
    private JButton takeTurnButton;
    private JButton quitButton;

    public BoardWindow(GUI container, HashMap<String, String> state, ArrayList<ArrayList<String>> boardState) {
        super(container, state);
        setTitle("Griffin Chess");
        this.boardState = boardState;
        applyBoardTheme();
        constructBoard();

        /* 'Quit' and 'Take Turn' Buttons */
        quitButton = new JButton("Quit");
        quitButton.setBounds(LEFT_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.addActionListener(this);
        applyButtonTheme(quitButton);
        add(quitButton);
        takeTurnButton = new JButton("Confirm");
        takeTurnButton.setBounds(RIGHT_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        takeTurnButton.addActionListener(this);
        applyButtonTheme(takeTurnButton);
        add(takeTurnButton);

        setVisible(true);
    }

    private void applyBoardTheme() {
        if (state.get("theme").equals("night"))
            lightColor = GRAY;
        }

    private void constructBoard() {
        GridLayout gridLayout = new GridLayout(8,8);
        JPanel board = new JPanel(gridLayout);
        int boardMargin = 20;
        int boardSize = 360;
        darkColor = BLACK;
        lightColor = RED;
        applyBoardTheme();
        board.setBounds(boardMargin,boardMargin, boardSize, boardSize);
        add(board);
        for (int row=0;row < 8; row++) {
            for (int col=0; col < 8; col++) {
                JPanel cell = new JPanel();
                cell.setSize(40, 40);
                if (row % 2 == 0 && col % 2 == 0) cell.setBackground(darkColor);
                else if (row % 2 == 1 && col % 2 == 1) cell.setBackground(darkColor);
                else cell.setBackground(lightColor);

                // adding text for now to represent board state for each square
                if (boardState.get(row).get(col).equals("-")) {
                    JLabel label = new JLabel("-");
                    label.setForeground(WHITE);
                    cell.add(label);
                }

                board.add(cell);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String buttonType = e.getActionCommand();
        setVisible(false);
        switch (buttonType) {
            case "Quit":
                myGUI.setView("main");
                break;
            case "Confirm":
                System.out.println("Taking Turn...");
        }
    }
}
