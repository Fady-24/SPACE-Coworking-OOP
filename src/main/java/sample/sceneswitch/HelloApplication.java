package sample.sceneswitch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
      //  Image icon = new Image(new FileInputStream(new File("C:\\Users\\fa\\IdeaProjects\\sceneswitch\\src\\main\\resources\\sample\\sceneswitch\\TEST-03.png")));
        //primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root2);
        String CSS = Objects.requireNonNull(this.getClass().getResource("application.css")).toExternalForm();
        scene.getStylesheets().add(CSS);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public void changescene (String fxml) throws IOException {
         Parent changeRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml))) ;
         stg.getScene().setRoot(changeRoot);
         stg.show();

    }
}
