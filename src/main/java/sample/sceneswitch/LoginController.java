package sample.sceneswitch;
import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField textfield01;
    @FXML
    private Label mylabel;
    @FXML
    private PasswordField passfield;
    @FXML
    private AnchorPane anchor, headanchor, anchor_credit;
    @FXML
    private ImageView creditsimage;
    //public static Visitor CurrentVisitor;

    int age;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public static Visitor Currentvisitor;
    public void switch_to_register (ActionEvent e) throws IOException
    {
        Animation.fade_transition(anchor,"register.fxml");
    }
    public void switch_to_credits (MouseEvent e)
    {
        Animation.fade_out(anchor);
        Animation.fade_out(headanchor);
        Animation.fade_in(anchor_credit);
        anchor_credit.setMouseTransparent(false);
    }
    public void switch_to_login(MouseEvent e)
    {
        Animation.fade_in(anchor);
        Animation.fade_in(headanchor);
        Animation.fade_out(anchor_credit);
        anchor_credit.setMouseTransparent(true);
    }
    public void imagefillin(MouseEvent e)
    {
        Animation.fade_out(creditsimage);
        Animation.changeImage(creditsimage,"Asset_79.png");
        Animation.fade_in(creditsimage);
    }
    public void imagefillout(MouseEvent e)
    {
        Animation.fade_out(creditsimage);
        Animation.changeImage(creditsimage,"Asset_78.png");
        Animation.fade_in(creditsimage);
    }
    public void backbutton_transition(MouseEvent e)
    {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56,56,56), Color.rgb(246, 201, 14));
    }
    public void backbutton_transition2(MouseEvent e)
    {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(246, 201, 14), Color.rgb(56,56,56));
    }
    public void button_transition(MouseEvent e) {
        Animation.colorfillin((Shape) e.getSource(), Color.rgb(56, 56, 56), Color.rgb(126, 96, 191));
    }
    public void button_transition2(MouseEvent e) {
        Animation.colorfillout((Shape) e.getSource(), Color.rgb(126, 96, 191), Color.rgb(56, 56, 56));
    }
    public void submit(MouseEvent actionEvent) throws IOException, ClassNotFoundException {

        ArrayList<Visitor> visitors = DataHandling.getVisitors();
        System.out.println(DataHandling.getVisitors().size());
        HelloApplication h = new HelloApplication();
        String name = textfield01.getText();
        String pass = passfield.getText();

        if(name.equals(Admin.getName()) && pass.equals(Admin.getPassword()))
        {
            h.changescene("admin.fxml");
        }
        else if (!visitors.isEmpty())
        {
            for (Visitor visitor : visitors) {
                System.out.println(visitor.toString());
                if (visitor.getName().equals(name) && visitor.getPassword().equals(pass)) {
                    Currentvisitor = visitor;
                    System.out.println("current visitor " + Currentvisitor.getName());
                    System.out.println("current type " + Currentvisitor.getType());
                    mylabel.setText("Successfully logged in");
                    h.changescene("scene1.fxml");
                    break;
                }
                else
                {
                    mylabel.setText("Incorrect username or password");
                }
            }
        }
        else
        {
            mylabel.setText("Incorrect username or password");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animation.fade_in(anchor);
    }
}
