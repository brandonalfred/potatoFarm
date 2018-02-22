package com.kasten.chess.views;

import com.kasten.chess.containers.Board;
import com.kasten.chess.containers.GUI;
import com.kasten.chess.pieces.Piece;
import com.kasten.chess.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.Color.*;

public class BoardWindow extends Window {
    private ArrayList<ArrayList<String>> boardState;
    private JPanel boardUI;
    private Color darkColor;
    private Color lightColor;
    private JButton takeTurnButton;
    private JButton quitButton;
    // there should be a Board object here

    public BoardWindow(GUI container, HashMap<String, String> state, ArrayList<ArrayList<String>> boardState) {
        super(container, state);
        setTitle("Griffin Chess");
        //this.boardState = boardState;
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
        // THIS METHOD SHOULD EVENTUALLY GO IN THE BOARD CLASS
        GridLayout gridLayout = new GridLayout(8,8);
        boardUI = new JPanel(gridLayout);
        int boardMargin = 20;
        int boardSize = 360;
        darkColor = BLACK;
        lightColor = RED;
        applyBoardTheme();
        boardUI.setBounds(boardMargin,boardMargin, boardSize, boardSize);
        add(boardUI);
        for (int row=0;row < 8; row++) {
            for (int col=0; col < 8; col++) {
                JPanel cell = new JPanel();
                cell.setSize(40, 40);
                if (row % 2 == 0 && col % 2 == 0) cell.setBackground(darkColor);
                else if (row % 2 == 1 && col % 2 == 1) cell.setBackground(darkColor);
                else cell.setBackground(lightColor);
/*
                for (Player player : board.getPlayers())
                    for (Piece piece : player.getPieces())
                        if (piece.getRow() == row && piece.getColumn() == col)
                            if (piece.isAlive()) {
                                JLabel label = new JLabel(piece.getType());
                                label.setForeground(WHITE);
                                cell.add(label);
                            }
*/


                // instead of looping over each cell... we should just loop through each players pieces
                // using the piece location to place it on the gui board
                // there doesn't really need to be a board-like representation on the back end...


                // adding text for now to represent board state for each square
                if (!boardState.get(row).get(col).equals("-")) {
                    JLabel label = new JLabel(boardState.get(row).get(col));
                    label.setForeground(WHITE);
                    cell.add(label);
                }

                boardUI.add(cell);
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
