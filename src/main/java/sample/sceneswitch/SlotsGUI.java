package sample.sceneswitch;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.w3c.dom.Node;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotsGUI implements intializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML private DatePicker SlotDate;
    @FXML private Label Date;
    @FXML private ChoiceBox <Time> SlotTimeFrom;
    @FXML private ChoiceBox <Time> SlotTimeTo;
    @FXML private Label TimeFrom;
    private List<LocalTime> starttimeslots=new ArrayList<>();
    @FXML private Label TimeTo;
    @FXML private ChoiceBox startTime;
    @FXML private Label SlotTimeLabel;
    @FXML private DatePicker slotdate;
    @FXML private Label slotdateLabel;
    private List<LocalTime> timeslotsfrom=new ArrayList<>();
    private List<LocalTime> timeslotsto=new ArrayList<>();
    public void addtime()
    {
       for(int hour=9;hour<=19;hour++)
       {
           timeslotsfrom.add(LocalTime.of(hour,0));
       }
        for(int hour=10;hour<=20;hour++)
        {
            timeslotsto.add(LocalTime.of(hour,0));
        }
       for(LocalTime time:timeslotsfrom)
       {
           SlotTimeFrom.getItems().add(Time.valueOf(time.toString()));
       }
        for(LocalTime time:timeslotsfrom)
        {
            SlotTimeTo.getItems().add(Time.valueOf(time.toString()));
        }
        //SlotTimeFrom.setOnAction(this::gettime())
    }
     public void gettime(ActionEvent event)
     {
         Time mytime1=SlotTimeFrom.getValue();
         TimeFrom.setText(mytime1.toString());
        Time mytime2=SlotTimeFrom.getValue();
        TimeFrom.setText(mytime2.toString());
     }
    public void getdate() {
        LocalDate myDate = SlotDate.getValue();
        //Slots slot=new Slots(myDate,)
        Date.setText("Date");
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("slots.fxml"));
        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScence2(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("slotsscene2.fxml"));
        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showdata() {
        for (int hour = 9; hour <= 19; hour++) {
            starttimeslots.add(LocalTime.of(hour, 0));
        }
        for (LocalTime time : starttimeslots) {
            startTime.getItems().add(Time.valueOf(time.toString()));
        }
        SlotTimeLabel.setText("Time");
        Object mytime = startTime.getValue();
        LocalDate myDate = slotdate.getValue();
        slotdateLabel.setText("Date");
        //array is created and search in it then display data or delated them from delate button
    }
}
