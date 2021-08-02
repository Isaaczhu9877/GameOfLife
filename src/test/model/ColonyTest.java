package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//JUnit tests for Colony class
public class ColonyTest {
    Board board;
    Colony colony;
    Cell cell;
    Cell cell2;
    Cell cell3;
    Cell cell4;
    Cell cell5;
    @BeforeEach
    public void setUp() {
        board = new Board();
        board.fillBoard();
        colony = new Colony();
        cell = new Cell(0,0);
        cell2 = new Cell(1,0);
        cell3 = new Cell(board.getHeight() - 1,0);
        cell4 = new Cell(0,board.getWidth() - 1);
        cell5 = new Cell(board.getHeight() - 1,board.getWidth() - 1);
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
        for (int x = 0; x < board.getWidth(); x++){
            for (int y = 0; y < board.getHeight(); y++ ) {
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
        assertEquals(board.getValue(0,1), 1);
    }
    @Test
    public void testFilterEmptyBoard() {
        colony.filter(board);
        for (int y = 0; y < board.getHeight(); y++){
            for (int x = 0; x < board.getWidth(); x++ ) {
                assertEquals(board.getValue(x, y), 0);
            }
        }
    }

    @Test
    public void testFilterOneCell() {
        colony.addCell(cell);
        colony.insertCells(board);
        assertEquals(board.getValue(cell.getPosX(), cell.getPosY()), 1);
        colony.filter(board);
        assertEquals(colony.getSize(), 0);
        assertEquals(board.getValue(cell.getPosX(), cell.getPosY()), 0);
    }
    @Test
    public void testRemove() {
        colony.addCell(cell);
        assertTrue(colony.contain(new Cell(0, 0)));
    }

    @Test
    public void testFilterMultipleCellsSpreadOut() {
        colony.addCell(cell);
        colony.addCell(cell2);
        colony.addCell(cell3);
        colony.addCell(cell4);
        colony.addCell(cell5);
        colony.insertCells(board);
        assertEquals(board.getValue(cell2.getPosX(), cell2.getPosY()), 1);
        assertEquals(board.getValue(cell3.getPosX(), cell3.getPosY()), 1);
        assertEquals(board.getValue(cell4.getPosX(), cell4.getPosY()), 1);
        assertEquals(board.getValue(cell5.getPosX(), cell5.getPosY()), 1);
        assertEquals(board.getValue(cell.getPosX(), cell.getPosY()), 1);
        colony.filter(board);
        assertEquals(board.getValue(cell2.getPosX(), cell2.getPosY()), 0);
        assertEquals(board.getValue(cell3.getPosX(), cell3.getPosY()), 0);
        assertEquals(board.getValue(cell4.getPosX(), cell4.getPosY()), 0);
        assertEquals(board.getValue(cell5.getPosX(), cell5.getPosY()), 0);
        assertEquals(board.getValue(cell.getPosX(), cell.getPosY()), 0);
        assertEquals(colony.getSize(), 0);
    }

    @Test
    public void testFilter3CellsTogether1Birth() {
        colony.addCell(cell);
        colony.addCell(new Cell(1, 0));
        colony.addCell(new Cell(0, 1));
        colony.insertCells(board);
        assertEquals(board.getValue(0, 1), 1);
        assertEquals(board.getValue(1, 0), 1);
        assertEquals(colony.getSize(), 3);
        assertEquals(board.getValue(cell.getPosX(), cell.getPosY()), 1);
        colony.filter(board);
        assertEquals(board.getValue(0, 1), 1);
        assertEquals(board.getValue(1, 0), 1);
        assertEquals(board.getValue(cell.getPosX(), cell.getPosY()), 1);
        assertEquals(board.getValue(1, 1), 1);
        assertEquals(colony.getSize(), 4);
    }

    @Test
    public void testFilter2Beside() {
        colony.addCell(new Cell(1,1));
        colony.addCell(new Cell(0, 1));
        colony.insertCells(board);
        colony.filter(board);
        assertEquals(board.getValue(1, 1), 0);
        assertEquals(board.getValue(1, 0), 0);
        assertEquals(colony.getSize(), 0);
    }
    @Test
    public void testFilter3UpDown() {
        colony.addCell(new Cell(1,1));
        colony.addCell(new Cell(0, 1));
        colony.addCell(new Cell(2, 1));
        colony.insertCells(board);
        colony.filter(board);
        assertEquals(board.getValue(1, 1), 1);
        assertEquals(board.getValue(1, 0), 0);
        assertEquals(board.getValue(1, 2), 0);
        assertEquals(board.getValue(0, 1), 1);
        assertEquals(board.getValue(2, 1), 1);
        assertEquals(colony.getSize(), 3);
    }

    @Test
    public void testFilter3Beside() {
        colony.addCell(new Cell(1,1));
        colony.addCell(new Cell(1, 2));
        colony.addCell(new Cell(1, 3));
        colony.insertCells(board);
        colony.filter(board);
        assertEquals(board.getValue(2, 1), 1);
        assertEquals(board.getValue(1, 1), 0);
        assertEquals(board.getValue(3, 1), 0);
        assertEquals(board.getValue(2, 0), 1);
        assertEquals(board.getValue(2, 2), 1);
        assertEquals(colony.getSize(), 3);

    }

    @Test
    public void testFilter3Neighbours() {
        colony.addCell(new Cell(1,1));
        colony.addCell(new Cell(1, 2));
        colony.addCell(new Cell(1, 3));
        colony.addCell(new Cell(2, 2));
        colony.insertCells(board);
        colony.filter(board);
        assertEquals(board.getValue(2, 1), 1);
        assertEquals(board.getValue(1, 1), 1);
        assertEquals(board.getValue(3, 1), 1);
        assertEquals(board.getValue(2, 2), 1);
        assertEquals(colony.getSize(), 7);
    }
    @Test
    public void testFilter8Cells() {
        colony.addCell(new Cell(1,1));
        colony.addCell(new Cell(1, 2));
        colony.addCell(new Cell(1, 3));
        colony.addCell(new Cell(2, 1));
        colony.addCell(new Cell(2, 2));
        colony.addCell(new Cell(2, 3));
        colony.addCell(new Cell(3, 1));
        colony.addCell(new Cell(3, 2));
        colony.addCell(new Cell(3, 3));
        colony.insertCells(board);
        colony.filter(board);

        assertEquals(colony.getSize(), 8);
    }
    @Test
    public void testFilter9Cells() {
        colony.addCell(new Cell(1,1));
        colony.addCell(new Cell(1, 2));
        colony.addCell(new Cell(1, 3));
        colony.addCell(new Cell(2, 1));
        colony.addCell(new Cell(2, 2));
        colony.addCell(new Cell(2, 3));
        colony.addCell(new Cell(3, 1));
        colony.addCell(new Cell(3, 2));
        colony.addCell(new Cell(3, 3));
        colony.insertCells(board);
        colony.filter(board);

        assertEquals(colony.getSize(), 8);
    }
}
