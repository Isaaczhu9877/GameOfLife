package persistence;

import model.Colony;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;

// based on the structure given in JsonSerializationDemo
// represents a writer that converts Colony to JSON and saves to file at given location
public class JsonWriter {
    private PrintWriter writer;
    private String fileLocation;

    // EFFECTS: creates writer for file at given location
    public JsonWriter(String location) {
        fileLocation = location;
    }

    //MODIFIES: this
    // EFFECTS: creates JSON representation of colony and saves it to file
    public void write(Colony colony) {
        JSONObject json = colony.toJson();
        writer.print(json.toString());
    }

    // opens writer and throws FIleNotFoundException if file at given location does not exist or can not be modified
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
