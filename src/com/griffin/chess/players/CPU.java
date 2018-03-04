package com.griffin.chess.players;

import com.griffin.chess.containers.Board;
import com.griffin.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CPU extends aPlayer {
    ArrayList<ArrayList<String>> currentBoardState;
    Player opponent;
    Board board;
    public CPU(int ID, Player human, Board container) {
        super(ID);
        opponent = human;
        board = container;

    }

    public String getType() { return "robot"; }

    public void takeAITurn() {
        //System.out.println("INSIDE AI TURN");
        //makeRandomMove();
        //calculateBestMove();
        takeBestMove();
    }

    private void makeRandomMove() {
        int pieceMax = getPiecesWithMoves().size();
        Piece randomPiece = getPiecesWithMoves().get(new Random().nextInt(pieceMax));

        int movesMax =  randomPiece.getAvailableMoves().size();
        ArrayList<Integer> randomMove = randomPiece.getAvailableMoves().get(new Random().nextInt(movesMax));
        int targetRow = randomMove.get(0);
        int targetCol = randomMove.get(1);

        // check for piece in target cell
        String targetState = currentBoardState.get(targetRow).get(targetCol);
        // kill target piece if it exists
        if (targetState.length() >= 4) {
            int targetID = Integer.parseInt(targetState.substring(2, 4));
            int enemyID = Integer.parseInt(targetState.substring(0, 1));

            // call the enemy players' pieces' kill method
            opponent.getPieces().get(targetID).kill();
            System.out.println("AI CAPTURE!!*********************\n\n"); // <-- debugging AI (evaluateBoard)
        }

        // no special handling for captures.. yet
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

    public ArrayList<ArrayList<ArrayList<String>>> getAllBoardsForPiece(Piece piece) {
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

    public ArrayList<ArrayList<String>> findBestBoard(ArrayList<ArrayList<ArrayList<String>>> boardList) {
        int bestValue = -9999;
        ArrayList<ArrayList<String>> bestBoard = new ArrayList<>();

        for (ArrayList<ArrayList<String>> board : boardList) {
            if (calculateBoardScore(board) > bestValue) {
                bestValue = calculateBoardScore(board);
                bestBoard = board;
            }
        }
        //System.out.printf("******BEST SCORE: %d******\n", bestValue);  // <-- debugging ai moves
        return bestBoard;
    }

    public void takeBestMove() {
        Piece bestPiece = null;
        Piece targetPiece = null;
        ArrayList<Integer> bestMove;
        int bestValue = -9999;
        int bestRow = 0;
        int bestCol = 0;

        for (Piece piece : getPiecesWithMoves()) {
            ArrayList<ArrayList<Integer>> movesList = piece.getAvailableMoves();
            int actualRow = piece.getRow();
            int actualCol = piece.getCol();
            for (ArrayList<Integer> move : movesList) {
                int moveRow = move.get(0);
                int moveCol = move.get(1);
                // find boardvalue of this move
                // if its the best.. set all the best things
                // then make the move with that piece the normal way

                // build "future board" for each potential move
                ArrayList<ArrayList<String>> boardCopy = getBoardCopy();
                String cellState = currentBoardState.get(actualRow).get(actualCol);
                boardCopy.get(actualRow).set(actualCol, "-");
                boardCopy.get(moveRow).set(moveCol, cellState);
                // compute score for that board
                if (calculateBoardScore(boardCopy) > bestValue) {
                    bestValue = calculateBoardScore(boardCopy);
                    bestPiece = piece;
                    bestMove = move;
                    bestRow = bestMove.get(0);
                    bestCol = bestMove.get(1);
                }
            }
        }

        // check for piece in target cell
            String targetState = currentBoardState.get(bestRow).get(bestCol);

        // kill target piece if it exists
        if (targetState.length() >= 4) {
            int targetID = Integer.parseInt(targetState.substring(2, 4));

            // call the enemy players' pieces' kill method
            targetPiece = opponent.getPieces().get(targetID);
            targetPiece.kill();
            System.out.println("SMARTER AI CAPTURE!!*********************\n\n"); // <-- debugging AI (evaluateBoard)
            System.out.printf("%s captured %s at %d,%d\n", bestPiece.getType(), targetPiece.getType(), bestRow, bestCol);
        }
        // no special handling for captures.. yet
        bestPiece.movePiece(bestRow, bestCol);
        System.out.printf("AI moved %s to %d,%d\n", bestPiece.getType(), bestRow, bestCol);
    }

    public int calculateBoardScore(ArrayList<ArrayList<String>> board) {
        int score = 0;
        for (int row = 0;row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (!board.get(row).get(col).equals("-")) {
                    int cellPlayerID = Integer.parseInt(board.get(row).get(col).substring(0,1));
                    String cellPiece = board.get(row).get(col).substring(1,2);
                    if (cellPlayerID == playerID) {
                        //System.out.printf("adding %d\n", getPieceValue(cellPiece));
                        score += getPieceValue(cellPiece);
                        //System.out.printf("score is now %d\n", score);
                    } else {
                        //System.out.printf("subtracting %d\n", getPieceValue(cellPiece));
                        score -= getPieceValue(cellPiece);
                        //System.out.printf("score is now %d\n", score);
                    }
                }
            }
        }
        //System.out.printf("calculated %d\n", score);
        return score;
    }

    public int getPieceValue(String piece) {
        if (piece.equals("♙")) {
            return 10;
        } else if (piece.equals("♖")) {
            return 50;
        } else if (piece.equals("♘")) {
            return 30;
        } else if (piece.equals("♗")) {
            return 30;
        } else if (piece.equals("♕")) {
            return 90;
        } else if (piece.equals("♔")) {
            return 888;
        } else {
            return 0;
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        this.currentBoardState = ( ArrayList<ArrayList<String>> ) arg;
        //System.out.println("AI Looking at new board State");  // <-- debugging AI
    }
}
