package ui;

import model.Board;
import model.Cell;
import model.Colony;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

// Class that is used to start a new game and render the grid
public class LaunchGame {
    private String saveLocation = "./data/boardStartStateSave.json";
    Colony colony;
    Colony initialColony;
    Board board;
    Scanner scanner = new Scanner(System.in);
    JsonReader jsonReader;
    JsonWriter jsonWriter;


    // EFFECTS: Creates new LaunchGame object with a colony and board begins the game
    public LaunchGame() {
        jsonWriter = new JsonWriter(saveLocation);
        jsonReader = new JsonReader(saveLocation);
        board = new Board();
        colony = new Colony();
        initialColony = new Colony();
        loadSave();
        runGame();
        System.out.println("Thank you for playing!");

    }

    // MODIFIES: this
    // EFFECTS: Givers user option to load initial board form last save
    public void loadSave() {
        System.out.println("Would you like to load your saved grid?");
        System.out.println("y -> Enter for yes");
        System.out.println("n -> Enter for no");
        String response;
        while (true) {
            response = scanner.nextLine();
            if ((response.equals("n")) || (response.equals("y"))) {
                break;
            } else {
                System.out.println("Invalid input please try again");
            }
        }

        if (response.equals("n")) {
            System.out.println("");
        } else if (response.equals("y")) {
            try {
                colony = jsonReader.read();
                initialColony = jsonReader.read();
                System.out.println("Loaded board form last save");
            } catch (IOException e) {
                System.out.println("Unable to read from file, please create new board");
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: asks user for board size and cells to insert IF the board was not loaded from save
    // If loaded, begin simulation
    public void runGame() {
        if (colony.getSize() > 0) {
            beginSimulation();
        } else if (colony.getSize() == 0) {
            insertCellToGrid();
            beginSimulation();
        }

    }

    // EFFECTS:
    public void insertCellToGrid() {
        while (true) {
            System.out.println("Please input the coordinates of cell you want to add with the exact format: \"X,Y\"."
                    + " X and Y both need to be >= zero"
                    + " If you are done placing cells and want to begin the game please type \"start\"!");
            String command = scanner.nextLine();
            if (command.equals("start")) {
                break;
            } else {
                String[] posXAndY = command.split(",");
                try {
                    int posX = parseInt(posXAndY[0]);
                    int posY = parseInt(posXAndY[1]);
                    if (posX < 0 || posY < 0 || posX >= board.getWidth() || posY >= board.getHeight()) {
                        System.out.println("Invalid input try again");
                    } else {
                        colony.addCell(new Cell(posY, posX));
                        initialColony.addCell(new Cell(posY, posX));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input try again");
                }
            }
        }
    }

    // EFFECTS: prints board until user wants to stop
    public void beginSimulation() {
        while (true) {
            printGrid();
            System.out.println("Input \"exit\" to quit and reset the simulation.");
            System.out.println("Input anything else to continue to the next generation");

            String command = scanner.nextLine();

            if (command.equals("exit")) {
                saveBoard();
                break;
            }
        }
    }


    // EFFECTS: Asks user if they want to save their board
    public void saveBoard() {
        System.out.println("Would you like to save your board?\n y -> yes \n n -> no");
        String response;
        while (true) {
            response = scanner.nextLine();
            if ((response.equals("n")) || (response.equals("y"))) {
                break;
            } else {
                System.out.println("Invalid input please try again");
            }
        }
        if (response.equals("y")) {
            System.out.println("Save which board? \n init => Initial board \n current => Current board");
            String choice;
            while (true) {
                choice = scanner.nextLine();
                if ((choice.equals("init")) || (choice.equals("current"))) {
                    break;
                } else {
                    System.out.println("Invalid input please try again");
                }
            }
            saveWhichBoard(choice);
        }
    }

    // EFFECTS: saves initial or current board based on user choice
    public void saveWhichBoard(String response) {

        if (response.equals("init")) {
            try {
                jsonWriter.open();
                jsonWriter.write(initialColony);
                jsonWriter.close();
                System.out.println("Initial board has been saved!");
            } catch (IOException e) {
                System.out.println("Unable to save, sorry.");
            }
        } else if (response.equals("current")) {
            try {
                jsonWriter.open();
                jsonWriter.write(colony);
                jsonWriter.close();
                System.out.println("Current board has been saved!");
            } catch (IOException e) {
                System.out.println("Unable to save, sorry.");
            }
        }
    }


    // EFFECTS: prints the grid with cells
    public void printGrid() {
        board.fillBoard();
        colony.insertCells(board);
        for (int i = 0; i < board.getHeight(); i++) {
            String border = "|";
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getValue(j, i) == 0) {
                    border += ".";
                } else {
                    border += "0";
                }
            }
            border += "|";
            System.out.println(border);
        }
        System.out.println("");
        colony.filter(board);
    }

}
