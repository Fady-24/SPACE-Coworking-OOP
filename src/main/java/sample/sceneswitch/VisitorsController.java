package sample.sceneswitch;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
public class VisitorsController
{
    @FXML
    private ImageView adminhome;
    @FXML
    private TextField searchField;
    public static Visitor visitor;
    @FXML
    private Label visitorid,visitorname,visitortype,visitortotalhours,visitornotfound;
    @FXML
    private AnchorPane visitorinfo;
    @FXML
    private Visitor CurrentVisitor;


    public void search (MouseEvent e) throws IOException, ClassNotFoundException
    {

        ArrayList<Visitor> visitors = DataHandling.getVisitors();
        if (searchField.getText().isEmpty())
        {
            visitornotfound.setVisible(true);
            visitornotfound.setText("Make sure to enter ID first");
            Animation.fade_in(visitornotfound);
            Animation.fade_out(visitorinfo);
        }
        else
        {
            int ID=Integer.parseInt(searchField.getText());
            for (Visitor visitor : visitors)
            {
                if (visitor.getID() == ID)
                {
                    Animation.fade_in(visitorinfo);
                    visitornotfound.setVisible(false);
                    CurrentVisitor = visitor;
                    visitorid.setText("VISITOR ID :  " + CurrentVisitor.getID());
                    visitorname.setText("VISITOR NAME :  " + CurrentVisitor.getName());
                    visitortype.setText("VISITOR TYPE :  " + CurrentVisitor.getType());
                    visitortotalhours.setText("VISITOR TOTAL HOURS :  " + CurrentVisitor.getHours());
                    break;
                }
                else
                {
                    visitornotfound.setText("User Not Found!");
                    visitornotfound.setVisible(true);
                    Animation.fade_in(visitornotfound);
                    Animation.fade_out(visitorinfo);
                }
            }
        }
    }
    public void homelogo1(MouseEvent e)
    {
        Animation.changeImageSmoothly(adminhome, "Asset_262.png");
    }
    public void homelogo2(MouseEvent e)
    {
        Animation.changeImageSmoothly(adminhome, "Asset_26_1.png");
    }
    public void adminhome(MouseEvent e) throws IOException, ClassNotFoundException
    {
        Animation.fade_transition((Node)e.getSource(),"Admin.fxml");
    }
    public void searchbuttontransition(MouseEvent e)
    {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(29,29,29), Color.rgb(56, 56, 56));
    }
    public void searchbuttontransition2(MouseEvent e)
    {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(29, 29, 29));
    }
    public void button_transition(MouseEvent e)
    {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(51, 51, 255));
    }

    public void button_transition2(MouseEvent e)
    {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(51, 51, 255), Color.rgb(56, 56, 56));
    }


    public void deletevisitor(MouseEvent e) throws IOException, ClassNotFoundException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Visitor");
        alert.setHeaderText("Confirm Delete Visitor");
        alert.setContentText("Are you sure you want to delete this Visitor?");
        if(alert.showAndWait().get() == ButtonType.OK)
        {
            ArrayList<Visitor> visitors = DataHandling.getVisitors();
            System.out.println(visitors);
            try
            {
                visitors.remove(CurrentVisitor.getID() - 1);
            }
            catch (Exception bounds)
            {

            }
            DataHandling.setVisitors(visitors);
            Animation.fade_out(visitorinfo);
            System.out.println("DONE");
        }
    }
    public void switch_to_leaderboard(MouseEvent e) throws IOException
    {
        HelloApplication h = new HelloApplication();
        h.changescene("leaderboard.fxml");
    }
}
