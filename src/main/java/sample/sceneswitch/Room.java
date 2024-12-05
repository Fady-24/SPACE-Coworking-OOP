package sample.sceneswitch;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Room implements Serializable {
    protected String room_name;
    protected int room_Id;
    protected int numofvisitors;
    protected ArrayList<Visitor> List_of_Visitors;
    protected ArrayList<Slots> List_of_Slots;
    protected int numberofslots;

    public Room(String room_name, int room_Id) {
        this.room_name = room_name;
        this.room_Id = room_Id;
    }
    public void New_Visitor(Visitor visitor){
        List_of_Visitors.add(visitor);
    }
    public void New_Slot(Slots slot){
        List_of_Slots.add(slot);
    }
    public abstract boolean fully_booked ();

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_Id() {
        return room_Id;
    }

    public void setRoom_Id(int room_Id) {
        this.room_Id = room_Id;
    }

    public  int getNumofvisitors() {
        return numofvisitors;
    }

    public void setNumofvisitors(int numofvisitors) {
        this.numofvisitors = numofvisitors;
    }
}