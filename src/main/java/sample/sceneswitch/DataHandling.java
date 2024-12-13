package sample.sceneswitch;

import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;

public class DataHandling {

    private static ArrayList<Visitor> visitors = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();

    public static ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    public static void setVisitors(ArrayList<Visitor> visitors) {DataHandling.visitors = visitors;
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static void setRooms(ArrayList<Room> rooms) {
        DataHandling.rooms = rooms;
    }

    public static void read_visitors() throws IOException {

        try
        {
            File f = new File("visitor.dat");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            visitors = (ArrayList<Visitor>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            write_visitors();

        }
    }

    public static void write_visitors() throws IOException {
        File f = new File("visitor.dat");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(visitors);
        oos.close();
    }

    public static ArrayList<Room> read_rooms() throws IOException {
        try
        {
            File f = new File("rooms.dat");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            rooms = (ArrayList<Room>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            write_rooms();

        }
        return null;
    }

    public static void write_rooms() throws IOException {
        File f = new File("rooms.dat");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(rooms);
        oos.close();
    }

    public static void handleClose(WindowEvent event) throws IOException {
        DataHandling.write_rooms();
        DataHandling.write_visitors();
        System.out.println("Saving");
    }
}
