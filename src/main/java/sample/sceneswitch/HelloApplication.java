package sample.sceneswitch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static Stage stg;
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        System.setProperty("prism.lcdtext", "false");
        stg = primaryStage;
        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("starting.fxml")));
        primaryStage.setTitle("SPACE");
        Image icon = new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("TEST-03.png")));
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root2);
        DataHandling.read_visitors();
        DataHandling.read_rooms();
        String CSS = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(CSS);
        primaryStage.setWidth(800);
        primaryStage.setHeight(630); //return this to 600 if needed
        primaryStage.setOnCloseRequest(event -> {
            try {
                DataHandling.handleClose(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);


//        ArrayList<Room> rooms = DataHandling.getRooms();
//        ArrayList<Visitor>visitors = DataHandling.getVisitors();
//        for (Room room : rooms)
//        {
//            if (room.getRoom_Id()==1)
//            {
//                Visitor v1 = new Visitor("visitor1", "testtest", "GENERAL", 99);
//                visitors.add(v1);
//                room.New_Visitor(v1);
//                rooms.set(rooms.indexOf(room), room);
//            }
//        }


//
//        ArrayList<Room> rooms =DataHandling.getRooms();
//        Room room1 = new General_room("General Room 1",1);
//        Room room2 = new General_room("General Room 2",2);
//        Room room3 = new Meeting_Room("Meeting Room 1", 3);
//        Room room4 = new Meeting_Room("Meeting Room 2", 4);
//        Room room5 = new Meeting_Room("Meeting Room 3", 5);
//        Room room6 = new Teaching_Room("Teaching Room 1", 6,"board", "projector", "instructor");
//        Room room7 = new Teaching_Room("Teaching Room 2", 7,"board", "projector", "instructor");
//        Room room8 = new Teaching_Room("Teaching Room 3", 8,"board", "projector", "instructor");
//        rooms.add(room1);
//        rooms.add(room2);
//        rooms.add(room3);
//        rooms.add(room4);
//        rooms.add(room5);
//        rooms.add(room6);
//        rooms.add(room7);
//        rooms.add(room8);
//        DataHandling.setRooms(rooms);

        primaryStage.show();
    }



    public void changescene (String fxml) throws IOException {
         Parent changeRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml))) ;
         stg.getScene().setRoot(changeRoot);
         stg.show();
    }
}
