package model;

import java.util.ArrayList;
import java.util.List;

public class Colony {

    private List<Cell> cellColony;

    // EFFECTS: creates new colony with zero cells
    public Colony() {
        cellColony = new ArrayList<>();
    }

    // REQUIRES: Cell to have different position than all other cells in list
    // MODIFIES: this
    // EFFECTRS: adds cell to colony
    public void addCell(Cell c) {
        this.cellColony.add(c);
    }

    // MODIFIES: this
    // if cell in list removes cell from colony and kills it, else do nothing
    public void delete(Cell cell) {
        if (cellColony.contains(cell)) {
            cellColony.remove(cell);
            cell.kill();
        }
    }

    // MODIFIES: board
    // EFFECTS: inserts all cells in cellColony into a board, if empty do nothing
    public void insertCells(Board board) {
        for (Cell cell : cellColony) {
            int x = cell.getPosX();
            int y = cell.getPosY();
            board.setBoard(x, y);
        }
    }

    public int getSize() {
        return cellColony.size();
    }

    public boolean contain(Cell c) {
        return cellColony.contains(c);
    }


}
