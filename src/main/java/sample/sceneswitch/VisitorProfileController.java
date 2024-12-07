package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisitorProfileController implements Initializable {

    @FXML
    Label welcome,visitortypelabel,visitortotalhourslabel,balance;
    @FXML
    Rectangle rect;
    @FXML
    ImageView reservations,icon;

    Visitor v = LoginController.Currentvisitor;

    public void user_logout(MouseEvent e) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changescene("starting.fxml");
    }
    public void hover_in(MouseEvent e){
        Animation.enlarge_image((Node)e.getSource());
    }
    public void hover_out(MouseEvent e){
        Animation.ensmall_image(((Node)e.getSource()));
    }
    public void button_transition(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(249, 178, 51));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(249, 178, 51), Color.rgb(56, 56, 56));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visitortypelabel.setText(v.getType());
        if (v.getType().equals("INSTRUCTOR")){
            Animation.changeImage(icon,"Asset 58.png");

        }else if(v.getType().equals("FORMAL")){
            Animation.changeImage(icon,"Asset 61.png");
        }
        visitortotalhourslabel.setText(String.valueOf(v.getHours()));
        welcome.setText(v.getName().toUpperCase().split(" ")[0]);
        balance.setText(String.valueOf(v.getBalance())+" $");
        System.out.println(v.getType());
    }
}
