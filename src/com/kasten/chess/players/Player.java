package com.kasten.chess.players;

// we should make Player an interface
// then have a human and a robot class that implement the interface

import com.kasten.chess.pieces.Piece;

import java.util.ArrayList;

public interface Player {
    String getType();
    int getID();
    ArrayList<Piece> getPieces();
    boolean isActive();
    ArrayList<Piece> generateNewPieces();
}
