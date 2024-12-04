package sample.sceneswitch;

import javafx.animation.FillTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
    public void log_out(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changescene("starting.fxml");
    }
    public void switch_to_visitor (MouseEvent e) throws IOException {
        Animation.fade_transition((Node)e.getSource(),"Visitor.fxml");
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
