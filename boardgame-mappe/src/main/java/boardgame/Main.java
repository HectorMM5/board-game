package boardgame;

import boardgame.visual.elements.GameInterface;
import javafx.application.Application;
import javafx.stage.Stage;

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