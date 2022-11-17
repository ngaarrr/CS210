package persistence;

import org.json.JSONObject;

public interface Writable {
    // Used the code from the Writable interface,
    // in the JsonSerializationDemo project given as an example
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
