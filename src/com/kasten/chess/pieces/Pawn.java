package com.kasten.chess.pieces;

import java.util.ArrayList;
import java.util.Observable;

public class Pawn extends Observable implements Piece {
    private int owner;
    private int ID;
    private int row;
    private int col;
    private boolean isAlive;

    public Pawn(int ownerID, int pieceID, int startRow, int startCol) {
        owner = ownerID;
        ID = pieceID;
        row = startRow;
        col = startCol;
        isAlive = true;
        //System.out.printf("creating pawn for player %d at %d, %d\n", ownerID, row, column);
    }

    @Override
    public int getID() {
        return ID;
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
    public int getCol() {
        return col;
    }

    @Override
    public void movePiece(int newRow, int newCol) {
        row = newRow;
        col = newCol;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();
        ArrayList<Integer> newMove;

        // check for starting position
        if (owner == 0 && row == 6) {
            for (int newRow = (row-1); newRow >= (row-2); newRow--) {
                newMove = new ArrayList<>();
                newMove.add(newRow);
                newMove.add(col);
                availableMoves.add(newMove);
            }
        } else {
            // normally pawns only move one space
            newMove = new ArrayList<>();
            newMove.add(row-1);
            newMove.add(col);
            availableMoves.add(newMove);
        }

        // we could add other potential moves if there's a capturable piece, etc
        return availableMoves;
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
