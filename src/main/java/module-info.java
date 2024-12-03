module sample.sceneswitch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sample.sceneswitch to javafx.fxml;
    exports sample.sceneswitch;
}