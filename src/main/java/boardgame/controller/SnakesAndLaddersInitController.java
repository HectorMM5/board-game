package boardgame.controller;
import java.util.ArrayList;

import boardgame.model.boardFiles.Player;
import boardgame.utils.GameSetup;
import boardgame.visual.elements.Ingame;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SnakesAndLaddersInitController extends GameInitController {

    public SnakesAndLaddersInitController(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    public void startGame(){
        ArrayList<Player> playerList = this.getCurrentPlayers();
        if (playerList != null && playerList.size() > 1) {
            new Ingame(new GameSetup("SnL", 0, playerList)).createGameScene(this.primaryStage);
            return;
        }
        System.out.println("Please select at least 2 players!");
    }

}
