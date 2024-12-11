package sample.sceneswitch;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

public class Slots implements Serializable
{
    private int ID;
    private LocalDate date;
    private LocalTime timef,timet;
    private int fees;
    private Boolean reserved;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    private String roomName;
    public Slots(LocalDate date, LocalTime timef,LocalTime timet, int fees,String roomName)
    {
        this.date = date;
        this.timef = timef;
        this.timet = timet;
        this.fees = fees;
        this.roomName = roomName;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public int getFees() {
        return fees;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public String preview(){
        String t = this.timef.toString()+ " to "+this.timet.toString();
        return t;
    }

    public LocalTime getTimef() {
        return timef;
    }

    public LocalTime getTimet() {
        return timet;
    }
}
