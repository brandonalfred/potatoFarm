package com.kasten.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class GUI implements Observer {
    private App myApp;
    private String currentView;
    private MenuWindow mainMenu;
    private OptionsWindow optionsMenu;
    //private BoardWindow boardWindow;

    @Override
    public void update(Observable o, Object arg) {
        AppState state = ( AppState ) arg;
        String newView = state.getView();
        System.out.println(newView);
        if (!newView.equals(currentView)) switchView(newView);
    }

    public GUI (App app) {
        myApp = app;
        currentView = "";
    }

    public void switchView(String newView) {
        switch (newView) {
            case "options":
                loadOptionsMenu();
                break;
            case "main":
                loadMainMenu();
                break;
        }
    }

    private void loadOptionsMenu() {
        optionsMenu = new OptionsWindow(this);
    }

    private void loadMainMenu() {
        mainMenu = new MenuWindow(this);
    }
}