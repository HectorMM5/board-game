package boardgame.visual.elements;

import java.util.ArrayList;

import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Tile;
import javafx.scene.layout.GridPane;

public class BoardVisual extends GridPane {
    private final Board board;
    private final ArrayList<Tile> tileLogic;
    private final ArrayList<TileVisual> tileViews;

    public BoardVisual(Board board) {
        this.board = board;
        this.tileLogic = board.getTiles();
        this.tileViews = new ArrayList<>();
        
        initializeBoard();
    }

    private void initializeBoard() {
        this.setHgap(4); // horizontal gap between tiles
        this.setVgap(4); // vertical gap between tiles
        this.setStyle("-fx-background-color: lightblue;"); // background visible in gaps

        Boolean movesRight = false;

        for (int i = 0; i < 90; i++) {

            Tile tile = tileLogic.get(i);
            TileVisual tileVisual = new TileVisual(tile);

            tileViews.add(tileVisual);

            if ((i % 10) == 0) { movesRight = !movesRight; }           

            if (movesRight) {
                this.add(tileVisual, i % 10, i / 10);

            } else {
                this.add(tileVisual, 10 - ((i % 10) + 1), i / 10);

            }
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

    public void displayEffects() {
        
    }


}
