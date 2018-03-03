package com.griffin.chess.pieces;

import java.util.ArrayList;
import java.util.Observer;

public interface Piece extends Observer {
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
