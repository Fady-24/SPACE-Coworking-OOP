package sample.sceneswitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SlotSceneController implements Initializable {

    @FXML
    private ComboBox roomchoice,SlotTimeFrom;
    @FXML
    private TextField SlotTimeto;
    @FXML
    private DatePicker SlotDate;
    @FXML
    private Rectangle rect;
    @FXML
    private ImageView create_button,delete_button;

    public void button_transition(MouseEvent e) {
        Animation.colorfillin((javafx.scene.shape.Shape) e.getSource(), javafx.scene.paint.Color.rgb(56, 56, 56), javafx.scene.paint.Color.rgb(208, 100, 157));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((javafx.scene.shape.Shape) e.getSource(), javafx.scene.paint.Color.rgb(208, 100, 157), Color.rgb(56, 56, 56));
    }

    public void on_hover_plus(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 55.png" );
    }
    public void on_hover_away_plus(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 53.png" );
    }
    public void on_hover_trash(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 54.png" );
    }
    public void on_hover_away_trash(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 52.png" );
    }
    private List<String> generateTimeSlots(int start_hour,int end_hour) {
        List<String> timeSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(start_hour, 0); // 09:00
        LocalTime endTime = LocalTime.of(end_hour, 0); // 20:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        while (!startTime.isAfter(endTime)) {
            timeSlots.add(startTime.format(formatter));
            startTime = startTime.plusHours(1); // Increment by 1 hour
        }
        return timeSlots;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            // STILL NEEDS TONs OF Adjustments
            String roomnames[]={"general room 1", "general room 2"};
            SlotTimeFrom.getItems().addAll(generateTimeSlots(9,20));
            roomchoice.getItems().addAll(roomnames);
    }
}
