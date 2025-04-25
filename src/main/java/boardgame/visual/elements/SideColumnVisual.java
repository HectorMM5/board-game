package boardgame.visual.elements;

import java.util.List;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;
import boardgame.visual.scenes.Ingame;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SideColumnVisual extends VBox {
    private final DiceAnimation diceAnimation;
    private final ButtonVisual rollButton;
    private final Ingame ingame;

    public SideColumnVisual(GameController gameController, List<Player> players, Ingame ingame) {
        System.out.println("Reached SideColumnVisual with player list size: " + players.size());

        this.setPrefWidth(500);
        this.setSpacing(150);
        this.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));

        this.diceAnimation = new DiceAnimation();
        this.rollButton = new ButtonVisual();
        this.ingame = ingame;

        rollButton.setOnAction(e -> {
            ingame.handleRollDice(rollButton);
            rollButton.setDisable(true);
            
        });

        BorderPane diceWrapper = new BorderPane();
        diceWrapper.setCenter(diceAnimation);
        diceWrapper.setMaxWidth(Double.MAX_VALUE);
        diceWrapper.setPrefWidth(500);

        this.getChildren().add(diceWrapper);
        this.getChildren().add(rollButton); 
        this.getChildren().add(new PlayerRowsVisual(players).getPlayerRows());

        this.setAlignment(Pos.CENTER); 
    }

    public void turnOnButton() {
        rollButton.setDisable(false);
    }

    public void displayRoll(int diceRoll) {
        diceAnimation.displayRoll(diceRoll);
    }

    public ButtonVisual getRollButton() {
        return rollButton;
    }

    
    
}