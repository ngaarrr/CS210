package persistence;

import model.MyEvent;
import model.Guest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// The code in the class below is from the JsonWriterTest class,
// in the JsonSerializationDemo project given as an example.
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MyEvent event = new MyEvent("My MyEvent", 300);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyEvent() {
        try {
            MyEvent event = new MyEvent("My MyEvent", 300);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyEvent.json");
            writer.open();
            writer.write(event);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEvent.json");
            event = reader.read();
            assertEquals("My MyEvent", event.getNameOfEvent());
            assertEquals(0, event.currentNumOfGuests());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalEvent() {
        try {
            MyEvent event = new MyEvent("My MyEvent", 300);
            event.addGuest(new Guest("Lindsay Chavez", "Female", 18));
            event.addGuest(new Guest("Batool Zarei", "Female", 53));
            JsonWriter writer = new JsonWriter("./data/testWriterNormalEvent.json");
            writer.open();
            writer.write(event);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalEvent.json");
            event = reader.read();
            assertEquals("My MyEvent", event.getNameOfEvent());
            List<Guest> guestList = event.getGuestList();
            assertEquals(2, guestList.size());
            checkGuest("Lindsay Chavez", "Female", 18, guestList.get(0));
            checkGuest("Batool Zarei", "Female", 53, guestList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
