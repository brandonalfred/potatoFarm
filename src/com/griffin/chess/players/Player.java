package com.griffin.chess.players;

// we should make Player an interface
// then have a human and a robot class that implement the interface

import com.griffin.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Observer;

public interface Player extends Observer {
    String getType();
    int getID();
    ArrayList<Piece> getPieces();
    ArrayList<Piece> generateNewPieces();
    public void takeAITurn();
}
