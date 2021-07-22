package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {

    Board board;

    @BeforeEach
    public void setUp () {
        board = new Board();
    }

    @Test
    public void testFillBoard(){
        board.fillBoard();
        for (int x = 0; x < board.WIDTH; x++){
            for (int y = 0; y < board.HEIGHT; y++ ) {
                assertEquals(board.getValue(x, y), 0);
            }
        }
    }
}
