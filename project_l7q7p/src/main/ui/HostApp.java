package ui;

import model.MyEvent;
import model.Guest;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represents the Host application
public class HostApp {
    private static final String JSON_STORE = "./data/myEvent.json";
    private MyEvent myEvent;
    private Scanner input;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // EFFECTS:runs the host application
    public HostApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHost();
    }

    // Used the code from the TellerApp class,
    // in the Account project given as an example
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runHost() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("d")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for using the Host Application");

    }

    // Used the code from the TellerApp class,
    // in the Account project given as an example
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if ("add".equals(command)) {
            addGuest();
        } else if ("remove".equals(command)) {
            removeGuest();
        } else if ("view".equals(command)) {
            viewGuestList();
        } else if ("legal".equals(command)) {
            viewGuestListLegal();
        } else if ("underage".equals(command)) {
            viewGuestListUnderAge();
        } else if ("details".equals(command)) {
            printGuestDetails();
        } else if ("save".equals(command)) {
            saveEvent();
        } else if ("load".equals(command)) {
            loadEvent();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // Used the code from the TellerApp class,
    // in the Account project given as an example
    // MODIFIES: this
    // EFFECTS: initializes an event
    private void init() {
        myEvent = new MyEvent("My Event", 300);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // Used the code from the TellerApp class,
    // in the Account project given as an example
    // EFFECTS: displays menu of different options to user
    private void displayMenu() {
        System.out.println("\nWhat do you need help with?");
        System.out.println("\tadd -> add a guest to the guest list");
        System.out.println("\tremove -> remove a guest from the guest list");
        System.out.println("\tview ->  view the guest list");
        System.out.println("\tlegal ->  view the list of guests that are legal age");
        System.out.println("\tunderage ->  view the list of guests that are underage");
        System.out.println("\tdetails -> get the details about a specific guest");
        System.out.println("\tsave -> save event to file");
        System.out.println("\tload -> load event from file");
        System.out.println("\td -> done");
    }

    // MODIFIES: this
    // EFFECTS: creates and adds a guest to the guest list,
    //          if capacity has not been reached.
    private void addGuest() {
        Guest guest = createGuest();
        myEvent.addGuest(guest);

        int indexCheck = myEvent.getGuestList().size();
        if (guest.equals(myEvent.getGuestList().get(indexCheck - 1))) {
            System.out.println(guest.getNameOfGuest() + " has been added to the guest list\n");
        } else {
            System.out.println(guest.getNameOfGuest() + " could not be added, you have reached the capacity.\n");
        }
    }

    // MODIFIES: Guest
    // EFFECTS: creates a guest with the given name, gender and age.
    private Guest createGuest() {
        System.out.println("Enter the guest's full name: ");
        String name = input.next();

        System.out.println("Enter the guest's gender: ");
        String gender = input.next();

        System.out.println("Enter the guest's age: ");
        int age = input.nextInt();

        while (age <= 0) {
            System.out.println("Age cannot be zero or negative, please enter a proper age: ");
            age = input.nextInt();
        }

        return new Guest(name, gender, age);
    }

    // MODIFIES: this
    // EFFECTS: removes a guest from the guest list
    private void removeGuest() {
        System.out.println("Enter the name of the guest you want to remove: ");
        String guestName = input.next();

        myEvent.removeGuestByName(guestName);

        System.out.print(guestName + " has been removed from the guest list.");
    }


    // EFFECTS: prints out a list of the guests.
    private void viewGuestList() {
        if (myEvent.isGuestListEmpty()) {
            System.out.println("The guest list is empty.");
        } else {
            ArrayList<Guest> guestList = myEvent.getGuestList();
            System.out.println("The guest list for " + myEvent.getNameOfEvent() + ":");

            for (int counter = 0; counter < guestList.size(); counter++) {
                Guest guest = guestList.get(counter);
                System.out.println("guest" + (counter + 1) + " : " + guest.getNameOfGuest());
            }
        }
    }

    // EFFECTS: prints out a list of the guests 19 and over.
    private void viewGuestListLegal() {
        if (myEvent.isGuestListEmpty()) {
            System.out.println("The guest list is empty.");
        } else {
            System.out.println("The list of legal guests for " + myEvent.getNameOfEvent() + ":");
            ArrayList<Guest> guestListLegal = myEvent.getGuestListLegalAge();
            for (int counter = 0; counter < guestListLegal.size(); counter++) {
                Guest guest = guestListLegal.get(counter);
                System.out.println("guest" + (counter + 1) + " : " + guest.getNameOfGuest());
            }
        }
    }

    // EFFECTS: prints out a list of the guests under 19.
    private void viewGuestListUnderAge() {
        if (myEvent.isGuestListEmpty()) {
            System.out.println("The guest list is empty.");
        } else {
            System.out.println("The list of underage guests for " + myEvent.getNameOfEvent() + ":");
            ArrayList<Guest> guestListUnderAge = myEvent.getGuestListUnderAge();
            for (int counter = 0; counter < guestListUnderAge.size(); counter++) {
                Guest guest = guestListUnderAge.get(counter);
                System.out.println("guest" + (counter + 1) + " : " + guest.getNameOfGuest());
            }
        }
    }

    // EFFECTS: prints details of the chosen guest
    private void printGuestDetails() {
        ArrayList<Guest> guestList = myEvent.getGuestList();
        if (guestList.size() == 0) {
            System.out.println("The guest list is empty.");
        } else {
            System.out.println("Enter the name of the guest you want the details of: ");
            String name = input.next();

            for (Guest guest : guestList) {
                if (guest.getNameOfGuest().equals(name)) {
                    System.out.print(name + ", " + guest.getGenderOfGuest() + ", " + guest.getAgeOfGuest());
                }
            }
        }
    }

    // Used the code from the WorkRoomApp class,
    // in the JsonSerializationDemo project given as an example
    // EFFECTS: saves the workroom to file
    private void saveEvent() {
        try {
            jsonWriter.open();
            jsonWriter.write(myEvent);
            jsonWriter.close();
            System.out.println("Saved " + myEvent.getNameOfEvent() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Used the code from the WorkRoomApp class,
    // in the JsonSerializationDemo project given as an example
    // MODIFIES: this
    // EFFECTS: loads event from file
    private void loadEvent() {
        try {
            myEvent = jsonReader.read();
            System.out.println("Loaded " + myEvent.getNameOfEvent() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
