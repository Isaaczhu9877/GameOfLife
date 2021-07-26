package ui;

import model.Board;
import model.Cell;
import model.Colony;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

// Class that is used to start a new game and render the grid
public class LaunchGame {
    int height;
    int width;
    Colony colony;
    Board board;
    Scanner scanner = new Scanner(System.in);


    // EFFECTS: Creates new LaunchGame object with a colony and board begins the game
    public LaunchGame() {
        setBoardSize();
        colony = new Colony();
        board = new Board(width, height);
        runGame();
        System.out.println("Thank you for playing!");

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
    // EFFECTS: asks user for cells that they want to add to the initial game state
    public void runGame() {
        while (true) {
            System.out.println("Please input the coordinates of cell you want to add with the exact format: \"X,Y\"."
                    + " X and Y both need to be >= zero"
                    + " If you are done placing cells and want to begin the game please type \"Start\"!");
            String command = scanner.nextLine();
            if (command.equals("Start")) {
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
        beginSimulation();
    }

    // EFFECTS: prints board until user wants to stop
    public void beginSimulation() {
        while (true) {
            printGrid();
            System.out.println("Input \"exit\" to quit and reset the simulation.");
            System.out.println("Input anything else to continue to the next generation");

            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
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
