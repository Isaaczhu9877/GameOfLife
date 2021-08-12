package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//JUnit tests for board class
public class BoardTest {

    Board board;

    @BeforeEach
    public void setUp () {
        board = new Board();
        board.fillBoard();
    }

    @Test
    public void testFillBoard(){

        for (int x = 0; x < board.getWidth(); x++){
            for (int y = 0; y < board.getHeight(); y++) {
                try {
                    assertEquals(board.getValue(x, y), 0);
                } catch (InvalidCoordinateException e) {
                    fail("Unexpected exception");
                }
            }
        }
    }

    @Test
    public void testCheckSurroundingEmpty() {
        assertEquals(board.checkSurrounding(15, 15), 0);

    }

    @Test
    public void testCheckSurrounding2() {
        try {
            board.setBoard(1,1, 1);
            board.setBoard(2,1,1);
        } catch (InvalidCoordinateException e) {
            fail("Unexpected exception");
        }
        assertEquals(board.checkSurrounding(2, 1), 2);
    }

    @Test
    public void testCheckSurroundingUpDown() {
        try {
            board.setBoard(1,1, 1);
            board.setBoard(1,2,1);
            board.setBoard(1,3,1);
        } catch (InvalidCoordinateException e) {
            fail("Unexpected exception");
        }
        assertEquals(board.checkSurrounding(2, 1), 2);
    }

    @Test
    public void testCheckSurrounding8() {
        try {
            board.setBoard(1,1, 1);
            board.setBoard(1,2,1);
            board.setBoard(1,3,1);
            board.setBoard(2,1,1);
            board.setBoard(2,3,1);
            board.setBoard(3,1,1);
            board.setBoard(3,2,1);
            board.setBoard(3,3,1);
        } catch (InvalidCoordinateException e) {
            fail("Unexpected exception");
        }
        assertEquals(board.checkSurrounding(2, 2), 8);
    }

    @Test
    public void testSetBoardInBoard() {
        try {
            board.setBoard(1,1,1);
            assertEquals(board.getValue(1,1), 1);
        } catch (InvalidCoordinateException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testSetBoardNotInBoard() {
        try {
            board.setBoard(10000,100000,1);
            assertEquals(board.getValue(10000,100000), 1);
            fail("Unexpected exception");
        } catch (InvalidCoordinateException e) {

        }
    }


    @Test
    public void testGetValueInBoard() {
        try {
            board.setBoard(1,1,1);
            assertEquals(board.getValue(1,1), 1);
        } catch (InvalidCoordinateException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testGetValueNotInBoard() {
        try {
            board.setBoard(1,1,1);
            assertEquals(board.getValue(10000,10000), 1);
            fail("Unexpected exception");
        } catch (InvalidCoordinateException e) {

        }
    }

}
