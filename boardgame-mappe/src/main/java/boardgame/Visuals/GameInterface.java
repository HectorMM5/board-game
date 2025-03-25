package boardgame.Visuals;

import java.util.stream.IntStream;

import boardgame.Visuals.views.GameInitScreen;
import boardgame.Visuals.views.SnakesAndLaddersInitScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;

public class GameInterface {
    private Stage primaryStage;

    public GameInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {

        primaryStage.setTitle("primaryStage");


        GridPane menuPane = new GridPane();
        Button b1 = new Button("Snakes & ladders");
        Button b2 = new Button("Goose game???");
        Button b3 = new Button("Ludo!");

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                new SnakesAndLaddersInitScreen(primaryStage).start();
            }
        });


        Scene menu = new Scene(menuPane, 600, 600);
        menuPane.getChildren().addAll(b1, b2, b3);
        GridPane.setColumnIndex(b1,0);
        GridPane.setColumnIndex(b2,1);
        GridPane.setColumnIndex(b3,2);

        primaryStage.setScene(menu);
    }

    public void start() {
        primaryStage.show();
    }
}
