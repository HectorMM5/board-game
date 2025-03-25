package boardgame.visual.elements;

import java.util.ArrayList;

import boardgame.model.Board;
import boardgame.model.Tile;
import javafx.scene.layout.GridPane;

public class BoardVisual extends GridPane {
    private static final int TILE_AMOUNT = 10; 
    private final ArrayList<Tile> tileLogic;
    private final ArrayList<TileVisual> tileViews;

    public BoardVisual(Board board) {
        this.tileLogic = board.getTiles();
        this.tileViews = new ArrayList<>();
        
        initializeBoard();
    }

    private void initializeBoard() {
        this.setHgap(4); // horizontal gap between tiles
        this.setVgap(4); // vertical gap between tiles
        this.setStyle("-fx-background-color: lightblue;"); // background visible in gaps

        for (int i = 0; i < TILE_AMOUNT*TILE_AMOUNT; i++) {

            Tile tile = tileLogic.get(i);
            TileVisual tileVisual = new TileVisual(tile);

            tileViews.add(tileVisual);

            this.add(tileVisual, i % 10, i / 10);
            
        }
    }

    public void updateEntireBoard() {
        for (TileVisual tv : tileViews) {
            tv.updateVisual();
        }
        
    }

    public BoardVisual getBoardVisual() {
        return this;
    }

}
