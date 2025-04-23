package boardgame.visual.elements;

import java.util.ArrayList;
import java.util.List;

import boardgame.model.boardFiles.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PlayerRowsVisual extends VBox {
    private List<HBox> playerRows = new ArrayList<>();

    public PlayerRowsVisual(List<Player> players) {
        players.stream().forEach(i -> createPlayerRow(i));
    }

    public void createPlayerRow(Player player) {
        HBox row = new HBox();
        row.setBackground(Background.fill(Color.WHITE));

        ImageView playerIcon = new ImageView(new Image(getClass().getResourceAsStream(player.getIcon())));

        playerIcon.setFitWidth(50);
        playerIcon.setFitHeight(50);
        Label name = new Label(player.getName());

        row.getChildren().addAll(playerIcon, name);

        this.getChildren().addAll(row);

    }

    public PlayerRowsVisual getPlayerRows() {
        return this;
    }  
 
    
}
