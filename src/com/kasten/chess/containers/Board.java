package com.kasten.chess.containers;

// once we add interactivity into the board window...
// this Board container class will manage the state of the game

// this class can observe the pieces so when they update position, the board knows

// board has 2 players
// players have pieces

import com.kasten.chess.pieces.Piece;
import com.kasten.chess.players.Human;
import com.kasten.chess.players.Player;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Piece>> board;
    private ArrayList<Player> players;

    public Board() {
        board = generateBlankBoard();
        players = new ArrayList<>();
        players.add(new Human(1)); // <- this is the method that should add pieces
    }

    private ArrayList<ArrayList<Piece>> generateBlankBoard() {
        board = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            board.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                // board.get(row).add("-");
                // this isn't finished at all yet.. still in debugging stage
            }
        }
        return board;
    }

    public void addPiece(Piece newPiece) {
        int row = newPiece.getRow();
        int column = newPiece.getColumn();

        board.get(row).add(column, newPiece);
    }

    public ArrayList<ArrayList<Piece>> getBoardState() {
        return board;
    }

    public void debugBoardOutput() {
        // print out some debug stuff
    }
    // add a startGame() method that calls both players.generatePieces() method?

    // there shouldn't be a problem adding a players pieces to the board
    // position is a row/col array [1, 3]
}
