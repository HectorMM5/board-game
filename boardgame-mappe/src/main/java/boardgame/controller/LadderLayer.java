package boardgame.controller;

import java.util.Iterator;
import java.util.List;

import boardgame.model.boardFiles.Tile;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.model.effectFiles.SnakeEffect;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.LadderVisual;
import boardgame.visual.elements.SnakeVisual;
import boardgame.visual.elements.TileVisual;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Rotate;

public class LadderLayer {

    private BoardVisual boardVisual;

    Pane ladderLayer = new Pane();

    public LadderLayer(BoardVisual boardVisual, List<Tile> tilesWithLadders, List<Tile> tilesWithSnakes) {
        this.boardVisual = boardVisual;

        ladderLayer.setPrefSize(536, 482); // same as board
        ladderLayer.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        ladderLayer.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        //ladderLayer.setStyle("-fx-border-color: yellow; -fx-border-width: 2;");

        for (Tile tile : tilesWithLadders) {
            renderLadder((LadderEffect) tile.getEffect());
        }

        for (Tile tile : tilesWithSnakes) {
            renderSnake((SnakeEffect) tile.getEffect());
        }



    }

    private void renderLadder(LadderEffect ladder) {

        LadderVisual ladderVisual;

        Integer baseX = null;
        Integer baseY = null;

        Integer targetX = null;
        Integer targetY = null;

        Iterator<Node> tileIterator = boardVisual.getChildren().iterator();

        final int TILE_SIZE = 50;
        final int GAP = 4;
        final int spacing = TILE_SIZE + GAP;

        while (((baseX == null) || (targetX == null)) && (tileIterator.hasNext())) {
            Node tileNode = tileIterator.next();

            if (tileNode instanceof TileVisual tileVisual) {
                if (tileVisual.getTile().getNumber() == ladder.getBaseTileIndex()) {

                    baseX = BoardVisual.getColumnIndex(tileVisual);
                    baseY = BoardVisual.getRowIndex(tileVisual);

                } else if (tileVisual.getTile().getNumber() == ladder.getTargetTileIndex()) {

                    targetX = BoardVisual.getColumnIndex(tileVisual);
                    targetY = BoardVisual.getRowIndex(tileVisual);

                }
            }
        }

        int dx = targetX - baseX;
        int dy = targetY - baseY;

        double hypotenuse = Math.sqrt((dx * dx) + (dy * dy));

        ladderVisual = new LadderVisual(hypotenuse * spacing - TILE_SIZE / 2);
        ladderVisual.setLayoutX(baseX * spacing);
        ladderVisual.setLayoutY(baseY * spacing + TILE_SIZE / 2.0);

        double angle = Math.toDegrees(Math.atan2(dx, dy));

        System.out.println(angle);

        ladderVisual.getTransforms().add(new Rotate(-angle, 25, 0));

        ladderLayer.getChildren().add(ladderVisual);

    }


    private void renderSnake(SnakeEffect snake) {

        SnakeVisual snakeVisual;

        Integer baseX = null;
        Integer baseY = null;

        Integer targetX = null;
        Integer targetY = null;

        Iterator<Node> tileIterator = boardVisual.getChildren().iterator();

        final int TILE_SIZE = 50;
        final int GAP = 4;
        final int spacing = TILE_SIZE + GAP;

        while (((baseX == null) || (targetX == null)) && (tileIterator.hasNext())) {
            Node tileNode = tileIterator.next();

            if (tileNode instanceof TileVisual tileVisual) {
                if (tileVisual.getTile().getNumber() == snake.getBaseTileIndex()) {

                    baseX = BoardVisual.getColumnIndex(tileVisual);
                    baseY = BoardVisual.getRowIndex(tileVisual);

                } else if (tileVisual.getTile().getNumber() == snake.getTargetTileIndex()) {

                    targetX = BoardVisual.getColumnIndex(tileVisual);
                    targetY = BoardVisual.getRowIndex(tileVisual);

                }
            }
        }

        int dx = targetX - baseX;
        int dy = targetY - baseY;

        double hypotenuse = Math.sqrt((dx * dx) + (dy * dy));

        snakeVisual = new SnakeVisual(hypotenuse * spacing - TILE_SIZE / 2);
        snakeVisual.setLayoutX(baseX * spacing);
        snakeVisual.setLayoutY(baseY * spacing + TILE_SIZE / 2.0);

        double angle = Math.toDegrees(Math.atan2(dx, dy));

        snakeVisual.getTransforms().add(new Rotate(-angle, 25, 0));

        ladderLayer.getChildren().add(snakeVisual);

    }

    public Pane getLadder() {
        return ladderLayer;
    }

}
