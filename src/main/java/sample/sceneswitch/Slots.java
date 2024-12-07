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
    private float fees;
    public Slots(LocalDate date, LocalTime timef,LocalTime timet, float fees)
    {
        this.date = date;
        this.timef = timef;
        this.timet = timet;
        this.fees = fees;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String preview(){
        String t = this.timef.toString()+ " to "+this.timet.toString();
        return t;
    }
}
