package com.kasten.chess.pieces;

import java.util.ArrayList;

public class Rook extends aPiece {
    public Rook(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        System.out.printf("creating rook for player %d at %d, %d\n", ownerID, row, col);
    }

    @Override
    public String getType() {
        return "rook";
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            availableMoves.add(goForward(i));
            availableMoves.add(goBackward(i));
            availableMoves.add(goRight(i));
            availableMoves.add(goLeft(i));
            
        }

        return availableMoves;
    }
}
