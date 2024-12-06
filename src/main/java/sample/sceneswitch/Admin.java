package sample.sceneswitch;

import java.sql.Date;
import java.sql.Time;

public class Admin {
    private String name;
    private String password;
    public Admin(String name,String password)
    {
        this.name=name;
        this.password=password;
    }
    public Slots AddSlots(String roomtype, int roomid, Date date, Time time,Float fees)
    {
        Slots newSlot=new Slots(date,time,fees);
        //create room and determine the type
        return newSlot;
    }
    public void DisplaySlotsForAllRooms(Date date, Time time, Slots slot[])
    {
        int  numOfSlots=10;   //when rooms is created this num will increase
        for(int i=0;i<numOfSlots;i++)
        {

        }
    }
    public void DisplayVisitorData(Visitor visitors[])
    {

    }
    public void DisplayRoomsData()
    {

    }
}
