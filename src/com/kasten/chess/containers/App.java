package com.kasten.chess.containers;

import com.kasten.chess.model.AppState;

import java.util.Observable;

public class App extends Observable {
    private AppState state;
    private GUI view;

    public App() {
        state = new AppState();
        view = new GUI(this);
        this.addObserver(view);
        setChanged();
        notifyObservers(state);
    }

    public void updateView(String newView) {
        state.setView(newView);
        setChanged();
        notifyObservers(state);

    }
}
