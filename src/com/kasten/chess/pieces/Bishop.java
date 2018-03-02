package com.kasten.chess.pieces;

import java.util.ArrayList;

public class Bishop extends aPiece {
    public Bishop(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        System.out.printf("creating bishop for player %d at %d, %d\n", ownerID, row, col);
    }

    @Override
    public String getType() {
        return "bishop";
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            availableMoves.add(goFrontRight(i));
            availableMoves.add(goFrontLeft(i));
            availableMoves.add(goRightBackward(i));
            availableMoves.add(goLeftBackward(i));
        }

        return availableMoves;
    }
}
