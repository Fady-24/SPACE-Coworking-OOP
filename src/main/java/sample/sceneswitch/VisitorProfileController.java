package sample.sceneswitch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class VisitorProfileController implements Initializable
{
    @FXML
    Label welcome,visitortypelabel,visitortotalhourslabel,balance,reser_details,freehours_label;
    @FXML
    Rectangle rect;
    @FXML
    ImageView reservations,icon,ReservationButton;

    Visitor v = LoginController.Currentvisitor;

    public void SwitchToReservation(MouseEvent e)
    {
    Animation.fade_transition((Node) e.getSource(),"AddReservation.fxml");
    }

    public void user_logout(MouseEvent e) throws IOException
    {
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
    public void hover_in(MouseEvent e)
    {
        Animation.enlarge_image((Node)e.getSource());
    }
    public void hover_out(MouseEvent e)
    {
        Animation.ensmall_image(((Node)e.getSource()));
    }
    public void button_transition(MouseEvent e)
    {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(51,51,255));
    }
    public void button_transition2(MouseEvent e)
    {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(51, 51, 255), Color.rgb(56, 56, 56));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ArrayList<Visitor> sorted =DataHandling.getVisitors();
        sorted.sort(Comparator.comparing(Visitor::getHours).reversed());;

        if (v.getID() == sorted.getFirst().getID() && !v.isRewardapplied()&&sorted.size()>7)
        {
            v.setFreehours(v.getFreehours()+7);
            v.setRewardapplied(true);
        }
        else if (v.getID() != sorted.getFirst().getID()&&v.getFreehours()==0)
        {
            v.setRewardapplied(false);
        }


        if (!v.V_list_of_slots.isEmpty())
        {
            reser_details.setText(v.V_list_of_slots.getLast().toString());
        }
        else
        {
            reser_details.setText("There are no current reservations");
        }
        visitortypelabel.setText(v.getType());
        if (v.getType().equals("INSTRUCTOR"))
        {
            Animation.changeImage(icon,"Asset 58.png");

        }
        else if(v.getType().equals("FORMAL"))
        {
            Animation.changeImage(icon,"Asset 61.png");
        }
        visitortotalhourslabel.setText(String.valueOf(v.getHours()));
        welcome.setText(v.getName().toUpperCase().split(" ")[0]);
        balance.setText(String.valueOf(v.getBalance())+" $");
        if (v.getFreehours()>0)
        {
            freehours_label.setText("you have " + v.getFreehours() + " free hours remaining");
        }
        else
        {
            freehours_label.setText(" ");
        }
        System.out.println(v.getType());
    }
}
