package com.griffin.chess.containers;

// once we add interactivity into the board window...
// this Board container class will manage the state of the game

// this class can observe the pieces so when they update position, the board knows

// board has 2 players
// players have pieces

import com.griffin.chess.pieces.Piece;
import com.griffin.chess.players.CPU;
import com.griffin.chess.players.Human;
import com.griffin.chess.players.Player;

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
        players.add(new Human(0));
        addSecondPlayer();
        activePlayer = 0;
        boardState = generateBlankBoard();
        selectedCell = new ArrayList<>();
        targetCell = new ArrayList<>();
        destinations = new ArrayList<>();
        addPieces();

        // experimental Piece/AI Observer pattern add-on
        for (Player player : players) {
            for (Piece piece : player.getPieces())
                addObserver(piece);
            if (player.getType().equals("robot")) {
                addObserver(player);
            }
        }
    }

    private void addSecondPlayer() {
        int id = 1;
        if (gameOptions.get("opponent").equals("human")) {
            players.add(new Human(id));
        } else {
            players.add(new CPU(id));
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
        // check if something is selected
        if (!selectedCell.isEmpty()) {
            int selRow = selectedCell.get(0);
            int selCol = selectedCell.get(1);
            String cellState = boardState.get(selRow).get(selCol);
            boardState.get(selRow).set(selCol, cellState.concat("~"));

            // check for any highlighted destinations
            if (!destinations.isEmpty()) {
                for (ArrayList<Integer> destination : destinations) {
                    int destRow = destination.get(0);
                    int destCol = destination.get(1);
                    cellState = boardState.get(destRow).get(destCol);
                    if (destRow != selRow || destCol != selCol)
                        if (cellState.length() >= 4)
                            // appropriately mark "enemy target" destinations
                            boardState.get(destRow).set(destCol, cellState.concat("x"));
                        else
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

        // check for an available move/capture
        String moveCheck = cellState.substring(cellState.length()-1);
        if (moveCheck.equals("x")) {
            System.out.println("capture click!"); // <- capture debug
            targetCell.add(row);
            targetCell.add(col);
        } else if (moveCheck.equals(".")) {
            System.out.println("valid target click!"); // <- capture debug
            targetCell.add(row);
            targetCell.add(col);
        } else {
            System.out.println(moveCheck + " click!"); // <- capture debug
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
        }
        updateDisplay();
    }

    private void setDestinations(int pieceID) {
        // clear old destinations
        destinations.clear();

        // get legal destinations from the piece itself
        destinations = players.get(activePlayer).getPieces().get(pieceID).getAvailableMoves();
    }

    public void updateDisplay() {
        generateNewBoard();
        setChanged();
        notifyObservers(boardState);
    }

    public void confirmMove() {
        if (players.get(activePlayer).getType().equals("human")) {
            System.out.println("HUMAN TAKING TURN");    // <-- debugging AI
            if (!targetCell.isEmpty()) {
                int pieceRow = selectedCell.get(0);
                int pieceCol = selectedCell.get(1);
                int targetRow = targetCell.get(0);
                int targetCol = targetCell.get(1);

                // identify the piece
                String cellState = boardState.get(pieceRow).get(pieceCol);
                int pieceID = Integer.parseInt(cellState.substring(2, 4));

                // check for piece in target cell
                String targetState = boardState.get(targetRow).get(targetCol);
                // kill target piece if it exists
                if (targetState.length() >= 4) {
                    int targetID = Integer.parseInt(targetState.substring(2, 4));
                    int enemyID = Integer.parseInt(targetState.substring(0, 1));

                    // call the enemy players' pieces' kill method
                    players.get(enemyID).getPieces().get(targetID).kill();
                }

                // call the pieces move method
                players.get(activePlayer).getPieces().get(pieceID).movePiece(targetRow, targetCol);
            }
        } else if (players.get(activePlayer).getType().equals("robot")) {
            System.out.println("AI TAKING TURN");   // <-- debugging AI
        }

        // toggle the active player
        activePlayer = (activePlayer + 1) % players.size();

        // update the view
        selectedCell.clear();
        destinations.clear();
        targetCell.clear();
        updateDisplay();
        // let the CPU know to go
        if (players.get(activePlayer).getType().equals("robot"))
            notifyAI(players.get(activePlayer));
    }

    // AI stuff going in here
    public void notifyAI(Player aiPlayer) {
        aiPlayer.takeAITurn();
        confirmMove();
    }
}
