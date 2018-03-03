package com.griffin.chess.pieces;

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
        checkSingleMove(availableMoves, goFrontRight(1));
        checkSingleMove(availableMoves, goFrontLeft(1));
        checkSingleMove(availableMoves, goBackRight(1));
        checkSingleMove(availableMoves, goBackLeft(1));
        checkSingleMove(availableMoves, goForward(1));
        checkSingleMove(availableMoves, goBackward(1));
        checkSingleMove(availableMoves, goRight(1));
        checkSingleMove(availableMoves, goLeft(1));
        return availableMoves;
    }
}
