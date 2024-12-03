package sample.sceneswitch;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.IOException;

public class Animation {




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
        FadeTransition ft = new FadeTransition(Duration.millis(450), button);
        button.setOpacity(0.0);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }
    public static void enlarge(Shape button, Color colorfrom, Color colorto) {
        FillTransition fl = new FillTransition(Duration.millis(500), button);
        fl.setFromValue(colorfrom);
        fl.setToValue(colorto);
        System.out.println("ay 7aga");
        fl.play();

    }
    public static void en_small(Shape button, Color colorfrom, Color colorto) {
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
}
