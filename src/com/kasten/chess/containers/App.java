package com.kasten.chess.containers;

import com.kasten.chess.views.BoardWindow;

import java.util.HashMap;

public class App {
    private HashMap<String, String> options;
    private Board myBoard;
    private GUI myGUI;

    public App() {
        options = new HashMap<>();
        myGUI = new GUI(this);
        setInitialOptions();
    }

    public void setInitialOptions() {
        options.put("view", "main");
        options.put("theme", "day");
        options.put("opponent", "robot");
        options.put("difficulty", "normal");
        myGUI.updateView(options);
    }

    public void setOptions(HashMap<String, String> newState) {
        options = newState;
        myGUI.updateView(options);
    }

    public void setSelected(String selected) {
        myBoard.setSelected(selected);
    }

    public void startNewGame(BoardWindow gameScreen) {
        // here is where we could get the Player2 and pass that to the constructor to set 2nd player
        // also difficulty level could be set in the same way
        myBoard = new Board(options);
        myBoard.addObserver(gameScreen);
        myBoard.updateDisplay();
    }

    public void setView(String newView) {
        options.put("view", newView);
        myGUI.updateView(options);
    }

    public void confirmMove() {
        myBoard.confirmMove();
    }
}
