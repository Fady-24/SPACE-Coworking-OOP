package sample.sceneswitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SlotSceneController implements Initializable {

    @FXML
    private ComboBox roomchoice,SlotTimeFrom,roomchoice1,Slot_picker;
    @FXML
    private TextField SlotTimeto;
    @FXML
    private DatePicker SlotDate,SlotDate1;
    @FXML
    private Rectangle rect,rect2;
    @FXML
    private ImageView create_button,delete_button,home;
    @FXML
    private AnchorPane Anchor_Del,Anchor_Add;
    boolean selected;
    @FXML
    private Label red_label,green_label;

    ArrayList<Room> rooms = DataHandling.getRooms();

    public void button_transition(MouseEvent e) {
        Animation.colorfillin((javafx.scene.shape.Shape) e.getSource(), javafx.scene.paint.Color.rgb(56, 56, 56), javafx.scene.paint.Color.rgb(51, 51, 255));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((javafx.scene.shape.Shape) e.getSource(), javafx.scene.paint.Color.rgb(51, 51, 255), Color.rgb(56, 56, 56));
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
    public void test(ActionEvent e){
        LocalTime oldtime = LocalTime.parse((String)SlotTimeFrom.getSelectionModel().getSelectedItem());
        LocalTime newtime = oldtime.plusHours(1);
        SlotTimeto.setText(newtime.format(DateTimeFormatter.ofPattern("HH:mm")));
    }
    public void switch_to_del(MouseEvent mouseEvent){
        Animation.fade_in(Anchor_Del);
        Animation.fade_out(Anchor_Add);
        Anchor_Add.setMouseTransparent(true);
        Anchor_Del.setMouseTransparent(false);
        red_label.setText(" ");
        green_label.setText(" ");
    }
    public void switch_to_Add(MouseEvent mouseEvent){
        Animation.fade_in(Anchor_Add);
        Animation.fade_out(Anchor_Del);
        red_label.setText(" ");
        green_label.setText(" ");
        Anchor_Del.setMouseTransparent(true);
        Anchor_Add.setMouseTransparent(false);
    }

    public void switch_back(MouseEvent mouseEvent){
        Animation.fade_transition((Node) mouseEvent.getSource(),"admin.fxml");
    }

    public void create_slot(MouseEvent e){


        LocalDate date = SlotDate.getValue();
        if (roomchoice.getSelectionModel().getSelectedItem() == null || SlotDate.getValue() == null || SlotTimeFrom.getValue()==null){
            red_label.setText("Make Sure That All Fields have entries");
            green_label.setText(" ");
        }else if (SlotDate.getValue().isBefore(LocalDate.now())) {
            green_label.setText(" ");
            red_label.setText("Make Sure That the Date is entered correctly");
        }else if(slot_already_exists((String) roomchoice.getSelectionModel().getSelectedItem(),SlotDate.getValue(),LocalTime.parse((String)SlotTimeFrom.getValue())))
        {
            red_label.setText("Slot Already Exists");
            green_label.setText(" ");
        }
        else {
            String rname = (String) roomchoice.getSelectionModel().getSelectedItem();
            LocalTime timef = LocalTime.parse((String)SlotTimeFrom.getSelectionModel().getSelectedItem());
            LocalTime timet =  LocalTime.parse(SlotTimeto.getText());
            for (Room room : rooms) {
                if (room.getRoom_name().equals(rname)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Create Slot");
                    alert.setHeaderText("Slot creation confirmation");
                    alert.setContentText("Are you sure you want to create a new slot");
                    if(alert.showAndWait().get() == ButtonType.OK)
                    {
                        Slots s = new Slots(date, timef, timet, 100,room.getRoom_name());
                        room.New_Slot(s);
                        rooms.set(room.getRoom_Id() - 1, room);
                        DataHandling.setRooms(rooms);
                        System.out.println("slot added");
                        System.out.println(room.toString());
                        red_label.setText(" ");
                        green_label.setText("Slot Added");
                        break;
                    }
                }
            }
        }
    }
    public void check_slots(MouseEvent e){



        ArrayList<String> list = new ArrayList<>();
        String rname = (String) roomchoice1.getSelectionModel().getSelectedItem();
        LocalDate date = SlotDate1.getValue();
        for (Room room : rooms){
            if (room.getRoom_name().equals(rname)){
                for (Slots slot : room.List_of_Slots){
                    if(slot.getDate().equals(date)){
                        list.add(slot.preview());
                    }
                }
            }
        }
        // Still needs improvement
        Slot_picker.getItems().clear();
        Slot_picker.setVisibleRowCount(list.size());
        Slot_picker.getItems().addAll(list);
    }

    public void selected(ActionEvent e){
        selected=true;
        System.out.println(Slot_picker.getItems());

    }

    public void delete_slot(MouseEvent e){ ////////////// WE ARE STILL HERE
        if (!selected ||roomchoice1.getSelectionModel().getSelectedItem() == null || SlotDate1.getValue() == null|| Slot_picker.getValue() == null){
            red_label.setText("Make Sure That All Fields have entries");
        }else if (SlotDate1.getValue().isBefore(LocalDate.now())){
            red_label.setText("Make Sure That the Date is entered correctly");
        }else{
            String timefrom = (String) Slot_picker.getSelectionModel().getSelectedItem();
            timefrom=timefrom.split(" ")[0];
            LocalTime timef = LocalTime.parse(timefrom);
            for (Room room : rooms) {
                if (room.getRoom_name().equals(roomchoice1.getSelectionModel().getSelectedItem())) {
                    room.List_of_Slots.removeIf(slot ->
                            slot.getDate().equals(SlotDate1.getValue()) && slot.getTimef().equals(timef)
                    );
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Delete Slot");
                    alert.setHeaderText("Confirm slot deletion");
                    alert.setContentText("Are you sure you want to delete the slot?");
                        red_label.setText("Slot Removed");
                        System.out.println("slot removed");
                        System.out.println(room.toString());
                        selected = false; //???
                        break;
                    }
                }
            }

        }




    public boolean slot_already_exists (String rname , LocalDate date, LocalTime timef){
        for (Room room : rooms){
            if (room.getRoom_name().equals(rname)){
                for (Slots slot : room.List_of_Slots){
                    if(slot.getDate().equals(date)&&slot.getTimef().equals(timef)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            // STILL NEEDS TONs OF Adjustments
            Anchor_Del.setMouseTransparent(true);
            String roomnames[]={"General Room 1", "General Room 2","Meeting Room 1","Meeting Room 2","Meeting Room 3","Teaching Room 1","Teaching Room 2","Teaching Room 3"};
            SlotTimeFrom.getItems().addAll(generateTimeSlots(9,20));

            roomchoice.getItems().addAll(roomnames);
            roomchoice1.getItems().addAll(roomnames);
    }
}
