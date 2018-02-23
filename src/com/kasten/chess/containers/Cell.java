package com.kasten.chess.containers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Color.WHITE;

public class Cell extends JButton {
    private int row;
    private int col;

    public Cell(ActionListener container) {
        addActionListener(container);
        setSize(40, 40);
        setOpaque(true);
        setBorderPainted(false);
    }

    public void setColor(Color color) {
        setBackground(color);
    }

    public void setCellState(String cellState) {
        if (!cellState.equals("-")) {
            JLabel label = new JLabel(cellState);
            label.setForeground(WHITE);
            add(label);
        }
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        setActionCommand(new Integer(row).toString() + new Integer(col).toString());
    }
}
