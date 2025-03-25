package boardgame.Visuals.views;
import boardgame.Visuals.Ingame;
import javafx.scene.control.Button;

import javafx.stage.Stage;

public class SnakesAndLaddersInitScreen extends GameInitScreen {

    public SnakesAndLaddersInitScreen(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public void startGame(Button button){
        new Ingame().start(this.primaryStage);
    }

}
