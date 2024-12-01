package sample.sceneswitch;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {

    public static void fade_transition(Node button) {  // Still needs configuration
        FadeTransition ft = new FadeTransition(Duration.millis(500), button);
        button.setOpacity(0.7);
        ft.setFromValue(0.7);
        ft.setToValue(1.0);
        ft.autoReverseProperty().set(true);

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
