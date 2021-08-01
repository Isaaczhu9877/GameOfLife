package persistence;

import model.Cell;
import model.Colony;

import java.io.IOException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// based on the structure given in JsonSerializationDemo
// represents a JSON reader that creates object from given JSON file
public class JsonReader {
    private String source;

    // EEFFECTS: Constructs reader for source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Colony from file and returns it, if cant read throws IOException
    public Colony read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return setUpObject(jsonObject);
    }

    // EFFECTS: reads file and returns string of it
    public String readFile(String source) throws IOException {
        return new String(Files.readAllBytes(Paths.get(source)));
    }


    // EFFECTS: creates colony object from Json file
    public Colony setUpObject(JSONObject jsonObject) {
        Colony colony = new Colony();
        JSONArray cellArray = jsonObject.getJSONArray("cells");
        for (Object cell : cellArray) {
            JSONObject nextCell = (JSONObject) cell;
            int posX = nextCell.getInt("posX");
            int posY = nextCell.getInt("posY");
            Cell newCell = new Cell(posY, posX);
            colony.addCell(newCell);
        }
        return colony;
    }

}
