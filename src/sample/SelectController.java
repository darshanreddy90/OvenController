package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by dxr141430 on 3/13/2016.
 */
public class SelectController implements Initializable{
    private List<Cycle> cycles;
    private Cycle selectedCycle;

    @FXML
    private Text ovenTemperatureText;

    @FXML
    private Text currentTimeText;
    @FXML
    private ListView<Cycle> cycleList;
    @FXML
    private Button addCycleBtn;



    @FXML
    private Button canceSelectBtn;
    private Oven oven;

    @FXML
    public void startCycle(ActionEvent event){
        this.oven.setCurrentCycle(selectedCycle);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            HomeController homeController = fxmlLoader.<HomeController>getController();
            homeController.setOven(oven);
            Stage stage = (Stage)canceSelectBtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void addCycle(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addCycle.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            AddController addController = fxmlLoader.<AddController>getController();
            addController.setOven(oven);
            Stage stage = (Stage)canceSelectBtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private TableView<CycleStep> cycleTableView;

    @FXML
    private TableColumn<CycleStep, String> startTempClm;
    @FXML
    private TableColumn<CycleStep, String> endTempClm;
    @FXML
    private TableColumn<CycleStep, String> timeClm;

    @FXML
    public void goBackHome(ActionEvent event){
        Scene scene1;
        Parent selectCycle;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            HomeController homeController = fxmlLoader.<HomeController>getController();
            homeController.setOven(oven);
            Stage stage = (Stage)canceSelectBtn.getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setListView() {
        ObservableList<Cycle> items = FXCollections.observableArrayList (
                cycles);
        cycleList.setItems(items);
        cycleList.setCellFactory(new Callback<ListView<Cycle>, ListCell<Cycle>>() {
            @Override
            public ListCell<Cycle> call(ListView<Cycle> param) {
                ListCell<Cycle> cell = new ListCell<Cycle>(){
                    @Override
                    protected void updateItem(Cycle t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            String visible = t.getName();
                            setText(visible);
                        }
                    }
                };
                return cell;
            }
        });

        startTempClm.setCellValueFactory(new PropertyValueFactory<CycleStep, String>("startTemp"));
        endTempClm.setCellValueFactory(new PropertyValueFactory<CycleStep, String>("endTemp"));
        timeClm.setCellValueFactory(new PropertyValueFactory<CycleStep, String>("timeInMinutes"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            startListening();
            cycles = Cycle.cycleLoader();
            setListView();

            cycleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cycle>() {

                @Override
                public void changed(ObservableValue<? extends Cycle> observable, Cycle oldValue, Cycle newValue) {
                    if (newValue!=null) {
                        selectedCycle = newValue;
                        populateEditingViewWithSelectedRebate();
                    }
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void populateEditingViewWithSelectedRebate() {


        cycleTableView.getItems().setAll(selectedCycle.getCycleSteps());

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
