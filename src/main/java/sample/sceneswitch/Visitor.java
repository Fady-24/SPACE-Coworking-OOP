package sample.sceneswitch;

import java.io.*;
import java.util.ArrayList;

public class Visitor implements Serializable {
    private String name;
    private final int ID;
    private String password;
    private String type;
    private int hours;
    private int balance;
    private int extraFee;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getExtraFee() {
        return extraFee;
    }

    public void setExtraFee(int extraFee) {
        this.extraFee = extraFee;
    }


//    private int reserved_slot;
    public ArrayList<Slots> V_list_of_slots=new ArrayList<>();

    public Visitor(String name, String password, String type,int ID) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.ID = ID;
        this.balance = 1000;
    }
    public void reserveSlot(Slots s){
        V_list_of_slots.add(s);
        s.setReserved(true);
        this.setBalance(this.getBalance()-(s.getFees()+this.getExtraFee()));
        this.setExtraFee(0);

    }
    public void cancel_reservation(Slots s){
        V_list_of_slots.remove(s);
        s.setReserved(false);
        this.setBalance(this.getBalance()+s.getFees());
        this.setExtraFee(this.getExtraFee()+20);
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

    public void updateReservation(Slots old_slot,Slots new_slot)
    {
        this.V_list_of_slots.remove(old_slot);
        this.V_list_of_slots.add(new_slot);
        old_slot.setReserved(false);
        new_slot.setReserved(true);
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
