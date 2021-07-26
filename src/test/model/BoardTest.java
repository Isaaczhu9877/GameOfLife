package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//JUnit tests for board class
public class BoardTest {

    Board board;

    @BeforeEach
    public void setUp () {
        board = new Board(100, 30);
        board.fillBoard();
    }

    @Test
    public void testFillBoard(){

        for (int x = 0; x < board.getWidth(); x++){
            for (int y = 0; y < board.getHeight(); y++) {
                assertEquals(board.getValue(x, y), 0);
            }
        }
    }

    @Test
    public void testCheckSurroundingEmpty() {
        assertEquals(board.checkSurrounding(15, 15), 0);

    }

    @Test
    public void testCheckSurrounding2() {
        board.setBoard(1,1, 1);
        board.setBoard(2,1,1);

        assertEquals(board.checkSurrounding(2, 1), 2);

    }

    @Test
    public void testCheckSurroundingUpDown() {
        board.setBoard(1,1, 1);
        board.setBoard(1,2,1);
        board.setBoard(1,3,1);

        assertEquals(board.checkSurrounding(2, 1), 2);

    }

    @Test
    public void testCheckSurrounding8() {
        board.setBoard(1,1, 1);
        board.setBoard(1,2,1);
        board.setBoard(1,3,1);
        board.setBoard(2,1,1);
        board.setBoard(2,3,1);
        board.setBoard(3,1,1);
        board.setBoard(3,2,1);
        board.setBoard(3,3,1);

        assertEquals(board.checkSurrounding(2, 2), 8);

    }

}
