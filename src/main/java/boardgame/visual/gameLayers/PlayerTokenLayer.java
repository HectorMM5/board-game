package boardgame.visual.gameLayers;

import java.util.HashMap;
import java.util.Map;

import boardgame.controller.GameController;
import boardgame.controller.VisualController;
import boardgame.model.boardFiles.Player;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class PlayerTokenLayer extends Pane {

    private final Map<Player, ImageView> playerTokens = new HashMap<>();
    private final GameController gameController;
    private final VisualController visualController;

    public PlayerTokenLayer(GameController gameController, VisualController visualController) {
        this.gameController = gameController;
        this.visualController = visualController;
    
        this.setPrefSize(536, 482);
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        for (Player player : gameController.getPlayers()) {
            ImageView token = new ImageView(new Image(player.getIcon()));
            token.setFitWidth(50);
            token.setFitHeight(50);
            playerTokens.put(player, token);
            this.getChildren().add(token); 
        }   

        System.out.println("Rows map after initialization: " + visualController.getBoardVisual().getRows());
    }

    public void moveToken(Player player, int tileNumber) {
        ImageView token = playerTokens.get(player);

        int boardWidth = 10;
        int spacing = 54; // TILE_SIZE (50) + GAP (4)   

        int col = visualController.getBoardVisual().getCols().get(tileNumber); //TILENUMBER TAR INN DIREKTE DICE!!!! HUSK
        int row = visualController.getBoardVisual().getRows().get(tileNumber);

        System.out.println("Calling getRows() from: " + System.identityHashCode(visualController.getBoardVisual()));
        System.out.println("Calling getCols() from: " + System.identityHashCode(visualController.getBoardVisual()));

        System.out.println("Trying to access row for tile number: " + tileNumber);
        System.out.println("Rows map contains: " + visualController.getBoardVisual().getRows().containsKey(tileNumber));
        System.out.println("Cols map contains: " + visualController.getBoardVisual().getCols().containsKey(tileNumber));


        System.out.println(tileNumber);
        System.out.println(col);
        System.out.println(row);

        double targetX = col * spacing;
        double targetY = row * spacing;

        token.setLayoutX(0);
        token.setLayoutY(0);

        TranslateTransition move = new TranslateTransition(Duration.millis(300), token);
        move.setToX(targetX);
        move.setToY(targetY);
        move.play();
    }


}
