package com.kasten.chess.pieces;

import java.util.ArrayList;

public class King extends aPiece {
    public King(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        System.out.printf("creating king for player %d at %d, %d\n", ownerID, row, col);
    }

    @Override
    public String getType() {
        return "♔";
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();


            availableMoves.add(goFrontRight(1));
            availableMoves.add(goFrontLeft(1));
            availableMoves.add(goRightBackward(1));
            availableMoves.add(goLeftBackward(1));
            availableMoves.add(goForward(1));
            availableMoves.add(goBackward(1));
            availableMoves.add(goRight(1));
            availableMoves.add(goLeft(1));

        return availableMoves;
    }
}
