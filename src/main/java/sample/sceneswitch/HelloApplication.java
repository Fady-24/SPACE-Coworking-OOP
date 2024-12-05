package sample.sceneswitch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        System.setProperty("prism.lcdtext", "false");
        stg = primaryStage;
        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("starting.fxml")));
        primaryStage.setTitle("SPACE");
        Image icon = new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("TEST-03.png")));
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root2);
        String CSS = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(CSS);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

//        File f = new File("rooms.dat");
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
//        ArrayList<Room> rooms = (ArrayList<Room>) ois.readObject();
//        for (Room room : rooms) {
//            if (room.getRoom_Id()==1){
//                room.New_Visitor(new Visitor("casilias","testtest","General",99));
//                room.New_Visitor(new Visitor("vector valdes","testtest","General",99));
//                room.New_Visitor(new Visitor("ramos","testtest","General",99));
//                room.New_Visitor(new Visitor("puyol","testtest","General",99));
//                room.New_Visitor(new Visitor("pepe","testtest","General",99));
//                room.New_Visitor(new Visitor("pique","testtest","General",99));
//                room.New_Visitor(new Visitor("xabi alonso","testtest","General",99));
//                room.New_Visitor(new Visitor("xavi","testtest","General",99));
//                room.New_Visitor(new Visitor("higuain","testtest","General",99));
//                room.New_Visitor(new Visitor("david villa","testtest","General",99));
//                room.New_Visitor(new Visitor("cristiano","testtest","General",99));
//                room.New_Visitor(new Visitor("messi","testtest","General",99));
//                rooms.set(rooms.indexOf(room), room);
//                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
//                oos.writeObject(rooms);
//                System.out.println("here");
//                oos.close();
//            }
//        }

//        File f = new File("rooms.dat");
//        ArrayList<Room> rooms = new ArrayList<>();
//        Room room1 = new General_room("General Room 1",1);
//        Room room2 = new General_room("General Room 2",2);
//
//        rooms.add(room1);
//        rooms.add(room2);
//
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
//        oos.writeObject(rooms);
//        System.out.println("here");
//        oos.close();
//



        primaryStage.show();
    }
    public void changescene (String fxml) throws IOException {
         Parent changeRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml))) ;
         stg.getScene().setRoot(changeRoot);
         stg.show();
    }
}
