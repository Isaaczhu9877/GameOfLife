package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.WritableClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// represents an array of Cells used to track living cells on the game board
public class Colony implements WritableClass {

    private Set<Cell> colony;

    // EFFECTS: creates new colony with zero cells
    public Colony() {
        colony = new HashSet<>();
    }

    // MODIFIES: this
    // EFFECTS: adds cell to colony
    public void addCell(Cell c) {
        this.colony.add(c);

    }

    // MODIFIES: this
    // if cell in list removes cell from colony and kills it, else do nothing
    public void delete(Cell cell) {
        if (colony.contains(cell)) {
            colony.remove(cell);
            cell.kill();
        }
    }

    // MODIFIES: board
    // EFFECTS: inserts all cells in cellColony into a board, if empty do nothing
    public void insertCells(Board board) {
        for (Cell cell : colony) {
            int x = cell.getPosX();
            int y = cell.getPosY();
            try {
                board.setBoard(x, y, 1);
            } catch (InvalidCoordinateException e) {
                //continue
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: checks every cell's neighbours and filters based on the requirements
    // if neighbours are more than 3 or less than 2 remove te cell from list and kill it
    // else do nothing
    // if empty square has 3 neighbours make cell there and add to list
    public void filter(Board board) {
        List<Cell> deadList = new ArrayList<>();
        List<Cell> aliveList = new ArrayList<>();
        for (int row = 0; row < board.getHeight(); row++) {
            for (int column = 0; column < board.getWidth(); column++) {
                int numOfNeighbours = board.checkSurrounding(row, column);
                Cell cell = new Cell(row, column);
                if (numOfNeighbours > 3 || numOfNeighbours < 2) {
                    if (colony.contains(cell)) {
                        deadList.add(cell);
                    }
                }  else if (numOfNeighbours == 3 && !(colony.contains(cell))) {
                    aliveList.add(cell);
                }
            }
        }
        for (Cell cell : deadList) {
            colony.remove(cell);
            organizeColony(board, cell, "dead");
        }
        for (Cell cell : aliveList) {
            colony.add(cell);
            organizeColony(board, cell, "alive");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets cell position on board to dead or alive
    public void organizeColony(Board board, Cell cell, String status) {
        try {
            if (status.equals("dead")) {
                board.setBoard(cell.getPosX(), cell.getPosY(), 0);
            } else  {
                board.setBoard(cell.getPosX(), cell.getPosY(), 1);
            }
        } catch (InvalidCoordinateException e) {
            //continue
        }
    }


    // EFFECTS: returns the size of the Colony
    public int getSize() {
        return colony.size();
    }

    // EFFECTS: returns if the Colony contains a cell at a certain position
    public boolean contain(Cell c) {
        return colony.contains(c);
    }

    //MODIFIES: this
    // EFFECTS: clears all cells in colony
    public void wipe() {
        colony.clear();

    }

    // EFFECTS: creates Json representation of colony
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cells", cellsToJson());
        return json;
    }

    // EFFECTS: creates Json representation of each cell in Colony
    public JSONArray cellsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Cell cell : colony) {
            jsonArray.put(cell.toJson());
        }
        return jsonArray;
    }

}
