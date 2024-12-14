package sample.sceneswitch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private Label roomname,roomid,counter_label,lb_1;
    @FXML
    private ImageView money_calc,right_arrow,left_arrow;
    @FXML
    private Rectangle rect;
    @FXML
    private Label total_fees;

    int counter;
    Room room = roomhomepagecontroller.current_room;

    public void right_pressed(MouseEvent event) {
        left_arrow.setOpacity(1);
        left_arrow.setMouseTransparent(false);
        if (room.List_of_Visitors.size()>(counter+1)*7){
            counter=counter+1;
            counter_label.setText(String.valueOf(counter+1));
            page_check(counter);
        }
         if ((counter+1)*7 > room.List_of_Visitors.size())
        {
            right_arrow.setMouseTransparent(true);
            right_arrow.setOpacity(0.5);
        }
    }
    public void left_pressed(MouseEvent event) {
        right_arrow.setMouseTransparent(false);
        right_arrow.setOpacity(1);
        if (counter-1 == 0){
            counter--;
            counter_label.setText(String.valueOf(counter+1));
            left_arrow.setMouseTransparent(true);
            left_arrow.setOpacity(0.5);
            page_check(counter);

        }
        else if (counter == 0)
        {
            counter_label.setText(String.valueOf(counter+1));
            left_arrow.setMouseTransparent(true);
            left_arrow.setOpacity(0.5);
        }
        else
        {
           counter--;
           page_check(counter);
            counter_label.setText(String.valueOf(counter+1));
            left_arrow.setMouseTransparent(false);
            left_arrow.setOpacity(1);
        }


    }
    public void page_check(int counter){

       String text="";
        System.out.println(counter);
        for (int i = counter*7; i < ((counter*7)+7)&& i < room.List_of_Visitors.size(); i++) {
            for (int j = counter*7; j < ((counter*7)+7)&& j < room.List_of_Visitors.get(i).V_list_of_slots.size(); j++) {

                if((room.List_of_Visitors.get(i).V_list_of_slots.get(j).getRoomName().equals(room.getRoom_name()))){
                    System.out.println("loop");
                text = text + room.List_of_Visitors.get(i).getName() + "                                           " +room.List_of_Visitors.get(i).V_list_of_slots.get(j).getDate()+"                   "+room.List_of_Visitors.get(i).V_list_of_slots.get(j).getTimef()+" to "+ room.List_of_Visitors.get(i).V_list_of_slots.get(j).getTimet()+"\n";
            }}
            lb_1.setText(text);

       }
        System.out.println(room.List_of_Visitors);

    }


    public void on_hover_money(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 50.png" );
    }
    public void on_hover_away_money(MouseEvent mouseEvent) {
        Animation.changeImageSmoothly((ImageView)mouseEvent.getSource(),"Asset 48.png" );
    }

    public void button_transition(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(51, 51, 255));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(51, 51, 255), Color.rgb(56, 56, 56));
    }

    public void switch_to_rooms (MouseEvent mouseEvent) throws IOException {
        HelloApplication helloApp = new HelloApplication();
        helloApp.changescene("roomhomepage.fxml");
    }

    public void calculate_total_fees (MouseEvent mouseEvent){
        int fee=0;
        for (Slots slots : room.List_of_Slots) {
            if(slots.getReserved()){
                fee+=slots.getFees();
            }
        }
        total_fees.setText(String.valueOf(fee)+" $");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        total_fees.setText("");
        counter=0;
        left_arrow.setOpacity(0.5);
        left_arrow.setMouseTransparent(true);
        right_arrow.setOpacity(1);
        right_arrow.setMouseTransparent(false);
        if (room.List_of_Visitors.size()<=7){
            right_arrow.setOpacity(0.5);
            right_arrow.setMouseTransparent(true);
        }
        roomname.setText(roomhomepagecontroller.current_room.getRoom_name());
        roomid.setText("ROOM ID : #"+String.valueOf(roomhomepagecontroller.current_room.getRoom_Id()));
        page_check(counter);
    }
}
