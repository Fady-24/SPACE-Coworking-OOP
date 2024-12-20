package sample.sceneswitch;

import java.sql.Date;
import java.sql.Time;

public class Admin
{
    private static String name = "admin";
    private static String password = "admin";
    public static String getPassword()
    {
        return password;
    }
    public static void setPassword(String password)
    {
        Admin.password = password;
    }
    public static String getName()
    {
        return name;
    }
    public static void setName(String name)
    {
        Admin.name = name;
    }
}
