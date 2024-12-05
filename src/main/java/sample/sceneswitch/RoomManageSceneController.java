package sample.sceneswitch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.sceneswitch.roomhomepagecontroller.*;

public class RoomManageSceneController implements Initializable {

    @FXML
    private Label roomname,roomid;
    @FXML
    private ImageView money_calc;
    @FXML
    private Rectangle rect;


    public void on_hover_money(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 50.png" );
    }
    public void on_hover_away_money(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 48.png" );
    }

    public void button_transition(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(226, 167, 35));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(226, 167, 35), Color.rgb(56, 56, 56));
    }

    public void switch_to_rooms (MouseEvent mouseEvent) throws IOException {
        HelloApplication helloApp = new HelloApplication();
        helloApp.changescene("roomhomepage.fxml");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomname.setText(roomhomepagecontroller.current_room.getRoom_name());
        roomid.setText("ROOM ID : #"+String.valueOf(roomhomepagecontroller.current_room.getRoom_Id()));
    }
}
