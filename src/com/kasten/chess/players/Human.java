package com.kasten.chess.players;

import com.kasten.chess.pieces.*;

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
        PieceFactory pf = new PieceFactory();
        ArrayList<Piece> newPieces = new ArrayList<>();
        int firstRow;
        int secondRow;
        int pieceID = 0;

        if (playerID == 1) {
            firstRow = 1;
            secondRow = 0;
        } else {
            firstRow = 6;
            secondRow = 7;
        }

        // first row
        for (int i=0; i<8; i++) {
            newPieces.add(pf.createPiece(playerID, pieceID, firstRow, i));
            pieceID++;
        }
        // second row
        for (int i=0; i<8;i++) {
            newPieces.add(pf.createPiece(playerID, pieceID, secondRow, i));
            pieceID++;
        }
        return newPieces;
    }
}
