package com.kasten.chess.pieces;

import java.util.*;

public abstract class aPiece implements Piece {
    protected int owner;
    protected int ID;
    protected int row;
    protected int col;
    protected boolean isAlive;
    protected ArrayList<ArrayList<String>> boardState;

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
            return inBoundsMove(distance, 0, -1);
        else
            return inBoundsMove(distance, 0, 1);
    }
    public ArrayList<Integer> goBackward(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 0, 1);
        else
            return inBoundsMove(distance, 0, -1);
    }
    public ArrayList<Integer> goLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, 0);
        else
            return inBoundsMove(distance, 1, 0);
    }
    public ArrayList<Integer> goRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, 0);
        else
            return inBoundsMove(distance, -1, 0);
    }
    public ArrayList<Integer> goFrontLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, -1);
        else
            return inBoundsMove(distance, 1, 1);
    }
    public ArrayList<Integer> goFrontRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, -1);
        else
            return inBoundsMove(distance, -1, 1);
    }
    public ArrayList<Integer> goBackLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, 1);
        else
            return inBoundsMove(distance, 1, -1);
    }
    public ArrayList<Integer> goBackRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, 1);
        else
            return inBoundsMove(distance, -1, -1);
    }
    
    //Movement for the Knight
    public ArrayList<Integer> goKnightDownRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, 2);
        else
            return inBoundsMove(distance, -1, -2);
    }
    public ArrayList<Integer> goKnightDownLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, 2);
        else
            return inBoundsMove(distance, 1, -2);
    }
    public ArrayList<Integer> goKnightUpRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, -2);
        else
            return inBoundsMove(distance, -1, 2);
    }
    public ArrayList<Integer> goKnightUpLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, -2);
        else
            return inBoundsMove(distance, 1, 2);
    }
    public ArrayList<Integer> goKnightRightDown(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 2, 1);
        else
            return inBoundsMove(distance, -2, -1);
    }
    public ArrayList<Integer> goKnightLeftDown(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -2, 1);
        else
            return inBoundsMove(distance, 2, -1);
    }
    public ArrayList<Integer> goKnightRightUp(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 2, -1);
        else
            return inBoundsMove(distance, -2, 1);
    }
    public ArrayList<Integer> goKnightLeftUp(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -2, -1);
        else
            return inBoundsMove(distance, 2, 1);
    }
    //Yeah, it's a fuckton of them.

    public ArrayList<Integer> inBoundsMove(int distance, int dirX, int dirY) {
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
        return move <= 7 && move >= 0;
    }

    public abstract ArrayList<ArrayList<Integer>> getAvailableMoves();

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    // **new** methods for collision/capturing
    public boolean pieceFound(ArrayList<Integer> newMove) {
        String cell = getCellState(newMove);
        if (isOccupied(cell)) return true;
        else return false;
    }

    public void checkSingleMove(ArrayList<ArrayList<Integer>> movesList, ArrayList<Integer> newMove) {
        movesList.add(newMove);
        String cell = getCellState(newMove);
        if (isOccupied(cell) && isOwnPiece(cell))
            movesList.remove(movesList.size() - 1);
    }

    public String getCellState(List<Integer> position) {
        return boardState.get(position.get(0)).get(position.get(1));
    }

    public boolean isOccupied(String cellState) {
        if (!cellState.substring(0,1).equals("-"))
            return true;
        else
            return false;
    }

    public boolean isOwnPiece(String cellState) {
        if (cellState.length() >= 4)
            if (Integer.parseInt(cellState.substring(0,1)) == getOwner())
                return true;
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.boardState = ( ArrayList<ArrayList<String>> ) arg;
    }
}
