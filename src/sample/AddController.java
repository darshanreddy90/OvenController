package sample;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.LinkedList;

/**
 * Created by DarshanNarayana on 3/13/2016.
 */
public class AddController implements Initializable {

    private Cycle selectedCycle;
    private LinkedList<CycleStep> cycleStepList;

    private Oven oven;

    @FXML
    private Text ovenTemperatureText;

    @FXML
    private Text currentTimeText;
    @FXML
    private TextField stepStartTempTxt;
    @FXML
    private TextField stepEndTempTxt;
    @FXML
    private TextField stepTimetxt;
    @FXML
    private TextField cycleNameTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button addStepBtn;

    @FXML
    private Button saveBtn;

    @FXML
    public void saveCycle(){
        selectedCycle = new Cycle(cycleNameTxt.getText());
        selectedCycle.setSteps(cycleStepList);
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter("C:\\Users\\DarshanNarayana\\Documents\\GitHub\\OvenController\\src\\sample\\data.txt", true));
            output.append(selectedCycle.convertToString());
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectCycle.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            SelectController selectController = fxmlLoader.<SelectController>getController();
            selectController.setOven(oven);
            Stage stage = (Stage)cancelBtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public void cancelAdd(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            HomeController homeController = fxmlLoader.<HomeController>getController();
            homeController.setOven(oven);
            Stage stage = (Stage)cancelBtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addStep(){
        CycleStep step = new CycleStep(Double.parseDouble(stepStartTempTxt.getText()), Double.parseDouble(stepEndTempTxt.getText()),Double.parseDouble( stepTimetxt.getText()));
        cycleStepList.add(step);
        cycleTableView.getItems().setAll(cycleStepList);
    }



    @FXML
    private TableView<CycleStep> cycleTableView;

    @FXML
    private TableColumn<CycleStep, String> startTempClm;
    @FXML
    private TableColumn<CycleStep, String> endTempClm;
    @FXML
    private TableColumn<CycleStep, String> timeClm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cycleStepList = new LinkedList<CycleStep>();
        startTempClm.setCellValueFactory(new PropertyValueFactory<CycleStep, String>("startTemp"));
        endTempClm.setCellValueFactory(new PropertyValueFactory<CycleStep, String>("endTemp"));
        timeClm.setCellValueFactory(new PropertyValueFactory<CycleStep, String>("timeInMinutes"));
        cycleTableView.getItems().setAll(cycleStepList);
        startListening();
    }

    public void setOven(Oven oven) {
        this.oven = oven;
    }

    private void startListening() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            ovenTemperatureText.setText(oven.getCurrentOvenTemperature() + "");
            LocalDateTime dateTime = LocalDateTime.now();
            currentTimeText.setText(dateTime.getHour() + ": " + dateTime.getMinute() + ": " + dateTime.getSecond());
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        }, 0, 1000);
    }


}
