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
import java.util.HashMap;
import java.util.Observable;

public class Board extends Observable {
    private HashMap<String, String> gameState;
    private ArrayList<ArrayList<String>> board;
    private ArrayList<Player> players;

    public Board() {
        gameState = new HashMap<>();
        players = new ArrayList<>();
        players.add(new Human(1)); // <- this is the method that should add pieces
        board = generateBlankBoard();
        setInitialGameState();
        addPieces();
    }

    private ArrayList<ArrayList<String>> generateBlankBoard() {
        board = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            board.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                board.get(row).add("-");
                // for now... `-` indicates a blank space
                // this isn't finished at all yet.. still in debugging stage
            }
        }
        return board;
    }

    public void setInitialGameState() {
        gameState.put("activePlayer", "one");
        gameState.put("selectedCell", "99");
    }

    public void addPieces() {
        for (Player player : players) {
            System.out.printf("players in game - %s\n", player.getType());
            for (Piece piece : player.getPieces()) {
                board.get(piece.getRow()).set(piece.getColumn(), piece.getType());
                System.out.printf("piece at %d, %d\n", piece.getRow(), piece.getColumn());
            }
        }

    }

    public ArrayList<ArrayList<String>> getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void debugBoardOutput() {
        // print out some debug stuff
    }

    public void setSelected(String selected) {
        gameState.put("selectedCell", selected);
        setChanged();
        notifyObservers(gameState);
    }
    // add a startGame() method that calls both players.generatePieces() method?

    // there shouldn't be a problem adding a players pieces to the board
    // position is a row/col array [1, 3]
}
