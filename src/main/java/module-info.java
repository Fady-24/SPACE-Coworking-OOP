module sample.sceneswitch {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;


    opens sample.sceneswitch to javafx.fxml;
    exports sample.sceneswitch;
}