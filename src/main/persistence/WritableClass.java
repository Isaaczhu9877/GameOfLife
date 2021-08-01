package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// interface for classes that will need to be serialized using JSON
public interface WritableClass {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
