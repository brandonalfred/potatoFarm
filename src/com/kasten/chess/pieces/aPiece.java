package com.kasten.chess.pieces;

import java.util.ArrayList;

public abstract class aPiece implements Piece {
    protected int owner;
    protected int ID;
    protected int row;
    protected int col;
    protected boolean isAlive;

    public aPiece(int ownerID, int pieceID, int startRow, int startCol) {
        owner = ownerID;
        ID = pieceID;
        row = startRow;
        col = startCol;
        isAlive = true;
    }

    public abstract String getType();

    public int getID() {
        return ID;
    }

    public int getOwner() {
        return owner;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getHomeRow() {
        if (owner == 0) return 6;
        else return 1;
    }

    public void movePiece(int newRow, int newCol) {
        row = newRow;
        col = newCol;
    }

    public ArrayList<Integer> goForward(int distance) {
        int direction;
        ArrayList<Integer> newMove = new ArrayList<>();
        if (owner == 0) direction = -1;
        else direction = 1;
        int newRow = row + (direction * distance);
        if (newRow > 7 || newRow < 0) newMove.add(row);
        else newMove.add(newRow);
        newMove.add(col);
        return newMove;
    }

    public abstract ArrayList<ArrayList<Integer>> getAvailableMoves();

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }
}
