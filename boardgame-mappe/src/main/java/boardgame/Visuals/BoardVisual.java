package boardgame.Visuals;

import java.util.ArrayList;

import boardgame.Logic.Entities.Tile;
import boardgame.Logic.Functionality.Board;
import javafx.scene.layout.GridPane;

public class BoardVisual extends GridPane{
    private static final int TILE_AMOUNT = 10; 
    private ArrayList<Tile> tileLogic;
    private TileVisual[][] tileViews;
    private Board board;

    public BoardVisual(Board board) {
        this.tileLogic = board.getTiles();
        this.tileViews = new TileVisual[TILE_AMOUNT][TILE_AMOUNT];
        this.board = board;
        
        initializeBoard();
    }

    private void initializeBoard() {
        this.setHgap(4); // horizontal gap between tiles
        this.setVgap(4); // vertical gap between tiles
        this.setStyle("-fx-background-color: lightblue;"); // background visible in gaps
        
        for (int row = 0; row < TILE_AMOUNT; row++) {

            for (int col = 0; col < TILE_AMOUNT; col++) {
                Tile tile = tileLogic.get(row * col);
                TileVisual tileVisual = new TileVisual(tile);
                tileViews[row][col] = tileVisual;

                this.add(tileVisual, col, row);
            }
        }
    }

    public void updateTileVisual(int row, int col) {
        tileViews[row][col].updateVisual();
    }

    public void updateEntireBoard() {
        for (int row = 0; row < TILE_AMOUNT; row++) {
            for (int col = 0; col < TILE_AMOUNT; col++) {
                tileViews[row][col].updateVisual();
            }
        }
    }

    public BoardVisual getBoard() {
        return this;
    }

    public Board getBoardObject() {
        return board;
    }

}
