package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    @FXML
    private ComboBox<String> roomBox, time_slot_box,Reservation_choice;
    @FXML
     private Label res_d;
    @FXML
    private Rectangle cancel_res,confirm_res,confirm_button;
    @FXML
    private ImageView add_icon,del_icon,edit_icon,visitorhome;
    @FXML
    private AnchorPane Anchor_can,Anchor_add,Anchor_update;
    private boolean in_can,in_add,in_edit;

    Visitor visitor = LoginController.Currentvisitor;

    ///////////////////////////////////////////// Scene Handling ////////////////////////////////////////////////////////////
    public void hover_in (MouseEvent e)
    {
        if (e.getSource() == del_icon){
            Animation.changeImageSmoothly(del_icon,"Asset 54.png");
        }else if (e.getSource() == add_icon){
            Animation.changeImageSmoothly(add_icon,"Asset 55.png");
        }else if(e.getSource()==edit_icon){
            Animation.changeImageSmoothly(edit_icon,"Asset 67.png");
        }
    }

    public void hover_away (MouseEvent e)
    {
        if (e.getSource() == del_icon){
            Animation.changeImageSmoothly(del_icon,"Asset 52.png");
        }else if (e.getSource() == add_icon){
            Animation.changeImageSmoothly(add_icon,"Asset 53.png");
        }else if(e.getSource()==edit_icon){
            Animation.changeImageSmoothly(edit_icon,"Asset 66.png");
        }
    }


    public void on_click (MouseEvent e)
    {
        if (e.getSource() == del_icon && in_add){
            Animation.fade_out(Anchor_add);
            Animation.fade_in(Anchor_can);
            in_can = true;
            in_add = false;
            in_edit = false;
        }else if (e.getSource() == del_icon && in_edit){
            Animation.fade_out(Anchor_update);
            Animation.fade_in(Anchor_can);
            in_can = true;
            in_add = false;
            in_edit = false;
        }else if(e.getSource()==edit_icon && in_can){
            Animation.fade_out(Anchor_can);
            Animation.fade_in(Anchor_update);
            in_can = false;
            in_add = false;
            in_edit = true;
        }else if (e.getSource()==edit_icon && in_add){
            Animation.fade_out(Anchor_add);
            Animation.fade_in(Anchor_update);
            in_can = false;
            in_add = false;
            in_edit = true;
        }else if (e.getSource()==add_icon && in_edit){
            Animation.fade_out(Anchor_update);
            Animation.fade_in(Anchor_add);
            in_can = false;
            in_add = true;
            in_edit = false;
        }else if (e.getSource()==add_icon && in_can){
            Animation.fade_out(Anchor_can);
            Animation.fade_in(Anchor_add);
            in_can = false;
            in_add = true;
            in_edit = false;
        }

        if(in_can){
            Anchor_can.setMouseTransparent(false);
            Anchor_add.setMouseTransparent(true);
            Anchor_update.setMouseTransparent(true);
        }else if(in_add){
            Anchor_can.setMouseTransparent(true);
            Anchor_add.setMouseTransparent(false);
            Anchor_update.setMouseTransparent(true);
        }else if (in_edit){
            Anchor_can.setMouseTransparent(true);
            Anchor_add.setMouseTransparent(true);
            Anchor_update.setMouseTransparent(false);
        }
    }

    public void return_home(MouseEvent e){
        Animation.fade_transition((Node)e.getSource() ,"scene1.fxml");
    }

    public void button_transition(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(180, 63, 63));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(180, 63, 63), Color.rgb(56, 56, 56));
    }

    public void button_transition3(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(249, 178, 51));
    }
    public void button_transition4(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(249, 178, 51), Color.rgb(56, 56, 56));
    }




    ///////////////////////////////////////////// Adding a Reservation ////////////////////////////////////////////////////////////
    public void Generate_TimeSlots (ActionEvent e){
        String room  = (String) roomBox.getSelectionModel().getSelectedItem();
        ArrayList<String>slots = new ArrayList<>();
    for(Room r:DataHandling.getRooms()){
        if(r.getRoom_name().equals(room)){
            for(Slots s: r.List_of_Slots){
                slots.add(s.getDate() + "   " + s.preview());
            }
        }
    }
        time_slot_box.getItems().setAll(slots);
    }





    ///////////////////////////////////////////// Canceling a Reservation ////////////////////////////////////////////////////////////
    public void displayReservation(ActionEvent e){
        String selection = (String)Reservation_choice.getSelectionModel().getSelectedItem();
        int slot_Id= Integer.parseInt(selection.split(" ")[1]);
        Slots targetSlot = visitor.V_list_of_slots.get(slot_Id-1);
        res_d.setText(targetSlot.getRoomName()+"          "+targetSlot.getDate()+"          "+targetSlot.preview()+"          "+targetSlot.getFees()+"$");
        Animation.fade_in(res_d);

    }

    public void cancel_res(MouseEvent e){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        in_add = true;
        in_edit = false;
        in_can = false;
        Anchor_add.setMouseTransparent(false);
        Anchor_update.setMouseTransparent(true);
        Anchor_can.setMouseTransparent(true);
        res_d.setOpacity(0.0);
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
