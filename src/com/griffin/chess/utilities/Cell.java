package com.griffin.chess.utilities;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

public class Cell extends JButton {
    private Color darkColor;
    private Color lightColor;
    private Color selectedColor;
    private Color destColor;
    private Color targetColor;
    private Color battleColor;
    private int row;
    private int col;

    public Cell(ActionListener container) {
        addActionListener(container);
        setSize(40, 40);
        setOpaque(true);
        setContentAreaFilled(true);
        setBorder(new LineBorder(BLACK));
        row = 0;
        col = 0;
        battleColor = PINK;
        //darkColor = BLACK;
        //lightColor = RED;
        darkColor = new Color(139, 69, 19);
        lightColor = new Color(238, 232, 170);
        selectedColor = GREEN;
        destColor = YELLOW;
        targetColor = BLUE;
    }

    private void setCellColor(String theme, String cellState) {
        if (theme.equals("night")) {
            lightColor = GRAY;
            darkColor = BLACK;
        }

        String cellColor = cellState.substring(cellState.length()-1);
        if (cellColor.equals("~")) setBackground(selectedColor);
        else if (cellColor.equals(".")) setBackground(destColor);
        else if (cellColor.equals("?")) setBackground(targetColor);
        else if (cellColor.equals("x")) setBackground(battleColor);
        else if (row % 2 == col % 2) setBackground(darkColor);
        else setBackground(lightColor);
    }

    public void setCellGraphics(String theme, String cellState) {
        setCellColor(theme, cellState);

        if(cellState.substring(0,1).equals("-")) {
            // eventually text here will get replaced with an image
            //setText("");
            setIcon(null);
        } else {
            //setText(cellState.substring(1,2));
            //setForeground(WHITE);
            if(theme.equals("night")) {
                if(cellState != "-") {
                    // print pictures
                    switch ((cellState.substring(0,2))) {
                        case "1♙":
                            setIcon(new ImageIcon("img/chess-pieces/white-pawn.png"));
                            break;
                        case "1♗":
                            setIcon(new ImageIcon("img/chess-pieces/white-bishop.png"));
                            break;
                        case "1♔":
                            setIcon(new ImageIcon("img/chess-pieces/white-king.png"));
                            break;
                        case "1♘":
                            setIcon(new ImageIcon("img/chess-pieces/white-knight.png"));
                            break;
                        case "1♕":
                            setIcon(new ImageIcon("img/chess-pieces/white-queen.png"));
                            break;
                        case "1♖":
                            setIcon(new ImageIcon("img/chess-pieces/white-rook.png"));
                            break;
                        case "0♙":
                            //setIcon(new ImageIcon("img/chess-pieces/red-pawn.png"));
                            break;
                        case "0♗":
                            //setIcon(new ImageIcon("img/chess-pieces/red-bishop.png"));
                            break;
                        case "0♔":
                            //setIcon(new ImageIcon("img/chess-pieces/red-king.png"));
                            break;
                        case "0♘":
                            //setIcon(new ImageIcon("img/chess-pieces/red-knight.png"));
                            break;
                        case "0♕":
                            //setIcon(new ImageIcon("img/chess-pieces/red-queen.png"));
                            break;
                        case "0♖":
                            //setIcon(new ImageIcon("img/chess-pieces/red-rook.png"));
                            break;
                    }
                }
            }
            else {
                if(cellState != "-") {
                    // print pictures
                    switch ((cellState.substring(0,2))) {
                        case "1♙":
                            setIcon(new ImageIcon("img/chess-pieces/white-pawn.png"));
                            break;
                        case "1♗":
                            setIcon(new ImageIcon("img/chess-pieces/white-bishop.png"));
                            break;
                        case "1♔":
                            setIcon(new ImageIcon("img/chess-pieces/white-king.png"));
                            break;
                        case "1♘":
                            setIcon(new ImageIcon("img/chess-pieces/white-knight.png"));
                            break;
                        case "1♕":
                            setIcon(new ImageIcon("img/chess-pieces/white-queen.png"));
                            break;
                        case "1♖":
                            setIcon(new ImageIcon("img/chess-pieces/white-rook.png"));
                            break;
                        case "0♙":
                            setIcon(new ImageIcon("img/chess-pieces/black-pawn.png"));
                            break;
                        case "0♗":
                            setIcon(new ImageIcon("img/chess-pieces/black-bishop.png"));
                            break;
                        case "0♔":
                            setIcon(new ImageIcon("img/chess-pieces/black-king.png"));
                            break;
                        case "0♘":
                            setIcon(new ImageIcon("img/chess-pieces/black-knight.png"));
                            break;
                        case "0♕":
                            setIcon(new ImageIcon("img/chess-pieces/black-queen.png"));
                            break;
                        case "0♖":
                            setIcon(new ImageIcon("img/chess-pieces/black-rook.png"));
                            break;
                    }
                }
            }
        }
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        setActionCommand(Integer.toString(row) + Integer.toString(col));
    }
}

