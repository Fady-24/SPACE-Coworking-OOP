package sample.sceneswitch;

import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class AdminController {
    @FXML
    private StackPane stack;
    @FXML
    private ImageView Slots,Rooms,visitors,anchor;
    private boolean v_selected,r_selected,s_selected;
    @FXML
    private Label v_info,r_info,s_info;
    @FXML
    private Rectangle rect;
    public void button_transition(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(51, 51, 255));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(51, 51, 255), Color.rgb(56, 56, 56));
    }
    public void log_out(MouseEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOG OUT");
        alert.setHeaderText("Going to login page");
        alert.setContentText("Are you sure you want to logout?");
        alert.setX(HelloApplication.stg.getX() + 200);
        alert.setY(HelloApplication.stg.getY() + 215);
        if (alert.showAndWait().get() == ButtonType.OK)
        {
            h.changescene("starting.fxml");
        }
    }
    public void switch_to_visitor (MouseEvent e) throws IOException {
        Animation.fade_transition((Node)e.getSource(),"Visitor.fxml");
    }
    public void switch_to_rooms (MouseEvent e) throws IOException {
        Animation.fade_transition((Node)e.getSource(),"roomhomepage.fxml");
    }
    public void switch_to_slots (MouseEvent e) throws IOException {
        Animation.fade_transition((Node)e.getSource(),"slots.fxml");
    }

    public void hover_in(MouseEvent actionEvent) throws IOException  {
        Animation.enlarge_image((Node)actionEvent.getSource());
        if((Node)actionEvent.getSource()==visitors){
//            Rooms.setEffect(Animation.blur);
//            Slots.setEffect(Animation.blur);
            v_selected=true;
            r_selected=false;
            s_selected=false;
        } else if ((Node)actionEvent.getSource()==Rooms) {
//            visitors.setEffect(Animation.blur);
//            Slots.setEffect(Animation.blur);
            v_selected=false;
            r_selected=true;
            s_selected=false;
        } else if ((Node)actionEvent.getSource()==Slots) {
//            visitors.setEffect(Animation.blur);
//            Rooms.setEffect(Animation.blur);
            v_selected = false;
            r_selected = false;
            s_selected = true;
        }else if((Node)actionEvent.getSource()==stack) {
//            visitors.setEffect(null);
//            Rooms.setEffect(null);
//            Slots.setEffect(null);
            v_selected = false;
            r_selected = false;
            s_selected = false;
        }
        if(v_selected==true)
        {
//            Animation.fade_in(v_info);
//            Animation.fade_out(r_info);
//            Animation.fade_out(s_info);
            Rooms.setEffect(Animation.blur);
            Slots.setEffect(Animation.blur);
            visitors.setEffect(null);
        }
        else if (r_selected==true)
        {
//            Animation.fade_in(r_info);
//            Animation.fade_out(v_info);
//            Animation.fade_out(s_info);
            Rooms.setEffect(null);
            Slots.setEffect(Animation.blur);
            visitors.setEffect(Animation.blur);
        }
        else if (s_selected==true)
        {
//            Animation.fade_in(s_info);
//            Animation.fade_out(v_info);
//            Animation.fade_out(r_info);
            Rooms.setEffect(Animation.blur);
            Slots.setEffect(null);
            visitors.setEffect(Animation.blur);
        }
        else
        {
//            Animation.fade_out(v_info);
//            Animation.fade_out(r_info);
//            Animation.fade_out(s_info);

            Rooms.setEffect(null);
            Slots.setEffect(null);
            visitors.setEffect(null);
        }
    }
    public void hover_out(MouseEvent actionEvent) throws IOException  {
        Animation.ensmall_image((Node)actionEvent.getSource());
    }


}
