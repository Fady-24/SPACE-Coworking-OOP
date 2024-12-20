package sample.sceneswitch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML
    private AnchorPane medals_anchor;

    @FXML
    private ImageView right_arrow,left_arrow,home;
    @FXML
    private Label counter_label,visitor_names,visitor_hours,visitor_order;
    @FXML
    private Rectangle rect;

    int counter;
    ArrayList<Visitor> visitors=DataHandling.getVisitors();
    public void backbutton_transition(MouseEvent e)
    {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56,56,56), Color.rgb(246, 201, 14));
    }
    public void backbutton_transition2(MouseEvent e)
    {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(246, 201, 14), Color.rgb(56,56,56));
    }
    public void switch_to_visitor(MouseEvent e) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changescene("Visitor.fxml");
    }

    public void right_pressed(MouseEvent event)
    {
        left_arrow.setOpacity(1);
        left_arrow.setMouseTransparent(false);
        medals_anchor.setVisible(false);
        if (visitors.size()>(counter+1)*7)
        {
            counter=counter+1;
            counter_label.setText(String.valueOf(counter+1));
            page_check(counter);
        }
        if ((counter+1)*7 > visitors.size())
        {
            right_arrow.setMouseTransparent(true);
            right_arrow.setOpacity(0.5);
        }
    }
    public void left_pressed(MouseEvent event)
    {
        right_arrow.setMouseTransparent(false);
        right_arrow.setOpacity(1);
        if (counter-1 == 0)
        {
            counter--;
            counter_label.setText(String.valueOf(counter+1));
            left_arrow.setMouseTransparent(true);
            left_arrow.setOpacity(0.5);
            medals_anchor.setVisible(true);
            page_check(counter);

        }
        else if (counter == 0)
        {
            medals_anchor.setVisible(true);
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
    public void page_check(int counter)
    {
        String order="",names="",hours="";
        System.out.println(counter);
        for (int i = counter*7; i < ((counter*7)+7)&& i < visitors.size(); i++)
        {
            order=order+(i+1)+"\n";
            names=names+visitors.get(i).getName()+"\n";
            hours=hours+visitors.get(i).getHours()+"\n";

        }
            visitor_order.setText(order);
            visitor_hours.setText(hours);
            visitor_names.setText(names);

        }
    public void switch_back(MouseEvent mouseEvent)
    {
        Animation.fade_transition((Node) mouseEvent.getSource(),"admin.fxml");
    }
    public void homelogo1(MouseEvent e)
    {
        Animation.changeImageSmoothly(home, "Asset_262.png");
    }
    public void homelogo2(MouseEvent e)
    {
        Animation.changeImageSmoothly(home, "Asset_26_1.png");
    }

    @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
    {
            visitors.sort(Comparator.comparing(Visitor::getHours).reversed());
            counter=0;
            left_arrow.setOpacity(0.5);
            left_arrow.setMouseTransparent(true);
            right_arrow.setOpacity(1);
            right_arrow.setMouseTransparent(false);
            if (visitors.size()<=7)
            {
                right_arrow.setOpacity(0.5);
                right_arrow.setMouseTransparent(true);
            }
            page_check(counter);
        }

}


