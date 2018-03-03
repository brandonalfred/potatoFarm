package com.griffin.chess.pieces;

import java.util.ArrayList;

public class Knight extends aPiece {
    public Knight(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        System.out.printf("creating knight for player %d at %d, %d\n", ownerID, row, col);
    }

    @Override
    public String getType() {
        return "♘";
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();
        checkSingleMove(availableMoves, goKnightUpRight(1));
        checkSingleMove(availableMoves, goKnightUpLeft(1));
        checkSingleMove(availableMoves, goKnightDownRight(1));
        checkSingleMove(availableMoves, goKnightDownLeft(1));

        checkSingleMove(availableMoves, goKnightRightUp(1));
        checkSingleMove(availableMoves, goKnightLeftUp(1));
        checkSingleMove(availableMoves, goKnightRightDown(1));
        checkSingleMove(availableMoves, goKnightLeftDown(1));
        return availableMoves;
    }
}
