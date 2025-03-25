package boardgame;

import boardgame.Visuals.GameInterface;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        GameInterface ui = new GameInterface(primaryStage);
        ui.init();
        ui.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}