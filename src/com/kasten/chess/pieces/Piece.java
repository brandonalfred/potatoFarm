package com.kasten.chess.pieces;

import java.util.ArrayList;

public interface Piece {
    String getType();
    int getID();
    int getOwner();
    int getRow();
    int getCol();
    ArrayList<ArrayList<Integer>> getAvailableMoves();
    void movePiece(int row, int col);
    boolean isAlive();
    void kill();
}
