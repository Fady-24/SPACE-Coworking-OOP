package sample.sceneswitch;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;

public class Animation {



    public static void changeImageSmoothly(ImageView imageView, String newImagePath) {
        InputStream is = Animation.class.getResourceAsStream(newImagePath);
            imageView.setImage(new Image(is));

            // Create a fade-in transition
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), imageView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();

    }
    public static void fade_transition(Node button,String path) {  // Still needs configuration
        FadeTransition ft = new FadeTransition(Duration.millis(250), button);
        ft.setFromValue(1.0);
        ft.setToValue(0);

        ft.setOnFinished((ActionEvent event) -> {
           HelloApplication h = new HelloApplication();
            try {
                h.changescene(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });


        ft.play();
    }

    public static void fade_in(Node button){
        FadeTransition ft = new FadeTransition(Duration.millis(300), button);
        button.setOpacity(0.0);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    public static void fade_out(Node button){
        FadeTransition ft = new FadeTransition(Duration.millis(300), button);
        ft.setFromValue(button.getOpacity());
        ft.setToValue(0.0);
        ft.play();
    }

    public static void colorfillin(Shape button, Color colorfrom, Color colorto) {
        FillTransition fl = new FillTransition(Duration.millis(500), button);
        fl.setFromValue(colorfrom);
        fl.setToValue(colorto);
        fl.play();

    }
    public static void enlarge_image(Node image) {
        ScaleTransition st = new ScaleTransition(Duration.millis(300), image);
        st.setFromX(1.0);
        st.setFromY(1.0);
        st.setToX(1.2);
        st.setToY(1.2);
        st.play();
    }
    public static void ensmall_image(Node image) {
        ScaleTransition st = new ScaleTransition(Duration.millis(300), image);
        st.setFromX(1.2);
        st.setFromY(1.2);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }
    public static void colorfillout(Shape button, Color colorfrom, Color colorto) {
        FillTransition fl = new FillTransition(Duration.millis(500), button);
        fl.setFromValue(colorfrom);
        fl.setToValue(colorto);
        fl.play();
    }
    public static void animate(ImageView imageView) {
        // Parameters for circular motion
        double centerX = 420; // Center of the circle (x-coordinate)
        double centerY = 300; // Center of the circle (y-coordinate)
        double radius = 10;  // Radius of the circle
        double animationDuration = 5; // Duration of one full circle in seconds

        // Create a Timeline for the circular motion
        Timeline timeline = new Timeline();
        int framesPerSecond = 60; // Smooth animation
        double angleStep = 360.0 / (animationDuration * framesPerSecond); // Degrees per frame

        for (int i = 0; i < framesPerSecond * animationDuration; i++) {
            double angle = i * angleStep;
            double radians = Math.toRadians(angle);
            double x = centerX + radius * Math.cos(radians) - imageView.getFitWidth() / 2;
            double y = centerY + radius * Math.sin(radians) - imageView.getFitHeight() / 2;

            // Create a KeyFrame to update the position
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(i * 1000.0 / framesPerSecond),
                    e -> {
                        imageView.setLayoutX(x);
                        imageView.setLayoutY(y);
                    }
            );

            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat the animation indefinitely
        timeline.play();
    }
    public static Effect blur=new BoxBlur(5.0,5.0,1);


}
