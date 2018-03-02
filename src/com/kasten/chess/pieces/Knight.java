package src.com.kasten.chess.pieces;

import java.util.ArrayList;

public class Knight extends aPiece {
    public Knight(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        System.out.printf("creating knight for player %d at %d, %d\n", ownerID, row, col);
    }

    @Override
    public String getType() {
        return "knight";
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();

        
          availableMoves.add(goKnightUpRight(1));
          availableMoves.add(goKnightUpLeft(1));
          availableMoves.add(goKnightDownRight(1));
          availableMoves.add(goKnightDownLeft(1));
          
          availableMoves.add(goKnightRightUp(1));
          availableMoves.add(goKnightLeftUp(1));
          availableMoves.add(goKnightRightDown(1));
          availableMoves.add(goKnightLeftDown(1));
   

        return availableMoves;
    }
}
