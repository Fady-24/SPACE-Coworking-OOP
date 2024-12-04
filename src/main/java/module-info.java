module sample.sceneswitch {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;


    opens sample.sceneswitch to javafx.fxml;
    exports sample.sceneswitch;
}