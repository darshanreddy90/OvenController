package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import sun.dc.pr.PRError;

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

    @FXML
    private Text cuurentStepStarttxt;

    @FXML
    private Text cuurentStepEndTxt;

    @FXML
    private Text cuurentStepTimeTxt;

    @FXML
    private AnchorPane currentStepPane;

    @FXML
    private Text previousStepStartTxt;

    @FXML
    private Text previousStepEndTxt;

    @FXML
    private Text previousStepTimeTxt;

    @FXML
    private AnchorPane prevStepPane;

    @FXML
    private Text nextStepStartTxt;

    @FXML
    private Text nextStepStopTxt;

    @FXML
    private Text nextStepTimeTxt;

    @FXML
    private AnchorPane nextStepPane;


    private Oven oven;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void turnOvenOnBtn(){
        this.oven.clearCycle();
        this.oven.turnOvenOn();
    }

    @FXML
    public void turnOvenOfftn(){

        this.oven.clearCycle();
        this.oven.turnOvenOff();
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
                if(oven.getCurrentCycle() == null) {

                    currentStepPane.setVisible(false);
                    prevStepPane.setVisible(false);
                    nextStepPane.setVisible(false);
                }
                if( oven.getCurrentCycle() != null && oven.getCurrentCycle().getCurrentStep() !=  null) {
                    currentStepPane.setVisible(true);
                    cuurentStepStarttxt.setText(oven.getCurrentCycle().getCurrentStep().getStartTemp()+"");
                    cuurentStepEndTxt.setText(oven.getCurrentCycle().getCurrentStep().getEndTemp()+"");
                    cuurentStepTimeTxt.setText(oven.getCurrentCycle().getCurrentStep().getTimeInMinutes()+"");
                } else {
                    currentStepPane.setVisible(false);
                }
                if( oven.getCurrentCycle() != null && oven.getCurrentCycle().getPreviousStep() !=  null) {
                    prevStepPane.setVisible(true);
                    previousStepStartTxt.setText(oven.getCurrentCycle().getPreviousStep().getStartTemp()+"");
                    previousStepEndTxt.setText(oven.getCurrentCycle().getPreviousStep().getEndTemp()+"");
                    previousStepTimeTxt.setText(oven.getCurrentCycle().getPreviousStep().getTimeInMinutes()+"");
                } else {
                    prevStepPane.setVisible(false);
                }
                if( oven.getCurrentCycle() != null && oven.getCurrentCycle().getNextStep() !=  null) {
                    nextStepPane.setVisible(true);
                    nextStepStartTxt.setText(oven.getCurrentCycle().getNextStep().getStartTemp()+"");
                    nextStepStopTxt.setText(oven.getCurrentCycle().getNextStep().getEndTemp()+"");
                    nextStepTimeTxt.setText(oven.getCurrentCycle().getNextStep().getTimeInMinutes()+"");
                } else {
                    nextStepPane.setVisible(false);
                }
            }
        }, 0, 1000);
    }
}
