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

public class ReservationController implements Initializable
{
    @FXML
    private ComboBox<String> roomBox, time_slot_box, Reservation_choice, reservations_combobox, room_combobox, timeslot_combobox;
    @FXML
    private Label time, date, name, fees,reservationdetail_label, newreservationdetail_label,update_res_success, addingreservationlabel;
    @FXML
    private ImageView add_icon, del_icon, edit_icon, home;
    @FXML
    private AnchorPane Anchor_can, Anchor_add, Anchor_update, res_d, add_1det, add_newdetail;
    private boolean in_can, in_add, in_edit;
    private Slots targetSlot, updatedslot, deltargetslot;
    private static String selectedroom, beforeupdateroom;


    Visitor visitor = LoginController.Currentvisitor;
    ArrayList<Room> rooms = DataHandling.getRooms();
    ///////////////////////////////////////////// Scene Handling ////////////////////////////////////////////////////////////
    public void hover_in(MouseEvent e)
    {
        if (e.getSource() == del_icon)
        {
            Animation.changeImageSmoothly(del_icon, "Asset 54.png");
        }
        else if (e.getSource() == add_icon)
        {
            Animation.changeImageSmoothly(add_icon, "Asset 55.png");
        }
        else if (e.getSource() == edit_icon)
        {
            Animation.changeImageSmoothly(edit_icon, "Asset 67.png");
        }
    }

    public void hover_away(MouseEvent e)
    {
        if (e.getSource() == del_icon)
        {
            Animation.changeImageSmoothly(del_icon, "Asset 52.png");
        }
        else if (e.getSource() == add_icon)
        {
            Animation.changeImageSmoothly(add_icon, "Asset 53.png");
        }
        else if (e.getSource() == edit_icon)
        {
            Animation.changeImageSmoothly(edit_icon, "Asset 66.png");
        }
    }


    public void on_click(MouseEvent e)
    {
        if (e.getSource() == del_icon && in_add)
        {
            Animation.fade_out(Anchor_add);
            Animation.fade_in(Anchor_can);
            in_can = true;
            in_add = false;
            in_edit = false;
        }
        else if (e.getSource() == del_icon && in_edit)
        {
            Animation.fade_out(Anchor_update);
            Animation.fade_in(Anchor_can);
            in_can = true;
            in_add = false;
            in_edit = false;
        }
        else if (e.getSource() == edit_icon && in_can)
        {
            Animation.fade_out(Anchor_can);
            Animation.fade_in(Anchor_update);
            in_can = false;
            in_add = false;
            in_edit = true;
        }
        else if (e.getSource() == edit_icon && in_add)
        {
            Animation.fade_out(Anchor_add);
            Animation.fade_in(Anchor_update);
            in_can = false;
            in_add = false;
            in_edit = true;
        }
        else if (e.getSource() == add_icon && in_edit)
        {
            Animation.fade_out(Anchor_update);
            Animation.fade_in(Anchor_add);
            in_can = false;
            in_add = true;
            in_edit = false;
        }
        else if (e.getSource() == add_icon && in_can)
        {
            Animation.fade_out(Anchor_can);
            Animation.fade_in(Anchor_add);
            in_can = false;
            in_add = true;
            in_edit = false;
        }

        if (in_can)
        {
            Anchor_can.setMouseTransparent(false);
            Anchor_add.setMouseTransparent(true);
            Anchor_update.setMouseTransparent(true);
        }
        else if (in_add)
        {
            Anchor_can.setMouseTransparent(true);
            Anchor_add.setMouseTransparent(false);
            Anchor_update.setMouseTransparent(true);
        }
        else if (in_edit)
        {
            Anchor_can.setMouseTransparent(true);
            Anchor_add.setMouseTransparent(true);
            Anchor_update.setMouseTransparent(false);
        }
        combo_reset();
    }

    public void return_home(MouseEvent e) {
        Animation.fade_transition((Node) e.getSource(), "scene1.fxml");
    }
    public void homelogo1(MouseEvent e)
    {
        Animation.changeImageSmoothly(home, "Asset_262.png");
    }
    public void homelogo2(MouseEvent e)
    {
        Animation.changeImageSmoothly(home, "Asset_26_1.png");
    }
    public void delbutton_transition1(MouseEvent e)
    {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(180, 63, 63));
    }

    public void delbutton_transition2(MouseEvent e)
    {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(180, 63, 63), Color.rgb(56, 56, 56));
    }

    public void addbutton_transition1(MouseEvent e)
    {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(249, 178, 51));
    }

    public void addbutton_transition2(MouseEvent e)
    {
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

    public void combo_reset()
    {
        roomBox.getSelectionModel().clearSelection();
        time_slot_box.getSelectionModel().clearSelection();
        Reservation_choice.getSelectionModel().clearSelection();
        reservations_combobox.getSelectionModel().clearSelection();
        room_combobox.getSelectionModel().clearSelection();
        timeslot_combobox.getSelectionModel().clearSelection();
        Animation.fade_out(res_d);
        Animation.fade_out(add_1det);
        Animation.fade_out(update_res_success);
    }



    ///////////////////////////////////////////// Adding a Reservation ////////////////////////////////////////////////////////////
    public void Generate_TimeSlots(ActionEvent e)
    {
        selectedroom = roomBox.getSelectionModel().getSelectedItem();
        ArrayList<String> slots = new ArrayList<>();
        time_slot_box.getSelectionModel().clearSelection();
        for (Room room : rooms)
        {
            if (room.getRoom_name().equals(selectedroom))
            {
                for (Slots slot : room.List_of_Slots)
                {
                    System.out.println(slot.preview() + " "+slot.getReserved());
                    if (!slot.getReserved())
                    {
                        slots.add(slot.getDate() + "   " + slot.preview());
                    }
                }
            }
        }
        time_slot_box.getItems().setAll(slots);
    }


    public void confirm_reservation(MouseEvent e)
    {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setX(HelloApplication.stg.getX() + 200);
        alert.setY(HelloApplication.stg.getY() + 215);
        selectedroom = roomBox.getSelectionModel().getSelectedItem();
        String selec = time_slot_box.getSelectionModel().getSelectedItem();
        ArrayList<String> slots = new ArrayList<>();
        for (Room room : rooms)
        {
            if (room.getRoom_name().equals(selectedroom))
            {
                for (Slots slot : room.List_of_Slots)
                {
                    if (room.fully_booked() && !visitorinrow())
                    {
                        alert.setAlertType(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Can't reserve");
                        alert.setContentText("this room is fully_booked");
                        alert.show();
                    }
                    else if (selec.split(" ")[0].equals("" + slot.getDate()) && selec.split(" ")[3].equals("" + slot.getTimef()))
                    {
                        alert.setAlertType(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Reservation");
                        if (visitor.getFreehours() > 0)
                        {
                            alert.setContentText("Are you sure you want to reserve this slot? \n Since you have a free hours this slot will cost you nothing!!");
                        }
                        else
                        {
                            alert.setContentText("Are you sure you want to reserve this slot? \n This slot will cost you " + (slot.getFees() + visitor.getExtraFee()) + "$");
                        }
                        if (alert.showAndWait().get() == ButtonType.OK)
                        {
                            visitor.reserveSlot(slot);
                            System.out.println(visitor.getFreehours());
                            slot.setReserved(true);
                            check_and_replace();
                            System.out.println(room.List_of_Visitors);
                            room.setTotal_fees(room.getTotal_fees() + (slot.getFees() + visitor.getExtraFee()));

                        }
                    }
                    if (!slot.getReserved())
                    {
                        slots.add(slot.getDate() + "   " + slot.preview());
                    }
                }
            }
            time_slot_box.getItems().setAll(slots);
            combo_reinitialize();
        }
    }
    ///////////////////////////////////////////// Updating a Reservation ////////////////////////////////////////////////////////////
    public void displayReservation_forupdate(ActionEvent e)
    {
        String selection = reservations_combobox.getSelectionModel().getSelectedItem();
        int slot_Id;
        if (!reservations_combobox.getSelectionModel().isEmpty())
        {
            slot_Id = Integer.parseInt(selection.split(" ")[1]);
            targetSlot = visitor.V_list_of_slots.get(slot_Id-1);
        }
        reservationdetail_label.setText(targetSlot.toString());
        Animation.fade_in(add_1det);
    }
    public void displaynewdetails(ActionEvent e)
    {
        String selectedtime = timeslot_combobox.getSelectionModel().getSelectedItem();
        for (Room room : rooms)
        {
            if (room.getRoom_name().equals(selectedroom))
            {
                for (Slots slot : room.List_of_Slots)
                {
                    if (selectedtime.split(" ")[0].equals("" + slot.getDate()) && selectedtime.split(" ")[3].equals("" + slot.getTimef()))
                    {
                        updatedslot = slot;
                        newreservationdetail_label.setText(slot.toString());
                        Animation.fade_in(add_newdetail);
                    }
                }
            }
        }
    }
    public void time_slot_selection(ActionEvent e)
    {
        selectedroom =room_combobox.getSelectionModel().getSelectedItem();
        ArrayList<String> slots = new ArrayList<>();
        timeslot_combobox.getSelectionModel().clearSelection();
        for (Room room : rooms)
        {
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
        timeslot_combobox.getItems().setAll(slots);
    }
    public void updateReservation(MouseEvent e)
    {
        for (Room room : rooms)
        {
                for (Slots slot : room.List_of_Slots)
                {
                    if (targetSlot.toString().equals(slot.toString())&&!room.fully_booked())
                    {
                        beforeupdateroom = targetSlot.toString().split("   ")[0];
                        System.out.println(beforeupdateroom);
                        System.out.println("mahmoud");
                        visitor.updateReservation(targetSlot, updatedslot);
                        updatedslot.setReserved(true);
                        targetSlot.setReserved(false);
                        slot.setReserved(false);
                        break;
                    }
                }
            }
        check_and_replace2();
        room_combobox.getSelectionModel().clearSelection();
        timeslot_combobox.getSelectionModel().clearSelection();
        reservations_combobox.getSelectionModel().clearSelection();
        Animation.fade_out(add_1det);
        Animation.fade_out(add_newdetail);
    }
    public void update_res(MouseEvent e)
    {
        String selec=timeslot_combobox.getSelectionModel().getSelectedItem();
        if(selec != null)
        {
            update_res_success.setStyle("-fx-text-fill: green");
            update_res_success.setText("Slot updated successfully");
            Animation.fade_in(update_res_success);
        }
        else
        {
            update_res_success.setText("Please make sure to enter time");
            update_res_success.setStyle("-fx-text-fill: red");
            Animation.fade_in(update_res_success);
        }

    }

    ///////////////////////////////////////////// Canceling a Reservation ////////////////////////////////////////////////////////////
    public void displayReservation(ActionEvent e)
    {
        String selection = Reservation_choice.getSelectionModel().getSelectedItem();
        int slot_Id;
        if (!Reservation_choice.getSelectionModel().isEmpty()) {
            slot_Id = Integer.parseInt(selection.split(" ")[1]);
            deltargetslot = visitor.V_list_of_slots.get(slot_Id-1);
        }
        name.setText(deltargetslot.getRoomName());
        date.setText(""+deltargetslot.getDate());
        time.setText(deltargetslot.preview());
        fees.setText(""+(deltargetslot.getFees() + visitor.getExtraFee()));
        Animation.fade_in(res_d);
    }
    public void cancel_res(MouseEvent e)
    {
        for (Room room : rooms) {
            for (Slots slot : room.List_of_Slots) {
                if(deltargetslot.toString().equals(slot.toString()))
                {
                    slot.setReserved(false);
                    visitor.cancel_reservation(deltargetslot);
                    System.out.println(room.List_of_Visitors);
                    remove_visitor( check_and_cancel());;
                    System.out.println("after removal");
                    System.out.println(room.List_of_Visitors);
                    break;
                }
            }
        }
     System.out.println(deltargetslot.toString());
     Reservation_choice.getSelectionModel().clearSelection();
     Animation.fade_out(res_d);
     System.out.println(visitor.getExtraFee());
     System.out.println(visitor.V_list_of_slots);
     combo_reinitialize();
     combo_2_reinitialize();
    }
    public void combo_reinitialize(){
        ArrayList<String>slotsList = new ArrayList<>();
        for(int i=0;i< visitor.V_list_of_slots.size();i++)
        {
            String x ="Reservation "+(i+1);
            slotsList.add(x);
        }
        Reservation_choice.getItems().setAll(slotsList);
        reservations_combobox.getItems().setAll(slotsList);
    }
    public void combo_2_reinitialize(){
        ArrayList<String> slots = new ArrayList<>();
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

    public Visitor check_and_cancel(){
        boolean flag=false;
        for(Room room : rooms){
            if (room.getRoom_name().equals(deltargetslot.getRoomName())){
                for (Visitor v:room.List_of_Visitors){
                    if(v.getID() == visitor.getID()) { // have another slots in the same room
                        System.out.println("replaced");
                        room.List_of_Visitors.remove(v);
                        room.New_Visitor(visitor);
                        flag = true;
                        return v;
                    }
                }
            }
        }
        return null;
    }
    public void remove_visitor(Visitor v){
        int counter=0;
        for(Room room : rooms){
            if (room.getRoom_name().equals(deltargetslot.getRoomName())){
                        for(Slots s : visitor.V_list_of_slots){
                            if(s.getRoomName().equals(room.getRoom_name())) {
                                counter++;
                                break;
                            }
                        }
                    }
            if (counter==0)
            {
                room.Remove_Visitor(v);
            }
        }
    }



    public void check_and_replace()
    {
        boolean flag=false;
        for(Room room : rooms)
        {
            if (room.getRoom_name().equals(selectedroom))
            {
                if(room.List_of_Visitors.isEmpty())
                {
                    room.List_of_Visitors.add(visitor);
                }
                else
                {
                    for (Visitor v:room.List_of_Visitors)
                    {
                        if(v.getID() == visitor.getID())
                        {
                            System.out.println("replaced");
                            System.out.println(room.List_of_Visitors.size() + " size ");
                            room.List_of_Visitors.remove(v);
                            room.New_Visitor(visitor);
                            flag = true;
                            break;
                        }
                    }
                    if(!flag)
                    {
                        room.New_Visitor(visitor);
                        break;
                    }
                }
            }
        }
    }


    public void check_and_replace2()
    {
        for(Room room : rooms)
        {
            if (room.getRoom_name().equals(selectedroom))
            {
                if(room.List_of_Visitors.isEmpty())
                {
                    room.List_of_Visitors.add(visitor);
                }
                else
                {
                    for (Visitor v:room.List_of_Visitors)
                    {
                        if(v.getID() == visitor.getID())
                        {
                            System.out.println("replaced");
                            room.List_of_Visitors.remove(v);
                            room.List_of_Visitors.add(visitor);
                            break;
                        }
                    }
                }
            }
            if(room.getRoom_name().equals(beforeupdateroom))
            {
                for(Visitor v:room.List_of_Visitors)
                {
                    if(v.getID() == visitor.getID())
                    {
                        room.List_of_Visitors.remove(v);
                        room.List_of_Visitors.add(visitor);
                        break;
                    }
                }
            }
        }
    }
    public boolean visitorinrow()
    {
        for(Room room:rooms)
        {
            if(room.getRoom_name().equals(selectedroom))
            {
                for(Visitor v:room.List_of_Visitors)
                {
                    if(v.getID() == visitor.getID())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public int index_check(){
        int index=0;
        for (Room room : rooms){
            for (int i=0;i<room.List_of_Visitors.size();i++){
                if (deltargetslot.getRoomName().equals(room.getRoom_name())&&room.List_of_Visitors.get(i).getName().equals(visitor.getName())){
                    index = i;
                }
            }

        }
        return index;
    }
    public void visit_check(String room_name){
        for (Room room : rooms) {
            if (room.getRoom_name().equals(room_name)) {
                if(!room.List_of_Visitors.contains(visitor)){
                    room.List_of_Visitors.add(visitor);
                }else{
                    System.out.println("already in there");
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
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
        reservations_combobox.getItems().setAll(slotsList);

        if(visitor.getType().equals("GENERAL")){
            String[] RoomName = {"General Room 1","General Room 2"};
            roomBox.getItems().setAll(RoomName);
            room_combobox.getItems().setAll(RoomName);
        }
        else if (visitor.getType().equals("INSTRUCTOR")) {

            String[] RoomName = {"Teaching Room 1", "Teaching Room 2","Teaching Room 3"};
            roomBox.getItems().setAll(RoomName);
            room_combobox.getItems().setAll(RoomName);
        }
        else{
            String[] RoomName = {"Meeting Room 1","Meeting Room 2","Meeting Room 3"};
            roomBox.getItems().setAll(RoomName);
            room_combobox.getItems().setAll(RoomName);
        }
    }
}
