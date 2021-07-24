package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Board board;

    @BeforeEach
    public void setUp () {
        board = new Board();
        board.fillBoard();
    }

    @Test
    public void testFillBoard(){

        for (int x = 0; x < board.WIDTH; x++){
            for (int y = 0; y < board.HEIGHT; y++ ) {
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
