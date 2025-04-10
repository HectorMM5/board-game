package boardgame.visual.elements;

import java.util.ArrayList;

import boardgame.controller.DiceButtonController;
import boardgame.controller.LadderLayer;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.model.effectFiles.SnakeEffect;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ingame extends Application {
    private final Board board = new Board();
    private final ArrayList<Player> players = new ArrayList<>();
    BoardVisual boardVisual = new BoardVisual(board);
    DiceButtonController DBController = new DiceButtonController(board, boardVisual, null);

    @Override
    public void start(Stage primaryStage) { 
        
        Player testPlayer = new Player("/PlayerIcons/xd.png", "VIKTOOOR");
        DBController.setPlayerWhoseTurn(testPlayer);
        board.getTiles().get(0).setPlayer(testPlayer);

        board.getTiles().get(2).setEffect(new LadderEffect(board));

        board.getTiles().get(85).setEffect(new SnakeEffect(board));

        
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
        
        
        root.setBottom(DBController.getButtonVisual());       // The roll dice button at the bottom

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
