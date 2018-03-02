package com.kasten.chess.pieces;

import java.util.ArrayList;
import java.util.Collection;

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
        if (owner == 0)
            return checkNewMove(distance, 0, -1);
        else
            return checkNewMove(distance, 0, 1);
    }
    public ArrayList<Integer> goBackward(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 0, 1);
        else
            return checkNewMove(distance, 0, -1);
    }
    public ArrayList<Integer> goLeft(int distance) {
        if (owner == 0)
            return checkNewMove(distance, -1, 0);
        else
            return checkNewMove(distance, 1, 0);
    }
    public ArrayList<Integer> goRight(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 1, 0);
        else
            return checkNewMove(distance, -1, 0);
    }
    public ArrayList<Integer> goFrontLeft(int distance) {
        if (owner == 0)
            return checkNewMove(distance, -1, -1);
        else
            return checkNewMove(distance, 1, 1);
    }
    public ArrayList<Integer> goFrontRight(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 1, -1);
        else
            return checkNewMove(distance, -1, 1);
    }
    public ArrayList<Integer> goLeftBackward(int distance) {
        if (owner == 0)
            return checkNewMove(distance, -1, 1);
        else
            return checkNewMove(distance, 1, -1);
    }
    public ArrayList<Integer> goRightBackward(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 1, 1);
        else
            return checkNewMove(distance, -1, -1);
    }
    
    //Movement for the Knight
    public ArrayList<Integer> goKnightDownRight(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 1, 2);
        else
            return checkNewMove(distance, -1, -2);
    }
    public ArrayList<Integer> goKnightDownLeft(int distance) {
        if (owner == 0)
            return checkNewMove(distance, -1, 2);
        else
            return checkNewMove(distance, 1, -2);
    }
    public ArrayList<Integer> goKnightUpRight(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 1, -2);
        else
            return checkNewMove(distance, -1, 2);
    }
    public ArrayList<Integer> goKnightUpLeft(int distance) {
        if (owner == 0)
            return checkNewMove(distance, -1, -2);
        else
            return checkNewMove(distance, 1, 2);
    }
    
    public ArrayList<Integer> goKnightRightDown(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 2, 1);
        else
            return checkNewMove(distance, -2, -1);
    }
    public ArrayList<Integer> goKnightLeftDown(int distance) {
        if (owner == 0)
            return checkNewMove(distance, -2, 1);
        else
            return checkNewMove(distance, 2, -1);
    }
    public ArrayList<Integer> goKnightRightUp(int distance) {
        if (owner == 0)
            return checkNewMove(distance, 2, -1);
        else
            return checkNewMove(distance, -2, 1);
    }
    public ArrayList<Integer> goKnightLeftUp(int distance) {
        if (owner == 0)
            return checkNewMove(distance, -2, -1);
        else
            return checkNewMove(distance, 2, 1);
    }
    //Yeah, it's a fuckton of them.
    

    
    public ArrayList<Integer> checkNewMove(int distance, int dirX, int dirY) {
        ArrayList<Integer> newMove = new ArrayList<>();

        int newRow = row + (dirY * distance);
        int newCol = col + (dirX * distance);
        if (boundsCheck(newRow) && boundsCheck(newCol)) {
            newMove.add(newRow);
            newMove.add(newCol);
        }
        else {
            newMove.add(row);
            newMove.add(col);
        }
        return newMove;
    }

    public boolean boundsCheck(int move) {
        if (move > 7 || move < 0) return false;
        else return true;
    }

    public abstract ArrayList<ArrayList<Integer>> getAvailableMoves();

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }
}
