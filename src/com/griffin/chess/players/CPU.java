package com.griffin.chess.players;

import com.griffin.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CPU extends aPlayer {
    ArrayList<ArrayList<String>> currentBoardState;
    public CPU(int ID) {
        super(ID);
    }

    public String getType() { return "robot"; }

    public void takeAITurn() {
        System.out.println("INSIDE AI TURN");
        /*
        int boardCount = 0;
        // for sample, generate boards for one piece
        for (Piece piece : getPieces()) {
            for (ArrayList<ArrayList<String>> board : getBoardsForPieceAI(piece)) {
                boardCount++;
                System.out.printf("AI BOARD # %d\n%s\n", boardCount, board.toString());
            }
        }
        */
        makeRandomMove();
    }

    private void makeRandomMove() {
        int pieceMax = getPiecesWithMoves().size();
        Piece randomPiece = getPiecesWithMoves().get(new Random().nextInt(pieceMax));

        int movesMax =  randomPiece.getAvailableMoves().size();
        ArrayList<Integer> randomMove = randomPiece.getAvailableMoves().get(new Random().nextInt(movesMax));
        int targetRow = randomMove.get(0);
        int targetCol = randomMove.get(1);

        // no handling for captures.. yet
        randomPiece.movePiece(targetRow, targetCol);
        System.out.println("random move made!");
    }

    public ArrayList<ArrayList<String>> getBoardCopy() {
        ArrayList<ArrayList<String>> boardCopy = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            boardCopy.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                boardCopy.get(row).add(currentBoardState.get(row).get(col));
            }
        }
        return boardCopy;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getBoardsForPieceAI(Piece piece) {
        // before we do full boards for a piece
        // lets just look at moves
        //System.out.printf("%s%d: %s\n",piece.getType(), piece.getID(), piece.getAvailableMoves().toString());
        ArrayList<ArrayList<ArrayList<String>>> boardList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> movesList = piece.getAvailableMoves();
        int actualRow = piece.getRow();
        int actualCol = piece.getCol();
        for (ArrayList<Integer> move : movesList) {
            int moveRow = move.get(0);
            int moveCol = move.get(1);
            ArrayList<ArrayList<String>> boardCopy = getBoardCopy();
            String cellState = currentBoardState.get(actualRow).get(actualCol);
            boardCopy.get(actualRow).set(actualCol, "-");
            boardCopy.get(moveRow).set(moveCol, cellState);
            boardList.add(boardCopy);
        }
        return boardList;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.currentBoardState = ( ArrayList<ArrayList<String>> ) arg;
        System.out.println("AI Looking at new board State");  // <-- debugging AI
    }
}
