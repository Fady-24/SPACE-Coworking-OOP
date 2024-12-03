package sample.sceneswitch;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField textfield01;
    private Button button01;
    @FXML
    private Label mylabel;

    @FXML
    private PasswordField passfield;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button submitbutton;
    //public static Visitor CurrentVisitor;

    int age;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public static Visitor Currentvisitor;
    public void switch_to_register (ActionEvent e) throws IOException {
        Animation.fade_transition(anchor,"register.fxml");

    }
    public void button_transition(MouseEvent e) {
        Animation.enlarge((Node) e.getSource());
    }
    public void button_transition2(MouseEvent e) {
        Animation.en_small((Node) e.getSource());
    }
    public void submit(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        File f = new File("visitor.dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        ArrayList<Visitor> visitors = (ArrayList<Visitor>) ois.readObject();


       for(Visitor visitor : visitors) {
           System.out.println(visitor.toString());
           String name = textfield01.getText();
           String pass = passfield.getText();
           HelloApplication h = new HelloApplication();
           if(name.equals("admin") && pass.equals("admin")) {
               h.changescene("admin.fxml");
           }else if(visitor.getName().equals(name) && visitor.getPassword().equals(pass)) {
               Currentvisitor = visitor;
               System.out.println("current visitor " + Currentvisitor.getName());
               System.out.println("current type " + Currentvisitor.getType());


               h.changescene("scene1.fxml");
               mylabel.setText("Successfully logged in");
               break;
           }else{
               mylabel.setText("Incorrect username or password");
           }

       }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animation.fade_in(anchor);
    }
}
