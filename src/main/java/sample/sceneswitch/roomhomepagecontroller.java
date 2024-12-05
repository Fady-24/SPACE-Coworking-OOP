package sample.sceneswitch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.ResourceBundle;

public class roomhomepagecontroller {
    @FXML
    private Button grbutton, trbutton, mrbutton;
    @FXML
    private Rectangle rect1, rect2, rect3, lastselected;
    @FXML
    private boolean isgrselected,ismrselected, istrselected;
    @FXML
    private Label initlabel;
    @FXML
    private ImageView mr1, mr2, mr3, gr1, gr2, tr1, tr2, tr3;

    @FXML
    public void adminhome(MouseEvent event) {
        Animation.fade_transition((Node) event.getSource(), "admin.fxml");
    }

    @FXML
    public void onclick(MouseEvent event) {
        if ((Shape) event.getSource() == rect1) {
            isgrselected = true;
            ismrselected = false;
            istrselected = false;
        } else if ((Shape) event.getSource() == rect2) {
            isgrselected = false;
            ismrselected = true;
            istrselected = false;
        } else if ((Shape) event.getSource() == rect3) {
            isgrselected = false;
            ismrselected = false;
            istrselected = true;
        }
        if (isgrselected) {
            Animation.colorfillout(lastselected, Color.rgb(56, 56, 56), Color.rgb(56, 56, 56));
            Animation.colorfillin(rect1, Color.rgb(56, 56, 56), Color.rgb(61, 138, 165));
            if (lastselected != rect1) {
                lastselected = rect1;
            }
            mr1.setScaleX(0.0);mr1.setScaleY(0.0);mr2.setScaleX(0.0);mr2.setScaleY(0.0);mr3.setScaleX(0.0);mr3.setScaleY(0.0);
            gr1.setScaleX(1.0);gr1.setScaleY(1.0);gr2.setScaleX(1.0);gr2.setScaleY(1.0);
            tr1.setScaleX(0.0);tr1.setScaleY(0.0);tr2.setScaleX(0.0);tr2.setScaleY(0.0);tr3.setScaleX(0.0);tr3.setScaleY(0.0);
            initlabel.setScaleX(0.0);initlabel.setScaleY(0.0);
        } else if (ismrselected) {
            Animation.colorfillout(lastselected, Color.rgb(56, 56, 56), Color.rgb(56, 56, 56));
            Animation.colorfillin(rect2, Color.rgb(56, 56, 56), Color.rgb(165, 147, 106));
            if (lastselected != rect2) {
                lastselected = rect2;
            }
            mr1.setScaleX(1.0);mr1.setScaleY(1.0);mr2.setScaleX(1.0);mr2.setScaleY(1.0);mr3.setScaleX(1.0);mr3.setScaleY(1.0);
            gr1.setScaleX(0.0);gr1.setScaleY(0.0);gr2.setScaleX(0.0);gr2.setScaleY(0.0);
            tr1.setScaleX(0.0);tr1.setScaleY(0.0);tr2.setScaleX(0.0);tr2.setScaleY(0.0);tr3.setScaleX(0.0);tr3.setScaleY(0.0);
            initlabel.setScaleX(0.0);initlabel.setScaleY(0.0);
        } else if (istrselected) {
            Animation.colorfillout(lastselected, Color.rgb(56, 56, 56), Color.rgb(56, 56, 56));
            Animation.colorfillin(rect3, Color.rgb(56, 56, 56), Color.rgb(102, 163, 62));
            if (lastselected != rect3) {
                lastselected = rect3;
            }
            mr1.setScaleX(0.0);mr1.setScaleY(0.0);mr2.setScaleX(0.0);mr2.setScaleY(0.0);mr3.setScaleX(0.0);mr3.setScaleY(0.0);
            gr1.setScaleX(0.0);gr1.setScaleY(0.0);gr2.setScaleX(0.0);gr2.setScaleY(0.0);
            tr1.setScaleX(1.0);tr1.setScaleY(1.0);tr2.setScaleX(1.0);tr2.setScaleY(1.0);tr3.setScaleX(1.0);tr3.setScaleY(1.0);
            initlabel.setScaleX(0.0);initlabel.setScaleY(0.0);
        }

    }
}
