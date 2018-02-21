package com.kasten.chess.containers;

import com.kasten.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class App extends Observable {
    private HashMap<String, String> options;
    private Board board; // this will get moved to Board class
    private GUI view;

    public App() {
        options = new HashMap<>();
        board = new Board();
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
        // we can add anything we want the app to keep track of here...
        // any contextual stuff, like castling, would be kept track of like this
        setChanged();
        notifyObservers(options);
    }

    public void setOptions(HashMap<String, String> newState) {
        options = newState;
        setChanged();
        notifyObservers(options);
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public ArrayList<ArrayList<Piece>> getBoardState() {
        return board.getBoardState();
    }
}
