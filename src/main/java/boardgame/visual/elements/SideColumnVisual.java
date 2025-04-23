package boardgame.visual.elements;

import java.util.List;

import boardgame.model.boardFiles.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SideColumnVisual extends VBox {
    DiceAnimation diceAnimation = new DiceAnimation();

    List<Player> players;

    public SideColumnVisual(List<Player> players) {
        this.players = players;

        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
        this.setPrefWidth(400);
        this.getChildren().add(new PlayerRowsVisual(players).getPlayerRows());
        this.getChildren().add(diceAnimation.getDiceBase());

    }

    public VBox getColumn() {
        return this;
    }

    

    
    
}
