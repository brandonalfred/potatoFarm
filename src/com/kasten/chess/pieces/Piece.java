package com.kasten.chess.pieces;

import java.util.List;

public interface Piece {
    String getType();
    List getAvailableMoves();
    boolean isAlive();
    void kill();

}
