package boardgame.Visuals;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class JavaFXTest extends Application {
    private static final int TILE_SIZE = 50; // Each tile will be 50x50 pixels
    private static final int GRID_ROWS = 5;  // The grid will have 5 rows
    private static final int GRID_COLS = 5;  // The grid will have 5 columns

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane to arrange the tiles in a grid layout
        GridPane gridPane = new GridPane();

        // Nested loops to create a grid of tiles (rows × columns)
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                Rectangle tile = createTile(row, col); // Create a tile
                gridPane.add(tile, col, row); // Add the tile to the grid at (col, row)
            }
        }

        // Create a scene with the gridPane as the root node
        Scene scene = new Scene(gridPane, GRID_COLS * TILE_SIZE, GRID_ROWS * TILE_SIZE);

        // Set up the Stage (Window)
        primaryStage.setTitle("Tile Grid"); // Window title
        primaryStage.setScene(scene); // Attach the scene to the stage
        primaryStage.show(); // Show the window
    }

    // Method to create a tile (a rectangle with styling)
    private Rectangle createTile(int row, int col) {
        Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE); // Create a square of TILE_SIZE
        tile.setFill((row + col) % 2 == 0 ? Color.LIGHTGRAY : Color.DARKGRAY); // Alternating colors for a checkered pattern
        tile.setStroke(Color.BLACK); // Add a border to the tile
        return tile;
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args); // Calls start() to set up and run the JavaFX app
    }
}
