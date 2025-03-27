package boardgame.visual.elements;

import java.util.ArrayList;

import boardgame.controller.DiceButtonController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.effectFiles.effectType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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

        board.createMovementEffect(effectType.LADDER, 3);
        
        // Layout that will hold both board and button
        BorderPane root = new BorderPane();
        root.setCenter(boardVisual);       // The game board in the center
        root.setBottom(DBController.getButtonVisual());       // The roll dice button at the bottom
        root.getChildren().addAll(new LadderVisual().getLadder());

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Board Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        boardVisual.updateEntireBoard();

    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
