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

import static java.lang.Integer.parseInt;

public class Board extends Observable {
    private HashMap<String, String> gameOptions;
    private ArrayList<Player> players;
    private int activePlayer;
    private ArrayList<ArrayList<String>> boardState;
    private ArrayList<Integer> selectedCell;
    private ArrayList<Integer> targetCell;
    private ArrayList<ArrayList<Integer>> destinations;

    public Board(HashMap<String, String> options) {
        gameOptions = options;
        players = new ArrayList<>();
        players.add(new Human(0)); // <- we'll have to add a player 2 here eventually
        addSecondPlayer();
        activePlayer = 0;
        boardState = generateBlankBoard();
        selectedCell = new ArrayList<>();
        targetCell = new ArrayList<>();
        destinations = new ArrayList<>();
        addPieces();
    }

    private void addSecondPlayer() {
        if (gameOptions.get("opponent").equals("human")) {
            players.add(new Human(1));
        }
    }

    private ArrayList<ArrayList<String>> generateBlankBoard() {
        boardState = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            boardState.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                boardState.get(row).add("-");
                // for now... `-` indicates a blank space
            }
        }
        return boardState;
    }

    private void generateNewBoard() {
        boardState = generateBlankBoard();
        addPieces();
        markSpecialCells();
    }

    private void markSpecialCells() {
        if (!selectedCell.isEmpty()) {
            int selRow = selectedCell.get(0);
            int selCol = selectedCell.get(1);
            String cellState = boardState.get(selRow).get(selCol);
            boardState.get(selRow).set(selCol, cellState.concat("~"));

            if (!destinations.isEmpty()) {
                for (ArrayList<Integer> destination : destinations) {
                    int destRow = destination.get(0);
                    int destCol = destination.get(1);
                    cellState = boardState.get(destRow).get(destCol);
                    if (destRow != selRow || destCol != selCol)
                        boardState.get(destRow).set(destCol, cellState.concat("."));
                }

                if (!targetCell.isEmpty()) {
                    int targetRow = targetCell.get(0);
                    int targetCol = targetCell.get(1);
                    cellState = boardState.get(targetRow).get(targetCol);
                    boardState.get(targetRow).set(targetCol, cellState.concat("?"));
                }
            }
        }
    }

    private void addPieces() {
        for (Player player : players) {
            //System.out.printf("players in game - %s\n", player.getType());
            for (Piece piece : player.getPieces()) {
                if (piece.isAlive()) {
                    boardState.get(piece.getRow()).set(piece.getCol(),
                            Integer.toString(piece.getOwner())
                            + piece.getType()
                            + setID(piece.getID()));
                    //System.out.printf("piece at %d, %d\n", piece.getRow(), piece.getCol());
                }
            }
        }
    }

    private String setID(int pieceID) {
        if (pieceID < 10)
            return "0" + Integer.toString(pieceID);
        return Integer.toString(pieceID);
    }

    public void setSelected(String selected) {
        // logic of IF cell should be selected goes in here.
        // by the time we make it to the window.. its already been validated

        targetCell.clear();

        int row = parseInt(selected.substring(0,1));
        int col = parseInt(selected.substring(1));
        String cellState = boardState.get(row).get(col);

        // check for an available move
        String moveCheck = cellState.substring(selected.length()-1);
        if (!moveCheck.equals(".")) {
            // new piece selection
            selectedCell.clear();

            // cant select an empty cell
            if (!boardState.get(row).get(col).equals("-")) {

                int owner = Integer.parseInt(boardState.get(row).get(col).substring(0,1));
                int pieceID = Integer.parseInt(boardState.get(row).get(col).substring(2,4));

                if (owner == activePlayer) {
                    selectedCell.add(row);
                    selectedCell.add(col);
                    setDestinations(pieceID);
                    System.out.printf("Clicked Piece %s\n", pieceID);
                }
            }
        } else {
            // handle a valid move selection
            // but wait for confirmation
            targetCell.add(row);
            targetCell.add(col);

        }
        updateDisplay();
    }

    private void setDestinations(int pieceID) {
        int destRow;
        int destCol;
        String cellState;

        // clear old destinations
        destinations.clear();

        // get legal destinations from the piece itself
        destinations = players.get(activePlayer).getPieces().get(pieceID).getAvailableMoves();

        // mark all the proper cells
        for (ArrayList<Integer> destination : destinations) {
            destRow = destination.get(0);
            destCol = destination.get(1);
            cellState = boardState.get(destRow).get(destCol);
            boardState.get(destRow).set(destCol, cellState.concat("."));
        }

    }

    public void updateDisplay() {
        generateNewBoard();
        setChanged();
        notifyObservers(boardState);
    }

    public void confirmMove() {
        if (!targetCell.isEmpty()) {
            int pieceRow = selectedCell.get(0);
            int pieceCol = selectedCell.get(1);
            int targetRow = targetCell.get(0);
            int targetCol = targetCell.get(1);

            // identify the piece
            String cellState = boardState.get(pieceRow).get(pieceCol);
            int pieceID = Integer.parseInt(cellState.substring(2,4));

            // call the pieces move method
            players.get(activePlayer).getPieces().get(pieceID).movePiece(targetRow, targetCol);

            // toggle the active player
            activePlayer = (activePlayer + 1) % players.size();

            // update the view
            selectedCell.clear();
            destinations.clear();
            targetCell.clear();
            updateDisplay();
        }
    }
}
