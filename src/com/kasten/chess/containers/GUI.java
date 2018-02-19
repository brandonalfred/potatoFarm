package com.kasten.chess.containers;

import com.kasten.chess.model.AppState;
import com.kasten.chess.views.MenuWindow;
import com.kasten.chess.views.OptionsWindow;

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
        System.out.println("new view: " + newView);
        if (!newView.equals(currentView)) switchView(newView);
    }

    public GUI (App app) {
        myApp = app;
        currentView = "";
    }

    public void switchView(String newView) {
        switch (newView) {
            case "options":
                optionsMenu = new OptionsWindow(this);
                break;
            case "main":
                mainMenu = new MenuWindow(this);
                break;
        }
    }
    public void updateView(String newView) {
        this.myApp.updateView(newView);
    }
}