package ui;

import model.Board;
import model.Cell;
import model.Colony;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.*;

// represents a game window and methods
public class GameFrame extends JFrame {

    private static int height = 700;
    private static int width = 1200;
    private static int cellLength = 15;
    private int interval = 500;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private JPanel gamePanel;
    private Clip clip1;
    private Clip clip2;
    private Colony colony;
    private Board board;
    private Timer timer = new Timer(interval, new ActionListener() {

        // MODIFIES: this
        //EFFECTS: timer that updates board after interval
        @Override
        public void actionPerformed(ActionEvent e) {
            board.fillBoard();
            colony.insertCells(board);
            colony.filter(board);
            generateGrid(getGraphics(), colony);
        }
    });

    // EFFECTS: initializes and sets up game window and adds all necessary components for game to run
    public GameFrame() {
        gamePanel = new JPanel();
        colony = new Colony();
        board = new Board();
        jsonWriter = new JsonWriter("./data/boardStartStateSave.json");
        jsonReader = new JsonReader("./data/boardStartStateSave.json");
        setTitle("Game of Life");
        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        gamePanel.setFocusable(false);
        getContentPane().setBackground(new Color(0x696969));
        setLayout(null);
        makeGamePanel();
        ButtonPanel buttonPanel = new ButtonPanel(this);
        add(buttonPanel);
        buttonPanel.setBounds(0, 600, 1200, 100);
        add(gamePanel);
        setLocationRelativeTo(null);
        makeSound();
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: creates new panel for game grid with mouse listeners
    public JPanel makeGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setBounds(0,0,1200,600);
        gamePanel.setFocusable(false);
        gamePanel.setBackground(new Color(0x4e4e4e));
        MouseListener mouseListener = new MouseClickListener();
        gamePanel.addMouseListener(mouseListener);
        return gamePanel;
    }

    // MODIFIES: this
    // EFFECTS: sets up sound for game
    public void makeSound() {
        try {
            File addSoundFile = new File("./data/hitmarker_2.wav");
            File removeSoundFile = new File("./data/strongpunch.wav");
            AudioInputStream addAudio = AudioSystem.getAudioInputStream(addSoundFile);
            AudioInputStream removeAudio = AudioSystem.getAudioInputStream(removeSoundFile);
            clip1 = AudioSystem.getClip();
            clip1.open(addAudio);
            clip2 = AudioSystem.getClip();
            clip2.open(removeAudio);
        } catch (Exception e) {
            System.out.println("Unable to play sound effect");
        }
    }

    //MODIFIES: this
    // EFFECTS: draws current state of game grid
    public void generateGrid(Graphics g, Colony colony) {
        for (int x = 0; x < gamePanel.getWidth(); x += cellLength) {
            for (int y = 0; y < gamePanel.getHeight(); y += cellLength) {
                if (colony.contain(new Cell((y / cellLength),(x / cellLength)))) {
                    g.setColor(Color.pink);
                    g.fillRect(x, y, cellLength, cellLength);
                } else {
                    g.setColor(new Color(0x4e4e4e));
                    g.fillRect(x, y, cellLength, cellLength);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds and removes cell from mouse click to/from colony
    public void addCells(Boolean operation, int x, int y) {
        int posX = (int) Math.floor(x / cellLength);
        int posY = (int) Math.floor(y / cellLength);

        if (operation) {
            colony.addCell(new Cell(posY, posX));
            clip1.setFramePosition(0);
            clip1.start();
        } else {
            colony.delete(new Cell(posY, posX));
            clip2.setFramePosition(0);
            clip2.start();
        }
        generateGrid(getGraphics(), colony);
    }

    // MODIFIES: this
    // EFFECTS: starts the game
    public void startGame() {
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: stops game
    public void stopGame() {
        timer.stop();
    }

    //MODIFIES: this
    // EFFECTS: clears all cells on current game board
    public void clearBoard() {
        stopGame();
        colony.wipe();
        board.fillBoard();
        generateGrid(getGraphics(), colony);
    }


    // EFFECTS: saves game to file
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(colony);
            jsonWriter.close();
            ImageIcon icon = new ImageIcon("./data/rollSafe.jpeg");
            JOptionPane.showMessageDialog(null, "Saved game to file!", "System Message",
                    JOptionPane.PLAIN_MESSAGE, icon);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error could not save game!",
                    "System Message", JOptionPane.PLAIN_MESSAGE);
        }
    }

    // EFFECTS: loads game from file
    public void loadGame() {
        try {
            colony = jsonReader.read();
            System.out.println("Loaded board form last save");
            ImageIcon icon = new ImageIcon("./data/SuccessKidMeme.jpeg");
            JOptionPane.showMessageDialog(null, "Loaded board from file!", "System Message",
                    JOptionPane.PLAIN_MESSAGE, icon);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error could not load game!",
                    "System Message", JOptionPane.ERROR_MESSAGE);
        }
        generateGrid(getGraphics(), colony);
    }

    // EFFECTS: quits the game
    public void exitGame() {
        System.exit(0);
    }

    // class that sets up mouse listener to get x and y pos of click
    private class MouseClickListener extends MouseAdapter {

        Boolean addOrRemove;

        // MODIFIES: this
        // EFFECTS: mouse listener to get button clicked and mouse position at click
        @Override
        public void mousePressed(MouseEvent e) {
            switch (e.getButton()) {
                case MouseEvent.BUTTON1:
                    addOrRemove = true;
                    break;
                case MouseEvent.BUTTON3:
                    addOrRemove = false;
                    break;
            }
            addCells(addOrRemove, e.getX(), e.getY());
        }
    }
}


