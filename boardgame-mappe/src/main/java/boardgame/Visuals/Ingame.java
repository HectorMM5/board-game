package boardgame.Visuals;

import boardgame.Logic.Entities.Player;
import boardgame.Logic.Functionality.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Ingame {

    public void start(Stage primaryStage) {

        Board testBoard = new Board();

        Player testPlayer = new Player("PlayerIcons/xd.png", "VIKTOOOR");

        Board.getTiles().get(0).setPlayer(testPlayer);

        BoardVisual boardVisual = new BoardVisual(testBoard);
        ButtonVisual buttonVisual = new ButtonVisual();

        // Layout that will hold both board and button
        BorderPane root = new BorderPane();
        root.setCenter(boardVisual.getBoard());       // The game board in the center
        root.setBottom(buttonVisual.getPane());       // The roll dice button at the bottom

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Board Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        boardVisual.updateTileVisual(0, 0);
    }

}
