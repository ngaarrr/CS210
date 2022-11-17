package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a guest having a name, gender, and age.
public class Guest implements Writable {
    private final String nameOfGuest;
    private final String genderOfGuest;
    private final int ageOfGuest;

    /*
     * REQUIRES: name is not an empty string
     *           gender is not an empty string
     *           age is > 0
     * EFFECTS: constructs a guest with a name, gender, and age.
     */
    public Guest(String name, String gender, int age) {
        nameOfGuest = name;
        genderOfGuest = gender;
        ageOfGuest = age;
    }

    public String getNameOfGuest() {
        return nameOfGuest;
    }

    public String getGenderOfGuest() {
        return genderOfGuest;
    }

    public int getAgeOfGuest() {
        return ageOfGuest;
    }

    // Used the code from the Thingy class,
    // in the JsonSerializationDemo project given as an example
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name of guest", nameOfGuest);
        json.put("gender of guest", genderOfGuest);
        json.put("age of guest", ageOfGuest);
        return json;
    }
}
