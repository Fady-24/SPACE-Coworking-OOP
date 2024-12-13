package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
    private ComboBox<String> roomBox, time_slot_box, Reservation_choice, reservation_combobox, room_combobox, timeslot_combobox;
    @FXML
    private Label time, date, name, fees;
    @FXML
    private Rectangle cancel_res, updatereservation_button, confirm_button;
    @FXML
    private ImageView add_icon, del_icon, edit_icon, visitorhome;
    @FXML
    private AnchorPane Anchor_can, Anchor_add, Anchor_update, res_d, add_1det;
    private boolean in_can, in_add, in_edit;
    private Slots targetSlot;
    private String selectedroom, selectedslot;

    Visitor visitor = LoginController.Currentvisitor;

    ///////////////////////////////////////////// Scene Handling ////////////////////////////////////////////////////////////
    public void hover_in(MouseEvent e) {
        if (e.getSource() == del_icon) {
            Animation.changeImageSmoothly(del_icon, "Asset 54.png");
        } else if (e.getSource() == add_icon) {
            Animation.changeImageSmoothly(add_icon, "Asset 55.png");
        } else if (e.getSource() == edit_icon) {
            Animation.changeImageSmoothly(edit_icon, "Asset 67.png");
        }
    }

    public void hover_away(MouseEvent e) {
        if (e.getSource() == del_icon) {
            Animation.changeImageSmoothly(del_icon, "Asset 52.png");
        } else if (e.getSource() == add_icon) {
            Animation.changeImageSmoothly(add_icon, "Asset 53.png");
        } else if (e.getSource() == edit_icon) {
            Animation.changeImageSmoothly(edit_icon, "Asset 66.png");
        }
    }


    public void on_click(MouseEvent e) {
        if (e.getSource() == del_icon && in_add) {
            Animation.fade_out(Anchor_add);
            Animation.fade_in(Anchor_can);
            in_can = true;
            in_add = false;
            in_edit = false;
        } else if (e.getSource() == del_icon && in_edit) {
            Animation.fade_out(Anchor_update);
            Animation.fade_in(Anchor_can);
            in_can = true;
            in_add = false;
            in_edit = false;
        } else if (e.getSource() == edit_icon && in_can) {
            Animation.fade_out(Anchor_can);
            Animation.fade_in(Anchor_update);
            in_can = false;
            in_add = false;
            in_edit = true;
        } else if (e.getSource() == edit_icon && in_add) {
            Animation.fade_out(Anchor_add);
            Animation.fade_in(Anchor_update);
            in_can = false;
            in_add = false;
            in_edit = true;
        } else if (e.getSource() == add_icon && in_edit) {
            Animation.fade_out(Anchor_update);
            Animation.fade_in(Anchor_add);
            in_can = false;
            in_add = true;
            in_edit = false;
        } else if (e.getSource() == add_icon && in_can) {
            Animation.fade_out(Anchor_can);
            Animation.fade_in(Anchor_add);
            in_can = false;
            in_add = true;
            in_edit = false;
        }

        if (in_can) {
            Anchor_can.setMouseTransparent(false);
            Anchor_add.setMouseTransparent(true);
            Anchor_update.setMouseTransparent(true);
        } else if (in_add) {
            Anchor_can.setMouseTransparent(true);
            Anchor_add.setMouseTransparent(false);
            Anchor_update.setMouseTransparent(true);
        } else if (in_edit) {
            Anchor_can.setMouseTransparent(true);
            Anchor_add.setMouseTransparent(true);
            Anchor_update.setMouseTransparent(false);
        }
    }

    public void return_home(MouseEvent e) {
        Animation.fade_transition((Node) e.getSource(), "scene1.fxml");
    }

    public void delbutton_transition1(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(180, 63, 63));
    }

    public void delbutton_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(180, 63, 63), Color.rgb(56, 56, 56));
    }

    public void addbutton_transition1(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(249, 178, 51));
    }

    public void addbutton_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(249, 178, 51), Color.rgb(56, 56, 56));
    }
    public void editbutton_transition1(MouseEvent e)
    {
        Animation.colorfillin((Shape)e.getSource(), Color.rgb(56, 56, 56), Color.rgb(80, 220, 100));
    }
    public void editbutton_transition2(MouseEvent e)
    {
        Animation.colorfillin((Shape)e.getSource(), Color.rgb(80, 220, 100), Color.rgb(56, 56, 56));
    }



    ///////////////////////////////////////////// Adding a Reservation ////////////////////////////////////////////////////////////
    public void Generate_TimeSlots(ActionEvent e) {
        selectedroom = (String) roomBox.getSelectionModel().getSelectedItem();
        ArrayList<String> slots = new ArrayList<>();
        ArrayList<Room> rooms = DataHandling.getRooms();
        time_slot_box.getSelectionModel().clearSelection();
        for (Room room : rooms) {
            if (room.getRoom_name().equals(selectedroom))
            {
                for (Slots slot : room.List_of_Slots)
                {
                    if (!slot.getReserved()) {
                        slots.add(slot.getDate() + "   " + slot.preview());
                    }
                }
            }
        }
        time_slot_box.getItems().setAll(slots);
    }

    public void wantedslot(ActionEvent e)
    {
        selectedslot = (String)time_slot_box.getSelectionModel().getSelectedItem().split("    ")[0];
        System.out.println(selectedslot);
    }
    public void confirm_reservation(MouseEvent e)
    {
        System.out.println("click");
        selectedroom = (String) roomBox.getSelectionModel().getSelectedItem();
        ArrayList<String> slots = new ArrayList<>();
        ArrayList<Room> rooms = DataHandling.getRooms();
        for (Room room : rooms) {
            if (room.getRoom_name().equals(selectedroom))
            {
                for (Slots slot : room.List_of_Slots)
                {
                    if (selectedslot.equals(slot.getDate() + "   " + slot.preview()) && !time_slot_box.getSelectionModel().isEmpty())
                    {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Slot Reservations");
                        alert.setHeaderText("CONFIRMATION");
                        alert.setContentText("Are you sure you want to reserve this slot? \n This slot will cost you 100$ ");
                        alert.setX(HelloApplication.stg.getX() + 200);
                        alert.setY(HelloApplication.stg.getY() + 215);
                        if (alert.showAndWait().get() == ButtonType.OK)
                        {
                            visitor.reserveSlot(slot);
                            System.out.println("rizz");
                        }
                    }
                }
            }
        }
    }




    ///////////////////////////////////////////// Canceling a Reservation ////////////////////////////////////////////////////////////
    public void displayReservation(ActionEvent e){
        String selection = (String)Reservation_choice.getSelectionModel().getSelectedItem();
        int slot_Id= Integer.parseInt(selection.split(" ")[1]);
        targetSlot = visitor.V_list_of_slots.get(slot_Id-1);
        name.setText(targetSlot.getRoomName());
        date.setText(""+targetSlot.getDate());
        time.setText(""+targetSlot.preview());
        fees.setText(""+targetSlot.getFees());
        Animation.fade_in(res_d);

    }

    public void cancel_res(MouseEvent e)
    {
     visitor.V_list_of_slots.remove(targetSlot);
     targetSlot.setReserved(false);
     visitor.setBalance(visitor.getBalance()+targetSlot.getFees());
     visitor.setExtraFee(visitor.getExtraFee()+20);
     Reservation_choice.getSelectionModel().clearSelection();
     Animation.fade_out(res_d);
     System.out.println(visitor.getExtraFee());

         System.out.println(visitor.V_list_of_slots);
        ArrayList<String>slotsList = new ArrayList<>();
        for(int i=0;i< visitor.V_list_of_slots.size();i++)
        {
            String x ="Reservation "+(i+1);
            slotsList.add(x);
        }
        Reservation_choice.getItems().setAll(slotsList);
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
