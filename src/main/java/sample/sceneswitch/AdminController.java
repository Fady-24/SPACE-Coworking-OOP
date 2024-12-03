package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminController {

    public void log_out(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changescene("starting.fxml");
    }
}
