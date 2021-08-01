package persistence;

import model.Cell;
import model.Colony;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// represents a test suite for the class JsonWriter
public class JsonWriterTest {
    Colony colony;
    Cell cell1;
    Cell cell2;
    Cell cell3;

    @BeforeEach
    public void setUp() {
        colony = new Colony();
        cell1 = new Cell(3, 4);
        cell2 = new Cell(3, 5);
        cell3 = new Cell(3, 6);
    }

    @Test
    public void testWriterIncorrectPath() {
        try {
            JsonWriter writer = new JsonWriter("./data/\0illeg<>>****alBadThingy;;;;;;;a[pl[pl.json");
            writer.open();
            fail("Exception was expected");
        } catch (IOException e) {

        }
    }

    @Test
    public void testWriterNoCells() {
        try {
            JsonWriter writer = new JsonWriter("./data/emptyWriterTest.json");
            writer.open();
            writer.write(colony);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyWriterTest.json");
            colony = reader.read();
            assertEquals(colony.getSize(), 0);
        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testWriterThreeCells() {
        try {
            colony.addCell(cell1);
            colony.addCell(cell2);
            colony.addCell(cell3);
            JsonWriter writer = new JsonWriter("./data/WriterTest.json");
            writer.open();
            writer.write(colony);
            writer.close();

            JsonReader reader = new JsonReader("./data/WriterTest.json");
            colony = reader.read();
            assertEquals(colony.getSize(), 3);
            assertTrue(colony.contain(cell1));
            assertTrue(colony.contain(cell2));
            assertTrue(colony.contain(cell3));
        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }

}
