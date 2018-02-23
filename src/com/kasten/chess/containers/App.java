package com.kasten.chess.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class App {
    private HashMap<String, String> options;
    private Board myBoard;
    private GUI myGUI;

    public App() {
        options = new HashMap<>();
        myBoard = new Board();
        myGUI = new GUI(this);
        setInitialOptions();
    }

    public void setInitialOptions() {
        options.put("view", "main");
        options.put("theme", "day");
        options.put("opponent", "robot");
        options.put("difficulty", "normal");
        options.put("activePlayer", "one");
        options.put("selectedCell", "99");

        myGUI.updateState(options);
    }

    public void setOptions(HashMap<String, String> newState) {
        options = newState;
        myGUI.updateState(options);
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public ArrayList<ArrayList<String>> getBoardState() {
        return myBoard.getBoard();
    }

    public Board getBoard() {
        return myBoard;
    }

    public void setSelected(String selected) {
        myBoard.setSelected(selected);
    }
}
