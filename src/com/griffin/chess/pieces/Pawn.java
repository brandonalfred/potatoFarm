package com.griffin.chess.pieces;

import java.util.ArrayList;

public class Pawn extends aPiece {
    public Pawn(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        System.out.printf("creating pawn for player %d at %d, %d\n", ownerID, row, col);
    }

    public String getType() {
        return "♙";
    }

    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        String cell;
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();

        if (row == getHomeRow()) {
            for (int i = 1; i <= 2; i++) {
                checkSingleMove(availableMoves, goForward(i));
                if (pieceFound(goForward(i))) {
                    if (!availableMoves.isEmpty())
                        availableMoves.remove(availableMoves.size()-1);
                    break;
                }
            }
        } else if (!pieceFound(goForward(1))) {
            availableMoves.add(goForward(1));
        }

        // these should be conditional
        // they should only show as valid IF there's a piece available for capture

        // check left diag
        cell = getCellState(goFrontLeft(1));
        if (isOccupied(cell) && !isOwnPiece(cell))
            availableMoves.add(goFrontLeft(1));

        // check right diag
        cell = getCellState(goFrontRight(1));
        if (isOccupied(cell) && !isOwnPiece(cell))
            availableMoves.add(goFrontRight(1));
        return availableMoves;
    }
}
