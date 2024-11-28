module sample.sceneswitch {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.sceneswitch to javafx.fxml;
    exports sample.sceneswitch;
}