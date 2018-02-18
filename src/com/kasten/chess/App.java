package com.kasten.chess;

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


}
