package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    private Guest testGuest;

    @BeforeEach
    void runBefore() {
        testGuest = new Guest("Negar Lajevardi", "Female", 20);
    }

    @Test
    void testConstructor() {
        assertEquals("Negar Lajevardi", testGuest.getNameOfGuest());
        assertEquals("Female", testGuest.getGenderOfGuest());
        assertEquals(20, testGuest.getAgeOfGuest());

    }
}