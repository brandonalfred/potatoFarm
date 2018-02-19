package com.kasten.chess.model;

import com.kasten.chess.containers.GUI;

import java.util.HashMap;
import java.util.Observable;

public class App extends Observable {
    private HashMap<String, String> state;
    private GUI view;

    public App() {
        state = new HashMap<>();
        view = new GUI(this);
        addObserver(view);
        setInitialState();
    }

    public void setInitialState() {
        state.put("view", "main");
        state.put("theme", "day");
        state.put("opponent", "robot");
        state.put("difficulty", "normal");
        state.put("board", "blank");
        setChanged();
        notifyObservers(state);
    }

    public void setState(HashMap<String, String> newState) {
        state = newState;
        setChanged();
        notifyObservers(state);
    }

    public HashMap<String, String> getState() {
        return state;
    }
}
