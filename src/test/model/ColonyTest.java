package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColonyTest {
    Board board;
    Colony colony;
    Cell cell;
    Cell cell2;
    @BeforeEach
    public void setUp() {
        board = new Board();
        board.fillBoard();
        colony = new Colony();
        cell = new Cell(0,0);
        cell2 = new Cell(1,0);
    }
    @Test
    public void testColony() {
        assertEquals(colony.getSize(), 0);

    }
    @Test
    public void testAddCellOne() {

        assertEquals(colony.getSize(), 0);
        colony.addCell(cell);
        assertEquals(colony.getSize(), 1);
        assertTrue(colony.contain(cell));
    }
    @Test
    public void testAddCellTwo() {
        assertEquals(colony.getSize(), 0);
        colony.addCell(cell);
        colony.addCell(cell2);
        assertEquals(colony.getSize(), 2);
        assertTrue(colony.contain(cell));
        assertTrue(colony.contain(cell2));
    }
    @Test
    public void testRemoveInList() {
        assertEquals(colony.getSize(), 0);
        colony.addCell(cell);
        colony.addCell(cell2);
        colony.delete(cell);
        assertEquals(colony.getSize(), 1);
        assertFalse(colony.contain(cell));
        assertTrue(colony.contain(cell2));
    }
    @Test
    public void testRemoveNotInList() {
        assertEquals(colony.getSize(), 0);
        colony.addCell(cell);
        assertEquals(colony.getSize(), 1);
        assertTrue(colony.contain(cell));
        colony.delete(cell2);
        assertEquals(colony.getSize(), 1);
        assertTrue(colony.contain(cell));
    }
    @Test
    public void testInsertCellsNoCells() {
        assertEquals(colony.getSize(), 0);
        colony.insertCells(board);
        for (int x = 0; x < board.WIDTH; x++){
            for (int y = 0; y < board.HEIGHT; y++ ) {
                assertEquals(board.getValue(x, y), 0);
            }
        }
    }
    @Test
    public void testInsertCells() {
        assertEquals(colony.getSize(), 0);
        colony.addCell(cell);
        colony.addCell(cell2);
        colony.insertCells(board);
        assertEquals(board.getValue(0,0), 1);
        assertEquals(board.getValue(1,0), 1);
    }
}
