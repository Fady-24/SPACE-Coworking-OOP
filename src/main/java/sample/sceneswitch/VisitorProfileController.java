package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisitorProfileController implements Initializable {
    Button profileButton, ReservationButton, logout;
    Label type,Welcome;

    public void user_logout(ActionEvent e) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changescene("starting.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
