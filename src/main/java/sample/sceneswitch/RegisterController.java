package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Button signbutton, login_switch;
    private String[] acc_types = {"GENERAL","INSTRUCTOR","FORMAL"};
    @FXML
    private ComboBox<String> choicebox01;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField namefield;
    @FXML
    private PasswordField passfield;
    @FXML
    private PasswordField confirmpassfield;
    @FXML
    private Label reactivelabel;
    @FXML
    private AnchorPane anchor;



    public void button_transition(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(255, 128, 78));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(255, 128, 78), Color.rgb(56, 56, 56));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox01.getItems().addAll(acc_types);
        Animation.fade_in(anchor);


    }
//    public void button_transition(MouseEvent e) {
//        Animation.enlarge((Node) e.getSource());
//    }
//    public void button_transition2(MouseEvent e) {
//        Animation.en_small((Node) e.getSource());
//    }
//
   public void switch_to_main (ActionEvent e) throws IOException {
        Animation.fade_transition(anchor,"starting.fxml");
    }

    public void create_new() throws IOException, ClassNotFoundException {
        String type = choicebox01.getSelectionModel().getSelectedItem();
        String name = namefield.getText();
        String pass = passfield.getText();
        String conf = confirmpassfield.getText();

        if (name.isEmpty()|| pass.isEmpty() || conf.isEmpty()) {
            reactivelabel.setText("PLEASE MAKE SURE TO FILL ALL FIELDS!!!");
        }
        else if (pass.length()<9 || ! number_check(pass)) {
            reactivelabel.setText("PLEASE MAKE SURE TO FOLLOW PASSWORD INSTRUCTIONS");
        }
        else if (!pass.equals(conf)) {
            reactivelabel.setText("PASSWORD CONFIRMATION DOESN'T MATCH");

        }
        else if (type == null){
            reactivelabel.setText("PLEASE CHOOSE A VISITOR TYPE");
        }
        else {
            File f = new File("visitor.dat");
            ArrayList<Visitor> visitors;

            if (f.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                    visitors = (ArrayList<Visitor>) ois.readObject();
                }
            } else {
                visitors = new ArrayList<>();
            }

            Visitor v = new Visitor(name, pass, type, visitors.size()+1); // will cause a logical error in the future
            visitors.add(v);
            System.out.println("done");


            // Write
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(visitors);
            oos.close();
        }
    }
    public boolean number_check(String pass) {
        for (char c : pass.toCharArray()) {
            if (Character.isDigit(c)) {
                return true; // Return true immediately if a digit is found
            }
        }
        return false; // Return false if no digit is found
    }

}
