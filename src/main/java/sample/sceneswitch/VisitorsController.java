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
public class VisitorsController {
    @FXML
    private ImageView search,adminhome;
    @FXML
    private TextField searchField;
    public static Visitor visitor;
    @FXML
    private Label visitorid,visitorname,visitortype,visitortotalhours,visitornotfound;
    @FXML
    private AnchorPane visitorinfo;
    @FXML
    private Button deletebutton;
    private Visitor CurrentVisitor;
    @FXML
    private Rectangle rect;

    public void search (MouseEvent e) throws IOException, ClassNotFoundException {
        int ID=Integer.parseInt(searchField.getText());
        File f = new File("visitor.dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        ArrayList<Visitor> visitors = (ArrayList<Visitor>) ois.readObject();
        for (Visitor visitor:visitors)
        {
            if(visitor.getID()==ID)
            {
                Animation.fade_in(visitorinfo);
                visitorid.setText("VISITOR ID :  "+visitor.getID());
                visitorname.setText("VISITOR NAME :  "+visitor.getName());
                visitortype.setText("VISITOR TYPE :  "+visitor.getType());
                CurrentVisitor=visitor;
                visitornotfound.setOpacity(0);
            }
            else {
                Animation.fade_in(visitornotfound);
                Animation.fade_out(visitorinfo);
            }
        }
    }


    public void adminhome(MouseEvent e) throws IOException, ClassNotFoundException {
        Animation.fade_transition((Node)e.getSource(),"Admin.fxml");
    }

    public void button_transition(MouseEvent e) {
        Animation.enlarge((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(255, 128, 78));
    }

    public void button_transition2(MouseEvent e) {
        Animation.en_small((Shape) e.getSource(), Color.rgb(255, 128, 78), Color.rgb(56, 56, 56));
    }


    public void deletevisitor(MouseEvent e) throws IOException, ClassNotFoundException {
        System.out.println("DONE");
        File f = new File("visitor.dat");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        ArrayList<Visitor> visitors = (ArrayList<Visitor>) ois.readObject();
        System.out.println(visitors);
        try {
            visitors.remove(CurrentVisitor.getID() - 1);
        }
        catch (Exception bounds){

        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(visitors);
            oos.close();
            Animation.fade_out(visitorinfo);
    }
}
