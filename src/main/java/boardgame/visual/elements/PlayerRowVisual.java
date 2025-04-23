package boardgame.visual.elements;

import boardgame.model.boardFiles.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerRowVisual {

    private HBox row;
    private Circle turnCircle;

    public PlayerRowVisual(Player player) {
        row = new HBox();

        row.setBackground(Background.fill(Color.WHITE));

        ImageView playerIcon = new ImageView(new Image(getClass().getResourceAsStream(player.getIcon())));

        playerIcon.setFitWidth(50);
        playerIcon.setFitHeight(50);

        Label name = new Label(player.getName());

        turnCircle = new Circle(3, Color.WHITE);

        row.getChildren().addAll(playerIcon, name);


    }

    public void toggleTurn() {
        turnCircle.setFill(Color.YELLOW);
        
    }

    public HBox getRow() {
        return row;
    }

    
 
    
}
