package com.kasten.chess.pieces;

import java.util.ArrayList;

public class Pawn extends aPiece {
    public Pawn(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        System.out.printf("creating pawn for player %d at %d, %d\n", ownerID, row, col);
    }

    @Override
    public String getType() {
        return "pawn";
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();

        if (row == getHomeRow()) {
            for (int i = 1; i <= 2; i++) {
                availableMoves.add(goForward(i));
            }
        } else {
            availableMoves.add(goForward(1));
        }
        // check left diag


        // check right diag
        return availableMoves;
    }
}
