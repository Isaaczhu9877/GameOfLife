package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

// represents panel of buttons that control game
public class ButtonPanel extends JPanel {

    private JButton startButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton resetButton;
    private JButton exitButton;
    private boolean play = true;
    private GameFrame gameFrame;

    // creates new panel with added buttons
    public ButtonPanel(GameFrame gf) {
        gameFrame = gf;
        JPanel buttonPanel = new JPanel();
        setFocusable(false);
        setBackground(new Color(0x696969));
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 70));
        add(startButton());
        add(saveButton());
        add(loadButton());
        add(resetButton());
        add(exitButton());
        addListeners();
    }

    // MODIFIES: this
    // EFFECTS: creates start button
    public Component startButton() {
        startButton = new JButton("Start");
        setFocusable(false);
        setBounds(1,1,100,50);
        setPreferredSize(new Dimension(100,50));
        return startButton;
    }

    // MODIFIES: this
    // EFFECTS: creates save button
    public Component saveButton() {
        saveButton = new JButton("Save");
        setFocusable(false);
        setBounds(100,1,100,50);
        setPreferredSize(new Dimension(100,50));
        return saveButton;
    }

    // MODIFIES: this
    // EFFECTS: creates load button
    public Component loadButton() {
        loadButton = new JButton("Load");
        setFocusable(false);
        setBounds(200,1,100,50);
        setPreferredSize(new Dimension(100,50));
        return loadButton;
    }

    // MODIFIES: this
    // EFFECTS: creates reset button
    public Component resetButton() {
        resetButton = new JButton("Clear Board");
        setFocusable(false);
        setBounds(300,1,100,50);
        setPreferredSize(new Dimension(100,50));
        return resetButton;
    }

    // MODIFIES: this
    // EFFECTS: sets the text of startButton to "Start" if true, "Pause" if false
    public void setTextOfStartPause() {
        if (play) {
            startButton.setText("Start");
        } else {
            startButton.setText("Pause");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates exit button
    public Component exitButton() {
        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(201, 38, 38));
        setFocusable(false);
        return exitButton;
    }

    // MODIFIES: this
    // EFFECTS: adds listeners to each button to function as intended
    public void addListeners() {

        startButton.addActionListener(e -> {
            if (play) {
                gameFrame.startGame();
            } else {
                gameFrame.stopGame();
            }
            play = !play;
            setTextOfStartPause();
        });
        saveButton.addActionListener(e -> {
            gameFrame.saveGame();
        });
        loadButton.addActionListener(e -> {
            gameFrame.loadGame();
        });
        resetButton.addActionListener(e -> {
            gameFrame.clearBoard();
            play = true;
            setTextOfStartPause();
        });
        exitButton.addActionListener(e -> {
            gameFrame.exitGame();
        });
    }

}
