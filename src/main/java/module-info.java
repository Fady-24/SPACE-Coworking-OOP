module sample.sceneswitch {
    requires java.sql;
    requires MaterialFX;


    opens sample.sceneswitch to javafx.fxml;
    exports sample.sceneswitch;
}