package boardgame.visual.elements;

import java.util.ArrayList;
import java.util.List;

import boardgame.controller.GameController;
import boardgame.controller.LadderLayer;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.model.effectFiles.SkipTurnEffect;
import boardgame.model.effectFiles.SnakeEffect;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ingame extends Application {
    private final Board board = new Board();
    private final BoardVisual boardVisual = new BoardVisual(board);
    GameController gameController;

    @Override
    public void start(Stage primaryStage) { 
        
        Player testPlayer1 = new Player("/PlayerIcons/solbriller.png", "VIKTOOOR");
        Player testPlayer2 = new Player("/PlayerIcons/xd.png", "VIKTOOOR");

        List<Player> playerList = new ArrayList<>();
        playerList.add(testPlayer1);
        playerList.add(testPlayer2);

        gameController = new GameController(board, boardVisual, playerList);

        board.getTiles().get(0).addPlayer(testPlayer1);
        board.getTiles().get(0).addPlayer(testPlayer2);

        board.getTiles().get(2).setEffect(new LadderEffect(board, gameController));
        board.getTiles().get(30).setEffect(new SkipTurnEffect(gameController));
        board.getTiles().get(85).setEffect(new SnakeEffect(board, gameController));

        
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
        
        
        root.setBottom(gameController.getDiceButton());       // The roll dice button at the bottom

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Board Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        
        LadderLayer ladders = new LadderLayer(boardVisual, tilesWithLadders, tilesWithSnakes);
        centerPane.getChildren().add(ladders.getLadder());
        root.setCenter(centerPane);

        boardVisual.updateEntireBoard();



    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
