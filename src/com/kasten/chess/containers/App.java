package com.kasten.chess.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class App extends Observable {
    private HashMap<String, String> options;
    private ArrayList<ArrayList<String>> board;
    private GUI view;

    public App() {
        options = new HashMap<>();
        board = generateBlankBoard();
        view = new GUI(this);
        addObserver(view);
        setInitialOptions();
    }

    public void setInitialOptions() {
        options.put("view", "main");
        options.put("theme", "day");
        options.put("opponent", "robot");
        options.put("difficulty", "normal");
        options.put("activePlayer", "one");

        setChanged();
        notifyObservers(options);
    }

    private ArrayList<ArrayList<String>> generateBlankBoard() {
        board = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            board.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                board.get(row).add("-");
                // add initial pieces in here!
            }
        }
        return board;
    }

    public void setOptions(HashMap<String, String> newState) {
        options = newState;
        setChanged();
        notifyObservers(options);
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public ArrayList<ArrayList<String>> getBoard() {
        return board;
    }
}
