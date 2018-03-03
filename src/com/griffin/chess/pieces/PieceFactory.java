package com.griffin.chess.pieces;

public class PieceFactory {
    public Piece createPiece(int playerID, int pieceID, int row, int col) {
        if (row == 6 || row == 1)
            return new Pawn(playerID, pieceID, row, col);
        else {
            if (col == 0 || col == 7) return new Rook(playerID, pieceID, row, col);
            else if (col == 1 || col == 6) return new Knight(playerID, pieceID, row, col);
            else if (col == 2 || col == 5) return new Bishop(playerID, pieceID, row, col);
            else if (col == 3) return new Queen(playerID, pieceID, row, col);
            else return new King(playerID, pieceID, row, col);
        }
    }
}
