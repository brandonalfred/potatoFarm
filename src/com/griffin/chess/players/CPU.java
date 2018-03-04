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
        System.out.println("INSIDE AI TURN");
        //makeRandomMove();
        calculateBestMove();
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



    public void calculateBestMove() {
        // maybe return a boardState?
        // or just manually move the piece at the end of a function...

        // initialize to a large negative number
        int bestValue = -99999;

        ArrayList<ArrayList<ArrayList<String>>> boardList = new ArrayList<>();
        ArrayList<ArrayList<String>> bestBoard;
        for (Piece piece : getPiecesWithMoves()) {
            boardList.addAll(getBoardsForPieceAI(piece));
        }
        // best board here...
        System.out.printf("Out of %d boards..\n", boardList.size());
        bestBoard = findBestBoard(boardList);
        board.setBoardState(findBestBoard(boardList));
        System.out.printf("****\nBest move score %d\n****\n", calculateBoardScore(bestBoard));
        for (int row = 0; row < 8; row++) {
            System.out.println(bestBoard.get(row).toString());
        }
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
        System.out.println("AI Looking at new board State");  // <-- debugging AI
    }
}
