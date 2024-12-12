package sample.sceneswitch;

import java.io.*;
import java.util.ArrayList;

public class Visitor implements Serializable {
    private String name;
    private final int ID;
    private String password;
    private String type;
    private int hours;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private int balance;
    private int extraFee;

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


    @Override
    public String toString() {
        return "Visitor{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' + "ID=" + ID +
                '}';
    }
}
