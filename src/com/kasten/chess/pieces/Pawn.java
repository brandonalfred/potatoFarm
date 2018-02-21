package com.kasten.chess.pieces;

import java.util.List;
import java.util.Observable;

public class Pawn extends Observable implements Piece {
    private int owner;
    private int row;
    private int column;
    private boolean isAlive;

    public Pawn(int ownerID, int startRow, int startCol) {
        // we don't want to call setPosition here...
        // that would cause too many update() calls
        // setting the position directly should fix that
        owner = ownerID;
        row = startRow;
        column = startCol;
        isAlive = true;
        System.out.printf("creating pawn for player %d at %d, %d\n", ownerID, row, column);
    }

    @Override
    public int getID() {
        return 0;
        // this doesn't work yet!!
        // do we even need it??
    }

    @Override
    public String getType() {
        return "pawn";
    }

    @Override
    public int getOwner() {
        return owner;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setPosition(int newRow, int newCol) {
        row = newRow;
        column = newCol;
    }

    @Override
    public List getAvailableMoves() {
        return null;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void kill() {
        isAlive = false;
    }
}
