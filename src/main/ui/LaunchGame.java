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
    private int height;
    private int width;
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
        loadSave();
        colony = new Colony();
        runGame();
        System.out.println("Thank you for playing!");

    }

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
                System.out.println("Loaded board form last save");
            } catch (IOException e) {
                System.out.println("Unable to read from file, please create new board");
            }
        }
    }

    // EFFECTS: asks the user for the height and width of the board
    public void setBoardSize() {
        System.out.println("What dimensions do you want to grid to have? Please input the dimensions in the form "
                + "width,height with both width and height > 0");
        String input = scanner.nextLine();
        String[] posXAndY = input.split(",");
        try {
            int posX = parseInt(posXAndY[0]);
            int posY = parseInt(posXAndY[1]);
            if (posX < 0 || posY < 0) {
                System.out.println("Invalid input try again");
            } else {
                height = posY;
                width = posX;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input try again");
        }

    }

    // MODIFIES: this
    // EFFECTS: asks user for board size and cells to insert IF the board was not loaded from save
    // If loaded, begin simulation
    public void runGame() {
        if (colony.getSize() > 0) {
            initialColony = colony;
            beginSimulation();
        } else {
            setBoardSize();
            board = new Board(width, height);
            insertCellToGrid();
            initialColony = colony;
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

    //MODIFIES: this
    // EFFECTS: Asks user if they want to save their initial board
    public void saveBoard() {
        System.out.println("Would you like to save your initial board?");
        System.out.println("y -> yes");
        System.out.println("n -> no");
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
            try {
                jsonWriter.open();
                jsonWriter.write(initialColony);
                jsonWriter.close();
                System.out.println("Initial board has been saved!");
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
