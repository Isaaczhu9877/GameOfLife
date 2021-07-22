package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell;

    @BeforeEach
    public void setUp() {
        cell = new Cell(0, 0);
    }

    @Test
    public void testCell(){
        assertEquals(cell.getPosX(), 0);
        assertEquals(cell.getPosY(), 0);
        assertEquals(cell.getStatus() , 1);
    }

    @Test
    public void testKillAlive() {
        cell.kill();
        assertEquals(cell.getStatus(), 0);
    }

    @Test
    public void testKillDead() {
        cell.setStatus(0);
        assertEquals(cell.getStatus(), 0);
        cell.kill();
        assertEquals(cell.getStatus(), 0);
    }
}