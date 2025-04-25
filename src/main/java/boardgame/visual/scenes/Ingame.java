package boardgame.visual.scenes;

import java.util.ArrayList;
import java.util.List;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;
import boardgame.model.diceFiles.Dice;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.model.effectFiles.SnakeEffect;
import boardgame.utils.GameSetup;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.ButtonVisual;
import boardgame.visual.elements.SideColumnVisual;
import boardgame.visual.gameLayers.PlayerTokenLayer;
import boardgame.visual.gameLayers.SnakesNLadders.LadderLayer;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ingame {

    private final Board board;
    private final BoardVisual boardVisual;
    private final List<Player> players;
    private final SideColumnVisual sideColumn;
    private final PlayerTokenLayer playerTokenLayer;
    private final Dice dice = new Dice(1);
    GameController gameController;

    public Ingame(GameSetup gameSetup) {
        this.gameController = gameSetup.getGameController();
        this.board = gameSetup.getBoard();
        this.boardVisual = new BoardVisual(board);
        this.sideColumn = new SideColumnVisual(gameSetup.getGameController(), gameSetup.getPlayers(), this);
        this.playerTokenLayer = new PlayerTokenLayer(gameSetup.getPlayers());
        this.players = gameSetup.getPlayers();

    }

    public void createGameScene(Stage primaryStage) {

        gameController.setIngame(this);

        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(boardVisual);
    
        ArrayList<Tile> tilesWithLadders = new ArrayList<>();
        for (Tile tile : board.getTiles()) {
            if (tile.getEffect() instanceof LadderEffect) {
                tilesWithLadders.add(tile);
            }
    
            if (tile.getEffect() != null) {
                System.out.println("EFFECT IN: " + tile.getNumber());
            }
        }
    
        ArrayList<Tile> tilesWithSnakes = new ArrayList<>();
        for (Tile tile : board.getTiles()) {
            if (tile.getEffect() instanceof SnakeEffect) {
                tilesWithSnakes.add(tile);
            }
        }
    
        BorderPane root = new BorderPane();
        root.setLeft(sideColumn);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Board Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    
        LadderLayer ladders = new LadderLayer(boardVisual, tilesWithLadders, tilesWithSnakes);
        centerPane.getChildren().add(ladders);
        centerPane.getChildren().add(playerTokenLayer);
        root.setCenter(centerPane);

        gameController.start();
    
        boardVisual.updateEntireBoard();
    }

    public void moveBy(Player player, int steps, ButtonVisual buttonVisual) {
        int nextPosition = player.getPosition() + steps;

        playerTokenLayer.movePlayerThroughPath(player, nextPosition);
        PauseTransition finalPause = new PauseTransition(Duration.millis((nextPosition - player.getPosition() + 1) * 200));
        finalPause.setOnFinished(event -> {
            gameController.movePlayer(player, nextPosition);
            sideColumn.turnOnButton();
            
        });
        finalPause.play();

    }

    public void handleRollDice(ButtonVisual buttonVisual) {
        int diceRoll = dice.roll();
        System.out.println("Rolled: " + diceRoll);
        moveBy(gameController.getCurrentPlayer(), diceRoll, buttonVisual);
        sideColumn.displayRoll(diceRoll);
        gameController.advanceTurn();

    }

    public void moveToken(Player player, int tileNumber) {
        playerTokenLayer.moveToken(player, tileNumber);
    }

}
