package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class HomeController implements Initializable{
    @FXML
    private Text ovenTemperatureText;

    @FXML
    private Text currentTimeText;

    @FXML
    private Button selectCycleBtn;

    private Oven oven;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void goToSelectCycle(ActionEvent e){
        Scene scene1;
        Parent selectCycle;
        try {
            selectCycle = FXMLLoader.load(getClass().getResource("selectCycle.fxml"));
            scene1 = new Scene(selectCycle);
            Stage stage = (Stage)selectCycleBtn.getScene().getWindow();
            stage.setScene(scene1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void setOven(Oven oven) {
        this.oven = oven;
        startListening();
    }

    private void startListening() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ovenTemperatureText.setText(oven.getCurrentOvenTemperature()+"");
                LocalDateTime dateTime = LocalDateTime.now();
                currentTimeText.setText(dateTime.getHour()+": "+dateTime.getMinute()+": "+dateTime.getSecond());
            }
        }, 0, 1000);
    }
}
