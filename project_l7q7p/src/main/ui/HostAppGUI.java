package ui;

import model.Event;
import model.EventLog;
import model.MyEvent;
import model.Guest;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

// SOURCE: https://docs.oracle.com/javase/tutorial/uiswing/components/index.html
// Represents the graphical host application
public class HostAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/myEvent.json";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;
    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 100;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private MyEvent myEvent;
    private JPanel mainPanel;
    private JTextField textFieldChosenGuest;
    private JTextField textFieldName;
    private JTextField textFieldGender;
    private JTextField textFieldAge;
    private JTextArea textAreaGuestDetail;
    private JTextArea textAreaGuestList;
    private JLabel labelAddGuest;
    private JLabel labelRemoveGuest;
    private JLabel labelSave;
    private JLabel labelLoad;
    private JButton buttonAddGuest;
    private JButton buttonDisplayGuestList;
    private JButton buttonShowInfo;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JButton buttonRemoveGuest;
    private ImageIcon image;

    // MODIFIES: this, MyEvent
    // EFFECTS: Runs the graphical host application
    public HostAppGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        myEvent = new MyEvent("My Event", 200);

        setImageIcon();

        setUpMainFrame();

        buttonAddGuest.addActionListener(e -> addGuest());

        buttonRemoveGuest.addActionListener(e -> removeGuest());

        buttonDisplayGuestList.addActionListener(e -> displayGuestList());

        buttonSave.addActionListener(e -> saveEvent());

        buttonLoad.addActionListener(e -> loadEvent());

        buttonShowInfo.addActionListener(e -> printGuestDetails());
    }

    // SOURCE: https://www.youtube.com/watch?v=aIdIXsi1qTU
    // MODIFIES: this
    // EFFECTS: sets up the main frame
    private void setUpMainFrame() {
        this.setContentPane(mainPanel);
        this.setTitle("Guest Planner");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        // SOURCE: https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
    }

    // CODE SOURCE: https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010619820-Intellij-can-not-resolve-path-for-image-in-resources-folder-on-local-and-absolute-paths
    //              https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    // IMAGE SOURCE: "https://flyclipart.com/deep-pink-thumbs-up-icon-thumbs-up-png-410472">Deep Pink Thumbs Up Icon - Thumbs Up PNG</a>
    // EFFECTS: loads the image from source,
    //          and scales it.
    private void setImageIcon() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("PinkThumbsUp.jpg");
        assert url != null;
        image = new ImageIcon(url);
        Image imageHolder = image.getImage();
        Image newImage = imageHolder.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(newImage);
    }


    // MODIFIES: this, MyEvent
    // EFFECTS: creates and adds a guest to the guest list,
    //          if capacity has not been reached.
    //          if the capacity has been reached,
    //          display an error, otherwise,
    //          displays a message that the guest has been created,
    //          and added.
    private void addGuest() {
        String guestName = textFieldName.getText();
        String guestGender = textFieldGender.getText();
        int guestAge = Integer.parseInt(textFieldAge.getText());
        Guest guest = new Guest(guestName, guestGender, guestAge);
        myEvent.addGuest(guest);
        int indexCheck = myEvent.getGuestList().size();
        if (guest.equals(myEvent.getGuestList().get(indexCheck - 1))) {
            labelAddGuest.setText("\"" + guestName + "\"" + " has been created and added to your guest list!");
        } else {
            String errorMessage = guest.getNameOfGuest() + " could not be added, you have reached the capacity.\n";
            JOptionPane.showMessageDialog(this, errorMessage);
        }

    }

    // MODIFIES: this, MyEvent
    // EFFECTS: removes a guest from the guest list,
    //          displays a message that the guest has been removed
    private void removeGuest() {
        String guestName = textFieldChosenGuest.getText();
        myEvent.removeGuestByName(guestName);
        labelRemoveGuest.setText(guestName + " has been removed from the guest list.");
    }

    // EFFECTS: displays a list of the guests.
    private void displayGuestList() {
        ArrayList<String> guestListNames = new ArrayList<>();
        ArrayList<Guest> eventGuestList = myEvent.getGuestList();
        for (Guest guest : eventGuestList) {
            guestListNames.add(guest.getNameOfGuest());
        }

        textAreaGuestList.setText(null);
        if (guestListNames.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your guest list is empty");
        }
        for (String guest : guestListNames) {
            textAreaGuestList.append(guest + "\n");
        }
        textAreaGuestList.setVisible(true);
    }


    // EFFECTS: displays details of the chosen guest
    private void printGuestDetails() {
        String guestName = textFieldChosenGuest.getText();
        ArrayList<Guest> guestList = myEvent.getGuestList();
        textAreaGuestDetail.setText(null);
        for (Guest guest : guestList) {
            if (guest.getNameOfGuest().equals(guestName)) {
                String detail = guest.getNameOfGuest() + ", " + guest.getGenderOfGuest() + ", " + guest.getAgeOfGuest();
                textAreaGuestDetail.setText(detail);
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
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
        labelSave.setText("Your guest list has been saved.");
        labelSave.setIcon(image);
        labelSave.setVisible(true);
    }

    // Used the code from the WorkRoomApp class,
    // in the JsonSerializationDemo project given as an example
    // MODIFIES: this
    // EFFECTS: loads event from file
    private void loadEvent() {
        try {
            myEvent = jsonReader.read();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
        labelLoad.setText("Your guest list has been loaded.");
    }

    private void printLog() {
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }

    }

    public static void main(String[] args) {
        new HostAppGUI();
    }

}
