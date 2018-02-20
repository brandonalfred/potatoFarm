package com.kasten.chess.containers;

import com.kasten.chess.views.MenuWindow;
import com.kasten.chess.views.OptionsWindow;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GUI implements Observer {
    private App myApp;
    private String currentView;
    private MenuWindow mainMenu;
    private OptionsWindow optionsMenu;
    //private BoardWindow boardWindow;

    public GUI (App app) {
        myApp = app;
        currentView = "";
    }

    @Override
    public void update(Observable o, Object arg) {
        HashMap<String, String> state = ( HashMap ) arg;
        String newView = state.get("view");

        if (!newView.equals(currentView)) {
            System.out.println("new view: " + newView); // FOR DEBUGGING
            if (newView.equals("options")) optionsMenu = new OptionsWindow(this, state);
            if (newView.equals("main")) mainMenu = new MenuWindow(this, state);
            if (newView.equals("board")) System.out.println("BOARD STUB - ");
        }
    }

    public void setView(String newView) {
        HashMap<String, String> newState = getState();
        newState.put("view", newView);
        myApp.setState(newState);
    }

    public void setOptions(HashMap<String, String> selectedOptions) {
        System.out.println("setting " + selectedOptions.get("theme"));
        myApp.setState(selectedOptions);
    }

    public HashMap<String, String> getState() {
        return myApp.getState();
    }
}