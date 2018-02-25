package com.kasten.chess.pieces;

import java.util.ArrayList;

public interface Piece {
    int getID();
    String getType();
    int getOwner();
    int getRow();
    int getCol();
    void movePiece(int row, int col);
    ArrayList<ArrayList<Integer>> getAvailableMoves();
    boolean isAlive();
    void kill();


}
