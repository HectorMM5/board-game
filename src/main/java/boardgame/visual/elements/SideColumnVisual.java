package boardgame.visual.elements;

import java.util.List;

import boardgame.model.boardFiles.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SideColumnVisual extends VBox {
    public SideColumnVisual(List<Player> players, DiceAnimation diceAnimation, ButtonVisual rollButton) {
        
        this.setPrefWidth(500);
        this.setSpacing(150);
        this.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));

        BorderPane diceWrapper = new BorderPane();
        diceWrapper.setCenter(diceAnimation);
        diceWrapper.setMaxWidth(Double.MAX_VALUE);
        diceWrapper.setPrefWidth(500);

        this.getChildren().add(diceWrapper);
        this.getChildren().add(rollButton); 
        this.getChildren().add(new PlayerRowsVisual(players).getPlayerRows());

        this.setAlignment(Pos.CENTER); 
    }

    public VBox getColumn() {
        return this;
    }
    
}