package boardgame.visual.scenes;
import java.util.ArrayList;

import boardgame.model.boardFiles.Player;
import boardgame.visual.elements.Ingame;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SnakesAndLaddersInitScreen extends GameInitScreen {

    public SnakesAndLaddersInitScreen(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public void startGame(Button button){
        ArrayList<Player> playerList = this.getCurrentPlayers();
        if (playerList != null && playerList.size() > 1) {
            new Ingame().start(this.primaryStage, playerList);
            return;
        }
        System.out.println("Please select at least 1 player");
    }

}
