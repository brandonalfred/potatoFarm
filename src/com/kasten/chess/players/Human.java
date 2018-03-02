package com.kasten.chess.players;

import com.kasten.chess.pieces.Pawn;
import com.kasten.chess.pieces.Rook;
import com.kasten.chess.pieces.Knight;
import com.kasten.chess.pieces.King;
import com.kasten.chess.pieces.Queen;
import com.kasten.chess.pieces.Bishop;
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
    public ArrayList<Piece> generateNewPieces() {
        // how should we do the pieces?
        // factory pattern?
        ArrayList<Piece> newPieces = new ArrayList<>();
        int startRow;
        int pieceID = 0;

        if (playerID == 1) {
            startRow = 1;
        } else {
            startRow = 6;
        }

        for (int i=0; i<8; i++) {
            newPieces.add(new Pawn(playerID, pieceID, startRow, i));
            // this is using hardcoded test values <- don't leave these!
            pieceID++;
        }
        newPieces.add(new Rook(playerID, pieceID, 7, 0));
        pieceID++;
        newPieces.add(new Knight(playerID, pieceID, 7, 1));
        pieceID++;
        newPieces.add(new Bishop(playerID, pieceID, 7, 2));
        pieceID++;
        newPieces.add(new Queen(playerID, pieceID, 7, 3));
        pieceID++;
        newPieces.add(new King(playerID, pieceID, 7, 4 ));
        pieceID++;
        newPieces.add(new Bishop(playerID, pieceID, 7, 5));
        pieceID++;
        newPieces.add(new Knight(playerID, pieceID, 7, 6));
        pieceID++;
        newPieces.add(new Rook(playerID, pieceID, 7, 7));
        
        return newPieces;
    }
}
