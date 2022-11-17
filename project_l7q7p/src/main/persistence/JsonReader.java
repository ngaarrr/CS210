package persistence;

import model.MyEvent;
import model.Guest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// The code in the class below is from the JsonReader class,
// in the JsonSerializationDemo project given as an example.
// Represents a reader that reads event from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads event from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyEvent read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEvent(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses event from JSON object and returns it
    private MyEvent parseEvent(JSONObject jsonObject) {
        String name = jsonObject.getString("name of event");
        int capacity = jsonObject.getInt("capacity of event");
        MyEvent event = new MyEvent(name, capacity);
        addGuestList(event, jsonObject);
        return event;
    }

    // MODIFIES: event
    // EFFECTS: parses list of guests from JSON object and adds them to event
    private void addGuestList(MyEvent event, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("guest list");
        for (Object json : jsonArray) {
            JSONObject nextGuest = (JSONObject) json;
            addGuest(event, nextGuest);
        }
    }

    // MODIFIES: event
    // EFFECTS: parses guest from JSON object and adds it to event
    private void addGuest(MyEvent event, JSONObject jsonObject) {
        String name = jsonObject.getString("name of guest");
        String gender = jsonObject.getString("gender of guest");
        int age = jsonObject.getInt("age of guest");
        Guest guest = new Guest(name, gender, age);
        event.addGuest(guest);
    }
}
