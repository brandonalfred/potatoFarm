package com.kasten.chess.views;

import com.kasten.chess.containers.Board;
import com.kasten.chess.containers.GUI;
import com.kasten.chess.pieces.Piece;
import com.kasten.chess.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import static com.sun.glass.ui.Cursor.setVisible;
import static java.awt.Color.*;

public class BoardWindow extends Window implements Observer {
    private HashMap<String, String> gameState;
    private ArrayList<ArrayList<String>> boardState;
    private JPanel boardUI;
    private Color darkColor;
    private Color lightColor;
    private Color selectedColor;
    private JButton takeTurnButton;
    private JButton quitButton;
    // there should be a Board object here

    public BoardWindow(GUI container, HashMap<String, String> state) {
        super(container, state);
        setTitle("Griffin Chess");
        //this.boardState = boardState;
        //boardState = generateBlankBoard();
        applyBoardTheme();
        //displayBoard();

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

    private ArrayList<ArrayList<String>> generateBlankBoard() {
        boardState = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            boardState.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                boardState.get(row).add("-");
                // for now... `-` indicates a blank space
                // this isn't finished at all yet.. still in debugging stage
            }
        }
        return boardState;
    }

    private void displayBoard() {
        GridLayout gridLayout = new GridLayout(8,8);
        boardUI = new JPanel(gridLayout);
        int boardMargin = 20;
        int boardSize = 360;
        boardUI.setBounds(boardMargin, boardMargin, boardSize, boardSize);
        darkColor = BLACK;
        lightColor = RED;
        selectedColor = GREEN;
        applyBoardTheme();
        add(boardUI);

        // debugging this
        int selectedRow = Integer.parseInt(gameState.get("selectedCell").substring(0,1));
        int selectedCol = Integer.parseInt(gameState.get("selectedCell").substring(1));
        System.out.printf("selected cell is %d, %d\n", selectedRow, selectedCol);

        for (int row=0;row < 8; row++) {
            for (int col=0; col < 8; col++) {
                JButton cell = new JButton();
                cell.setSize(40, 40);
                if (row % 2 == 0 && col % 2 == 0) cell.setBackground(darkColor);
                else if (row % 2 == 1 && col % 2 == 1) cell.setBackground(darkColor);
                else cell.setBackground(lightColor);
                cell.setOpaque(true);
                cell.setBorderPainted(false);

                if (row == selectedRow && col == selectedCol) cell.setBackground(selectedColor);

                // this is how the clicked cell tells the board which
                String coords = new Integer(row).toString() + new Integer(col).toString();
                cell.setActionCommand(coords);



                // adding text for now to represent board state for each square
                if (!boardState.get(row).get(col).equals("-")) {
                    JLabel label = new JLabel(boardState.get(row).get(col));
                    label.setForeground(WHITE);
                    cell.add(label);
                }

                cell.addActionListener(this);
                boardUI.add(cell);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String buttonType = e.getActionCommand();
        //setVisible(false);
        switch (buttonType) {
            case "Quit":
                setVisible(false);
                myGUI.setView("main");
                break;
            case "Confirm":
                System.out.println("Taking Turn...");
                break;
            default:
                System.out.println(buttonType);
                myGUI.setSelected(buttonType);
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("receiving update from board...");
        gameState = ( HashMap<String, String> ) arg;
        Board myBoard = ( Board ) o;
        boardState = myBoard.getBoard();
        displayBoard();
    }
}
