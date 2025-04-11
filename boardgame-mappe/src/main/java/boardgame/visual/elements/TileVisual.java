package boardgame.visual.elements;

import java.util.List;

import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TileVisual extends StackPane {
    private final Tile tile;
    private final Rectangle background;
    private final ImageView iconView;
    private final Pane cover;
    private final Label playersInTile;

    public TileVisual(Tile tile) {
        this.tile = tile;
        this.background = new Rectangle(50, 50);
        background.setFill(Color.rgb(255, 255, 224));

        this.iconView = new ImageView();
        iconView.setFitWidth(40);
        iconView.setFitHeight(40);
        
        Label viewNumber = new Label(Integer.toString(tile.getNumber()));

        this.cover = new Pane();
        cover.setPrefSize(50, 50);
        cover.setBackground(Background.EMPTY);

        this.playersInTile = new Label();


        this.getChildren().addAll(background, viewNumber, iconView, cover, playersInTile);

    }

    public void updateVisual() {
        if (tile.getEffect() != null) {
            this.background.setFill(Color.rgb(205, 84, 84));
        }
        
        List<Player> players = tile.getPlayers();

        if (!players.isEmpty()) {
            background.setFill(Color.LIGHTYELLOW);

            if (players.size() == 1) {
                String iconPath = players.get(0).getIcon();
                Image playerIcon = new Image(getClass().getResourceAsStream(iconPath));
                iconView.setImage(playerIcon);
                playersInTile.setText("");
                cover.setBackground(Background.EMPTY);
            }

            else {
                cover.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                playersInTile.setText(Integer.toString(players.size()));

            }
            

        } else if (iconView.getImage() != null || playersInTile.getText().equals("")) {
            iconView.setImage(null);
            playersInTile.setText("");
            cover.setBackground(Background.EMPTY);
            
        }

    }

    public Tile getTile() {
        return tile;
    }

}  
