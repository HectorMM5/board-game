package boardgame.visual.elements;

import java.util.List;

import boardgame.model.boardFiles.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SideColumnVisual {
    DiceAnimation diceAnimation = new DiceAnimation();

    List<Player> players;
    VBox column;

    public SideColumnVisual(List<Player> players) {
        this.players = players;

        column = new VBox();
        column.setAlignment(Pos.CENTER);

        column.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));

        column.setPrefWidth(400);

        for (Player player : players) {
            column.getChildren().add(new PlayerRowVisual(player).getRow());
            
        }

        column.getChildren().add(diceAnimation.getDiceBase());

    }

    public VBox getColumn() {
        return column;
    }

    

    
    
}
