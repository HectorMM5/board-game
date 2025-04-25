package boardgame.visual.gameLayers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import boardgame.model.boardFiles.Player;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class PlayerTokenLayer extends Pane {

    private final Map<Player, ImageView> playerTokens = new HashMap<>();
    private final Map<Integer, Integer> cols = new HashMap<>();
    private final Map<Integer, Integer> rows = new HashMap<>();

    public PlayerTokenLayer(List<Player> players) {

        this.setPrefSize(536, 482);
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        for (Player player : players) {
            ImageView token = new ImageView(new Image(player.getIcon()));
            token.setFitWidth(50);
            token.setFitHeight(50);
            playerTokens.put(player, token);
            this.getChildren().add(token);
        }

        AtomicBoolean movesRight = new AtomicBoolean(false);

        IntStream.rangeClosed(0, 89).forEach(i -> {

            if ((i % 10) == 0) {
                movesRight.set(!movesRight.get());
            }

            int row = i / 10;
            int col = movesRight.get()
                    ? i % 10
                    : 10 - ((i % 10) + 1);

            cols.put(i + 1, col);
            rows.put(i + 1, row);

            System.out.println("Row: " + row + " Col: " + col + " Tile: " + (i + 1));
        });


    }

    public void moveToken(Player player, int tileNumber) {
        ImageView token = playerTokens.get(player);

        int spacing = 54; // TILE_SIZE (50) + GAP (4)   

        int col = cols.get(tileNumber); 
        int row = rows.get(tileNumber);

        double targetX = col * spacing;
        double targetY = row * spacing;

        token.setLayoutX(0);
        token.setLayoutY(0);

        TranslateTransition move = new TranslateTransition(Duration.millis(300), token);
        move.setToX(targetX);
        move.setToY(targetY);
        move.play();

    }

    public void movePlayerThroughPath(Player player, int endTile) {
        int playerPosition = player.getPosition();
        IntStream.rangeClosed(0, endTile - playerPosition).forEach(i -> {
            PauseTransition pause = new PauseTransition(Duration.millis(i * 200));
            pause.setOnFinished(event -> {
                moveToken(player, playerPosition + i);

            });
            pause.play();
        });

        
    }

    //public void moveStep(Player player, int position) {
    //    PauseTransition pause = new PauseTransition(Duration.millis(100));
    //    pause.setOnFinished(e -> {
    //        moveToken(player, position + 1);
    //    });
    //    pause.play();
    //}

}
