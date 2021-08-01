package persistence;

import model.Cell;
import model.Colony;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


// represents test cases for JsonReader class
public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/helloWorldByeBye.json");
        try {
             Colony colony = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    public void testReaderNoCells() {
        JsonReader reader = new JsonReader("./data/emptyReaderTest.json");
        try {
            Colony colony = reader.read();
            assertEquals(0, colony.getSize());
        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testReader() {
        JsonReader reader = new JsonReader("./data/testJsonReader.json");
        try {
            Colony colony = reader.read();
            assertEquals(3, colony.getSize());
            assertTrue(colony.contain(new Cell(4,4)));
            assertTrue(colony.contain(new Cell(4,5)));
            assertTrue(colony.contain(new Cell(4,6)));
        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }

}
