package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisitorProfileController implements Initializable {
    @FXML
    Button profileButton, ReservationButton, logout;
    @FXML
    Label type22,welcome,usernamelabel,passwordlabel,visitortypelabel,visitoridlabel,visitortotalhourslabel;
    @FXML
    RadioButton passtoggle;

    Visitor v = LoginController.Currentvisitor;

    public void user_logout(ActionEvent e) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changescene("starting.fxml");
    }
    public void prof_hover(MouseEvent e) throws IOException {

        Animation.fade_transition((Button) e.getSource() );

    }
    public void show_pass(ActionEvent e) {
        if (passtoggle.isSelected()) {
            passwordlabel.setText("PASSWORD: " + v.getPassword());
        }
        else {
            passwordlabel.setText("PASSWORD: " + v.getPassword().replaceAll(".","●"));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernamelabel.setText("USERNAME: "+v.getName());
        passwordlabel.setText("PASSWORD: " + v.getPassword().replaceAll(".","●"));
        visitortypelabel.setText("VISITOR TYPE: "+v.getType());
        visitoridlabel.setText("VISITOR ID: "+v.getID());
        visitortotalhourslabel.setText("TOTAL HOURS: "+v.getHours());
        System.out.println(v.getType());
        type22.setText(v.getType());
        welcome.setText("WELCOME, " + v.getName().toUpperCase().split(" ")[0]);

    }
}
