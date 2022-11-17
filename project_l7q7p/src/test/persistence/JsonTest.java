package persistence;

import model.Guest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// The code in the class below is from the JsonTest class,
// in the JsonSerializationDemo project given as an example.
public class JsonTest {
    protected void checkGuest(String name, String gender, int age, Guest guest) {
        assertEquals(name, guest.getNameOfGuest());
        assertEquals(gender, guest.getGenderOfGuest());
        assertEquals(age, guest.getAgeOfGuest());
    }
}
