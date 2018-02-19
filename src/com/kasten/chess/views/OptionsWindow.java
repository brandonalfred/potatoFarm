package com.kasten.chess.views;

import com.kasten.chess.containers.GUI;

import javax.swing.*;

public class OptionsWindow extends JFrame {
    private GUI myGUI;
    private JButton cancelButton;
    private JButton applyButton;

    public OptionsWindow(GUI container) {
        // SOMEHOW LOAD THE APP STATE TO SHOW THE CURRENT SELECTED OPTIONS
        myGUI = container;

        /* Window Size/Position settings */
        this.setTitle("Game Options");
        this.setSize(400, 500);
        this.setLocation(450, 100);

        /* Options Buttons */

        /* Day/Night Mode */
        JLabel themeHeading = new JLabel("Display Theme:");
        themeHeading.setBounds(20, 20, 200, 30);
        JRadioButton dayRadio = new JRadioButton("Day Mode");
        JRadioButton nightRadio = new JRadioButton("Night Mode");
        dayRadio.setBounds(50, 50, 150, 20);
        nightRadio.setBounds(50, 75, 150, 20);
        ButtonGroup themeGroup = new ButtonGroup();
        themeGroup.add(dayRadio);
        themeGroup.add(nightRadio);
        this.add(themeHeading);
        this.add(dayRadio);
        this.add(nightRadio);

        /* Human/Computer Opponent */
        JLabel playerHeading = new JLabel("Opponent Type:");
        playerHeading.setBounds(20, 120, 200, 30);
        JRadioButton humanRadio = new JRadioButton("Human");
        JRadioButton robotRadio = new JRadioButton("AI-powered Robot");
        humanRadio.setBounds(50, 150, 150, 20);
        robotRadio.setBounds(50, 175, 150, 20);
        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(humanRadio);
        playerGroup.add(robotRadio);
        this.add(playerHeading);
        this.add(humanRadio);
        this.add(robotRadio);

        /* Difficulty Selection */
        JLabel difficultyHeading = new JLabel("Difficulty Level:");
        difficultyHeading.setBounds(20, 220, 200, 30);
        JRadioButton easyRadio = new JRadioButton("Easy");
        JRadioButton normalRadio = new JRadioButton("Normal");
        JRadioButton hardRadio = new JRadioButton("Hard");
        easyRadio.setBounds(50, 250, 150, 20);
        normalRadio.setBounds(50, 275, 150, 20);
        hardRadio.setBounds(50, 300, 150, 20);
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyRadio);
        difficultyGroup.add(normalRadio);
        difficultyGroup.add(hardRadio);
        this.add(difficultyHeading);
        this.add(easyRadio);
        this.add(normalRadio);
        this.add(hardRadio);

        /* 'Apply' and 'Cancel' Buttons */
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(20, 380, 75, 60);
        cancelButton.addActionListener(e -> handleCancelPress());
        this.add(cancelButton);
        applyButton = new JButton("Apply");
        applyButton.setBounds(305, 380, 75, 60);

        // add Apply handler here
        applyButton.addActionListener(e -> handleApplyPress());
        this.add(applyButton);
        this.getRootPane().setDefaultButton(applyButton);
        applyButton.requestFocus();

        /* Layout/Visibility Window Options */
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    /* Button Handlers */
    private void handleCancelPress() {
        System.out.println("Cancel Pressed.");
        this.setVisible(false);
        myGUI.updateView("main");
    }

    private void handleApplyPress() {
        System.out.println("Apply Pressed.");
        // APPLY NEW OPTIONS TO APP STATE
        this.setVisible(false);
        myGUI.updateView("main");
    }
}
