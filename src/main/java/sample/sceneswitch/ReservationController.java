package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    @FXML
    private ComboBox roomBox, time_slot_box,Reservation_choice;
    @FXML
    private Button confirm_button;
    @FXML
     private Label res_d;

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
    public void displayReservation(ActionEvent e){
        String selection = (String)Reservation_choice.getSelectionModel().getSelectedItem();
        int slot_Id= Integer.parseInt(selection.split(" ")[1]);
        Slots targetSlot = visitor.V_list_of_slots.get(slot_Id-1);
        res_d.setText(targetSlot.getRoomName()+"          "+targetSlot.getDate()+"          "+targetSlot.preview()+"          "+targetSlot.getFees());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String>slotsList = new ArrayList<>();
        for(int i=0;i< visitor.V_list_of_slots.size();i++)
        {
            String x ="Reservation "+(i+1);
            slotsList.add(x);
        }
        Reservation_choice.getItems().setAll(slotsList);

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
