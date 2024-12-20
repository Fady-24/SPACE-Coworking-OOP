package sample.sceneswitch;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Visitor implements Serializable {
    private final int ID;
    private String name, password, type;
    private int hours, balance, extraFee, freehours, freehourscounter;
    private boolean rewardapplied;
    //    private int reserved_slot;
    public ArrayList<Slots> V_list_of_slots=new ArrayList<>();

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(int extraFee) {
        this.extraFee = extraFee;
    }

    public boolean isRewardapplied() {
        return rewardapplied;
    }

    public void setRewardapplied(boolean rewardapplied) {
        this.rewardapplied = rewardapplied;
    }

    public Visitor(String name, String password, String type, int ID) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.ID = ID;
        this.balance = 3000;
    }
    public void reserveSlot(Slots s){
        this.freehourscounter++;
        V_list_of_slots.add(s);
        s.setReserved(true);
        this.setHours(this.getHours() + 1);
        System.out.println(this.getHours());
        if(this.getFreehours() == 0)
        {
            this.setBalance(this.getBalance() - (s.getFees() + this.getExtraFee()));
            this.setExtraFee(0);
            s.setFees(100);
        }
        else
        {
            this.setFreehours(this.getFreehours()-1);
            s.setFees(0);
        }
        if(this.getType().equals("FORMAL") | this.getType().equals("GENERAL") && this.freehourscounter == 6)
        {
            this.setFreehours(getFreehours()+1);
            this.freehourscounter = 0;
        }
        else if (this.getType().equals("INSTRUCTOR") && this.freehourscounter == 12)
        {
            this.setFreehours(getFreehours()+1);
            this.freehourscounter = 0;
        }
    }
    public void cancel_reservation(Slots s){
        V_list_of_slots.remove(s);
        s.setReserved(false);
        this.setBalance(this.getBalance()+s.getFees());
        this.setExtraFee(this.getExtraFee()+20);
        this.setHours(this.getHours()-1);
        this.freehourscounter--;
    }
    public void updateReservation(Slots old_slot,Slots new_slot)
    {
        this.V_list_of_slots.remove(old_slot);
        this.V_list_of_slots.add(new_slot);
        old_slot.setReserved(false);
        new_slot.setReserved(true);
    }

    public int getBalance() {
        return balance;
    }

    public int getID() {
        return ID;
    }

    public int getHours() {
        return hours;
    }
    public int getFreehours()
    {
        return freehours;
    }
    public void setFreehours(int freehours)
    {
        this.freehours = freehours;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' + "ID=" + ID +
                '}';
    }


}
