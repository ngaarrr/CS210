package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private MyEvent testEvent;
    private Guest guest1;
    private Guest guest2;
    private Guest guest3;
    private Guest guest4;
    private Guest guest5;
    private Guest guest6;


    @BeforeEach
    void runBefore() {
        testEvent = new MyEvent("New Year's party", 5);
        guest1 = new Guest("Ryse Pieces", "Female", 12);
        guest2 = new Guest("Anthony Pacho", "Male", 18);
        guest3 = new Guest("Alican Vural", "Male", 20);
        guest4 = new Guest("Siavash Farjadi", "Male", 39);
        guest5 = new Guest("Magic Polan", "Male", 31);
        guest6 = new Guest("Jessica Q", "Female", 19);
    }

    @Test
    void testConstructor() {
        assertEquals("New Year's party", testEvent.getNameOfEvent());
        assertEquals(5, testEvent.getCapacityOfEvent());
        assertEquals(0, testEvent.currentNumOfGuests());
        assertEquals(0, testEvent.getGuestList().size());
    }

    @Test
    void testAddGuest() {
        testEvent.addGuest(guest1);
        assertEquals(1, testEvent.getGuestList().size());
        assertEquals(guest1, testEvent.getGuestList().get(0));
        assertEquals(1, testEvent.currentNumOfGuests());
        testEvent.addGuest(guest2);
        assertEquals(2, testEvent.getGuestList().size());
        assertEquals(guest2, testEvent.getGuestList().get(1));
        assertEquals(2, testEvent.currentNumOfGuests());

        testEvent.addGuest(guest3);
        testEvent.addGuest(guest4);
        testEvent.addGuest(guest5);
        assertEquals(5, testEvent.getGuestList().size());
        assertEquals(guest5, testEvent.getGuestList().get(4));
        assertEquals(5, testEvent.currentNumOfGuests());

        testEvent.addGuest(guest6);
        assertEquals(5, testEvent.getGuestList().size());
        assertEquals(guest5, testEvent.getGuestList().get(4));
        assertEquals(5, testEvent.currentNumOfGuests());
    }

    @Test
    void testRemoveGuest() {
        testEvent.addGuest(guest1);
        testEvent.addGuest(guest2);
        testEvent.addGuest(guest3);

        testEvent.removeGuest(guest3);
        assertEquals(2, testEvent.getGuestList().size());
        assertEquals(guest2, testEvent.getGuestList().get(1));
        assertEquals(guest1, testEvent.getGuestList().get(0));
        assertEquals(2, testEvent.currentNumOfGuests());

        testEvent.removeGuest(guest1);
        assertEquals(1, testEvent.getGuestList().size());
        assertEquals(guest2, testEvent.getGuestList().get(0));
        assertEquals(1, testEvent.currentNumOfGuests());
    }

    @Test
    void testRemoveGuestByName() {
        testEvent.addGuest(guest1);
        testEvent.addGuest(guest2);
        testEvent.addGuest(guest3);
        testEvent.addGuest(guest4);
        testEvent.addGuest(guest5);

        testEvent.removeGuestByName("Ryse Pieces");
        assertEquals(4, testEvent.getGuestList().size());
        assertEquals(guest2, testEvent.getGuestList().get(0));
        assertEquals(guest3, testEvent.getGuestList().get(1));
        assertEquals(guest4, testEvent.getGuestList().get(2));
        assertEquals(guest5, testEvent.getGuestList().get(3));

        testEvent.removeGuestByName("Alican Vural");
        assertEquals(3, testEvent.getGuestList().size());
        assertEquals(guest2, testEvent.getGuestList().get(0));
        assertEquals(guest4, testEvent.getGuestList().get(1));
        assertEquals(guest5, testEvent.getGuestList().get(2));
    }

    @Test
    void testRemoveGuestByNameDoesntExist() {
        testEvent.addGuest(guest1);
        testEvent.addGuest(guest2);
        testEvent.addGuest(guest3);
        testEvent.addGuest(guest4);
        testEvent.addGuest(guest5);

        testEvent.removeGuestByName("Negar Lajevardi");
        assertEquals(5, testEvent.getGuestList().size());
    }

    @Test
    void testGuestListUnderAge() {
        testEvent.addGuest(guest1);
        testEvent.addGuest(guest2);
        testEvent.addGuest(guest3);
        testEvent.addGuest(guest4);
        testEvent.addGuest(guest5);

        assertEquals(2, testEvent.getGuestListUnderAge().size());
        assertEquals(guest1, testEvent.getGuestListUnderAge().get(0));
        assertEquals(guest2, testEvent.getGuestListUnderAge().get(1));
    }

    @Test
    void testGuestListLegalAge() {
        testEvent.addGuest(guest1);
        testEvent.addGuest(guest2);
        testEvent.addGuest(guest3);
        testEvent.addGuest(guest4);
        testEvent.addGuest(guest5);

        assertEquals(3, testEvent.getGuestListLegalAge().size());
        assertEquals(guest3, testEvent.getGuestListLegalAge().get(0));
        assertEquals(guest4, testEvent.getGuestListLegalAge().get(1));
        assertEquals(guest5, testEvent.getGuestListLegalAge().get(2));
    }

    @Test
    void testIsGuestListEmptyTrue() {
        assertTrue(testEvent.isGuestListEmpty());
    }

    @Test
    void testIsGuestListEmptyFalseOneGuest() {
        testEvent.addGuest(guest1);
        assertFalse(testEvent.isGuestListEmpty());
    }

    @Test
    void testIsGuestListEmptyFalseMultipleGuest() {
        testEvent.addGuest(guest1);
        testEvent.addGuest(guest2);
        testEvent.addGuest(guest3);

        assertFalse(testEvent.isGuestListEmpty());
    }

    @Test
    void testCurrentNumberOfGuests() {
        assertEquals(0, testEvent.currentNumOfGuests());

        testEvent.addGuest(guest3);
        assertEquals(1, testEvent.currentNumOfGuests());

        testEvent.addGuest(guest6);
        testEvent.addGuest(guest4);
        assertEquals(3, testEvent.currentNumOfGuests());
    }
}


