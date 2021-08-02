package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// based on the structure given in JsonSerializationDemo

// interface for classes that will need to be serialized using JSON
public interface WritableClass {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
