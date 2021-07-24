package ui;

import model.Board;
import model.Cell;
import model.Colony;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

// to game application
public class LaunchGame {
    Colony colony;
    Board board;
    Scanner scanner = new Scanner(System.in);

    public LaunchGame() {
        colony = new Colony();
        board = new Board();
        runGame();
        System.out.println("Thank you for playing!");

    }

    public void runGame() {
        while (true) {
            System.out.println("Please input the coordinates of cell you want to add with the exact format: \"x,y\"."
                    + " If you are done placing cells and want to begin the game please type \"Start\"!");
            String command = scanner.nextLine();
            if (command.equals("Start")) {
                break;
            } else {
                String[] posXAndY = command.split(",");
                try {
                    int posX = parseInt(posXAndY[0]);
                    int posY = parseInt(posXAndY[1]);
                    colony.addCell(new Cell(posY, posX));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input try again");
                }
            }

        }
        beginSimulation();
    }

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
