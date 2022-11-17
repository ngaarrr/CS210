package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents an event having a name, date, capacity, and guest list.
public class MyEvent implements Writable {
    private final String nameOfEvent;
    private final int capacityOfEvent;
    private final ArrayList<Guest> guestList;


    /*
     * REQUIRES: name is not an empty string
     *           date is not an empty string
     *           capacity is > 0.
     * EFFECTS: constructs an event with a name, date, capacity,
     *          and an empty guest list.
     */
    public MyEvent(String name, int capacity) {
        nameOfEvent = name;
        capacityOfEvent = capacity;
        guestList = new ArrayList<>();
    }

    /*
     * REQUIRES: guest is not null.
     * MODIFIES: this
     * EFFECTS: if the event hasn't reached capacity, adds a guest to the guest list.
     */
    public void addGuest(Guest guest) {
        if (currentNumOfGuests() < capacityOfEvent) {
            EventLog.getInstance().logEvent(new Event("Guest added to the event."));
            guestList.add(guest);
        }
    }

    /*
     * REQUIRES: guest is not null.
     * MODIFIES: this
     * EFFECTS: remove the guest from the guest list.
     */
    public void removeGuest(Guest guest) {
        EventLog.getInstance().logEvent(new Event("Guest removed from the event."));
        guestList.remove(guest);
    }

    /*
     * REQUIRES: guestName is not null.
     * MODIFIES: this
     * EFFECTS: looks for the guest in the guest list by their name,
     *          and removes them from the guest list.
     */
    public void removeGuestByName(String guestName) {
        Guest removeGuest = null;
        for (Guest guest : guestList) {
            if (guest.getNameOfGuest().equals(guestName)) {
                removeGuest = guest;
            }
        }
        removeGuest(removeGuest);
    }

    /*
     * EFFECTS: returns the list of guests that are under 19.
     */
    public ArrayList<Guest> getGuestListUnderAge() {
        ArrayList<Guest> guestListUnderAge = new ArrayList<>();
        for (Guest guest : guestList) {
            if (guest.getAgeOfGuest() < 19) {
                guestListUnderAge.add(guest);
            }
        }
        return guestListUnderAge;
    }

    /*
     * EFFECTS: returns the list of guests that are 19 and over.
     */
    public ArrayList<Guest> getGuestListLegalAge() {
        ArrayList<Guest> guestListLegalAge = new ArrayList<>();
        for (Guest guest : guestList) {
            if (guest.getAgeOfGuest() >= 19) {
                guestListLegalAge.add(guest);
            }
        }
        return guestListLegalAge;
    }

    /*
     * EFFECTS: returns true if the list of guests is empty,
     *          false otherwise.
     */
    public boolean isGuestListEmpty() {
        return guestList.size() == 0;
    }

    // EFFECTS: returns the current number of guests in the event.
    public int currentNumOfGuests() {
        return guestList.size();
    }

    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    public String getNameOfEvent() {
        return nameOfEvent;
    }

    public int getCapacityOfEvent() {
        return capacityOfEvent;
    }

    // Used the code from the WorkRoom class,
    // in the JsonSerializationDemo project given as an example
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name of event", nameOfEvent);
        json.put("capacity of event", capacityOfEvent);
        json.put("guest list", guestListToJson());
        return json;

    }

    // Used the code from the WorkRoom class,
    // in the JsonSerializationDemo project given as an example
    // EFFECTS: returns the guests of this event as a JSON array
    private JSONArray guestListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Guest guest : guestList) {
            jsonArray.put(guest.toJson());
        }

        return jsonArray;

    }
}
