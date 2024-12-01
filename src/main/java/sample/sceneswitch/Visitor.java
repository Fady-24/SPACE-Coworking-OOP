package sample.sceneswitch;

import java.io.*;
import java.util.ArrayList;

public class Visitor implements Serializable {
    private String name;
    private final int ID;
    private String password;
    private String type;
    private int hours;

    public Visitor(String name, String password, String type,int ID) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.ID = ID;
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
