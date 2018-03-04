package com.griffin.chess.players;

import com.griffin.chess.pieces.Piece;
import com.griffin.chess.pieces.PieceFactory;

import java.util.ArrayList;
import java.util.Observable;

public abstract class aPlayer implements Player {
    protected int playerID;
    protected ArrayList<Piece> pieces;

    public aPlayer(int ID) {
        playerID = ID;
        pieces = generateNewPieces();
    }

    public abstract String getType();

    public int getID() { return playerID; }

    public ArrayList<Piece> getPieces() { return pieces; }

    public ArrayList<Piece> getPiecesWithMoves() {
        ArrayList<Piece> pieceList = new ArrayList<>();
        for (Piece piece : pieces) {
            if (!piece.getAvailableMoves().isEmpty())
                pieceList.add(piece);
        }
        return pieceList;
    }

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

    public void takeAITurn() { }

    public void update(Observable o, Object arg) { }
}
