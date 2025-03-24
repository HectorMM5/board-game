package boardgame.Visuals;

import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileVisual extends StackPane {
    private final Tile tile;
    private final Rectangle background;
    private final ImageView iconView;

    public TileVisual(Tile tile) {
        this.tile = tile;
        this.background = new Rectangle(50, 50);
        this.background.setFill(Color.rgb(255, 255, 224));

        this.iconView = new ImageView();
        this.iconView.setFitWidth(40);
        this.iconView.setFitHeight(40);
        this.getChildren().addAll(background, iconView);

    }

    public void updateVisual() {
        Player player = tile.getPlayer();
        if (player != null) {
            String iconPath = player.getIcon(); // e.g., "/images/knight.png"
            Image playerIcon = new Image(getClass().getResourceAsStream(iconPath));
            iconView.setImage(playerIcon);
            background.setFill(Color.LIGHTYELLOW);

        } else {
            iconView.setImage(null);
            background.setFill(Color.LIGHTGRAY);
        }

        if (tile.getEffect() != null) {
            background.setStroke(Color.BLUE);
            background.setStrokeWidth(3);
        } else {
            background.setStroke(Color.BLACK);
            background.setStrokeWidth(1);
        }
    }

    public TileVisual getTile() {
        return this;
    }
}  
