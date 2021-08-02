package persistence;

import org.json.JSONObject;

// Code based on JsonSerializationDemo in phase 2 instructions
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
