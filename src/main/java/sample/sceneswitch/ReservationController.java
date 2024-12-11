package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    @FXML
    private ComboBox roomBox, time_slot_box;
    @FXML
    private Button confirm_button;

    Visitor visitor = LoginController.Currentvisitor;

    public void Generate_TimeSlots (ActionEvent e){
        String room  = (String) roomBox.getSelectionModel().getSelectedItem();
        ArrayList<String>slots = new ArrayList<>();
    for(Room r:DataHandling.getRooms()){
        if(r.getRoom_name().equals(room)){
            for(Slots s: r.List_of_Slots){
                slots.add(s.getDate() + "   " + s.getTimef()+ "   " + s.getTimet() );
            }
        }
    }
        time_slot_box.getItems().setAll(slots);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(visitor.getType().equals("GENERAL")){
            String[] RoomName = {"General Room 1","General Room 2"};
            roomBox.getItems().setAll(RoomName);

        }
        else if (visitor.getType().equals("INSTRUCTOR")) {

            String[] RoomName = {"Teaching Room 1", "Teaching Room 2","Teaching Room 3"};
            roomBox.getItems().setAll(RoomName);

        }
        else{
            String[] RoomName = {"Meeting Room 1","Meeting Room 2","Meeting Room 3"};
            roomBox.getItems().setAll(RoomName);

        }
    }
}
