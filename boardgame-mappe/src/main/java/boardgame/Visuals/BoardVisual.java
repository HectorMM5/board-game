package boardgame.Visuals;

import java.util.stream.IntStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BoardVisual extends Application {
    private static final int TILE_SIZE = 50; 
    private static final int gridSize = 10;


    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        
        IntStream.range(0, gridSize)
        .forEach(row -> {  
            IntStream.range(0, gridSize)
                .forEach(col -> {  
                    Rectangle tile = createTile();
                    gridPane.add(tile, col, row); 
                });
        });


        Scene scene = new Scene(gridPane, gridSize * TILE_SIZE, gridSize * TILE_SIZE);

        primaryStage.setTitle("Tile Grid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Rectangle createTile() {
        Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
        tile.setFill(Color.DARKGRAY); 
        tile.setStroke(Color.BLACK); 
        return tile;
    }


    public static void main(String[] args) {
        launch(args); 
    }
}
