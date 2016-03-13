package sample;

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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by dxr141430 on 3/13/2016.
 */
public class SelectController implements Initializable{
    private List<Cycle> cycles;
    private Cycle selectedCycle;


    @FXML
    private ListView<Cycle> cycleList;

    @FXML
    private Button canceSelectBtn;

    @FXML
    public void startCycle(ActionEvent event){

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
            selectCycle = FXMLLoader.load(getClass().getResource("home.fxml"));
            scene1 = new Scene(selectCycle);
            Stage stage = (Stage)canceSelectBtn.getScene().getWindow();
            stage.setScene(scene1);
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
}
