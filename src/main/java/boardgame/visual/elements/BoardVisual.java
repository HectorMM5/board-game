package boardgame.visual.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Tile;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class BoardVisual extends GridPane {
    private final Board board;
    private final ArrayList<Tile> tileLogic;
    private final ArrayList<TileVisual> tileViews;

    Map<Integer, Integer> rows = new HashMap<>();
    Map<Integer, Integer> cols = new HashMap<>();


    public BoardVisual(Board board) {
        this.board = board;
        this.tileLogic = board.getTiles();
        this.tileViews = new ArrayList<>();

        this.setPrefSize(536, 482); // same as board
        this.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        initializeBoard();
    }

    private void initializeBoard() {
        this.setHgap(4); // horizontal gap between tiles
        this.setVgap(4); // vertical gap between tiles
        this.setStyle("-fx-background-color: lightblue;"); // background visible in gaps

        Boolean movesRight = false;

        for (int i = 0; i < board.getTileCount(); i++) {
            Tile tile = tileLogic.get(i);
            TileVisual tileVisual = new TileVisual(tile);
            tileViews.add(tileVisual);
        
            if ((i % board.getBoardWidth()) == 0) {
                movesRight = !movesRight;
            }
        
            int row = i / board.getBoardWidth();
            int col = movesRight
                ? i % board.getBoardWidth()
                : board.getBoardWidth() - ((i % board.getBoardWidth()) + 1);

            this.add(tileVisual, col, row);
            cols.put(tile.getNumber(), col);
            rows.put(tile.getNumber(), row);
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

    public Map<Integer, Integer> getRows() {
        return rows;
    }

    public Map<Integer, Integer> getCols() {
        return cols;
    }


    public void displayEffects() {
        
    }


}
