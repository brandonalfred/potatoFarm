package com.kasten.chess.players;

import com.kasten.chess.pieces.Pawn;
import com.kasten.chess.pieces.Piece;

import java.util.ArrayList;

public class Human implements Player {
    private int playerID;
    private ArrayList<Piece> pieces;

    public Human(int ID) {
        playerID = ID;
        pieces = generateNewPieces();
    }

    @Override
    public String getType() {
        return "human";
    }

    @Override
    public int getID() {
        return playerID;
    }
    @Override
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public ArrayList<Piece> generateNewPieces() {
        // how should we do the pieces?
        // factory pattern?
        ArrayList<Piece> newPieces = new ArrayList<>();
        int pieceID = 0;
        for (int i=0; i<8; i++) {
            newPieces.add(new Pawn(playerID, pieceID,6, i));
            // this is using hardcoded test values <- don't leave these!
            pieceID++;
        }
        return newPieces;
    }
}
