package sample.sceneswitch;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

public class Slots
{
    private int ID;
    private Date date, time;
    private float fees;
    public Slots(Date date, Time time, float fees)
    {
        this.date = date;
        this.time = time;
        this.fees = fees;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
}
