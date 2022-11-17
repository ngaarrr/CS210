package persistence;

import model.MyEvent;
import model.Guest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The code in the class below is from the JsonReaderTest class,
// in the JsonSerializationDemo project given as an example.
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MyEvent event = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyEvent() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEvent.json");
        try {
            MyEvent event = reader.read();
            assertEquals("My Event", event.getNameOfEvent());
            assertEquals(0, event.currentNumOfGuests());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalEvent() {
        JsonReader reader = new JsonReader("./data/testReaderNormalEvent.json");
        try {
            MyEvent event = reader.read();
            assertEquals("My Event", event.getNameOfEvent());
            List<Guest> guestList = event.getGuestList();
            assertEquals(2, guestList.size());
            checkGuest("Batool Zarei", "Female", 53, guestList.get(0));
            checkGuest("Hassan Lajevardi", "Male", 54, guestList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
