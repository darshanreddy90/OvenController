package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        HomeController homeController = fxmlLoader.<HomeController>getController();

        Oven oven = new Oven(false, 0);
        CycleStep c1= new CycleStep(0,5,5);
        CycleStep c2= new CycleStep(5,10,5);

        Cycle cycle = new Cycle("Test");
        cycle.addStep(c1);
        cycle.addStep(c2);
        CycleExecutor executor = new CycleExecutor(cycle, oven);
        OvenRunner runner = new OvenRunner(oven, executor);
        runner.start();
        homeController.setOven(oven);
        primaryStage.setTitle("Oven Controller");
        primaryStage.setScene(new Scene(root, 1000, 800));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
