package boardgame.controller;

import java.util.Iterator;
import java.util.List;

import boardgame.model.boardFiles.Tile;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.LadderVisual;
import boardgame.visual.elements.TileVisual;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Rotate;

public class LadderRotationController {

    private LadderVisual ladderVisual;
    private BoardVisual boardVisual;

    Pane ladderLayer = new Pane();

    public LadderRotationController(BoardVisual boardVisual, List<Tile> tilesWithLadders) {
        this.boardVisual = boardVisual;

        ladderLayer.setPickOnBounds(false);

        ladderLayer.setPrefSize(536, 482); // same as board
        ladderLayer.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        ladderLayer.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        for (Tile tile : tilesWithLadders) {
            renderLadder((LadderEffect) tile.getEffect());
        }

    }

    public void renderLadder(LadderEffect ladder) {

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

        double hypotenuse = Math.sqrt(dx * dx + dy * dy);

        ladderVisual = new LadderVisual(hypotenuse * spacing - TILE_SIZE / 2);
        ladderVisual.setLayoutX(baseX * spacing - TILE_SIZE / 2);
        ladderVisual.setLayoutY(baseY * spacing - TILE_SIZE / 2);

        if (dy == 0) {
            ladderVisual.getTransforms().add(new Rotate(
                    270,
                    25, // pivot X (relative to ladder's local space)
                    0
            ));

        } else if (dx != 0) {
            double degree = Math.toDegrees(Math.atan(dy / dx));

            System.out.println(degree);

            if (dx > 0) {

                // Set pivot for rotation to the bottom of the ladder
                ladderVisual.getTransforms().add(new Rotate(
                        270 + degree,
                        25, // pivot X (relative to ladder's local space)
                        0
                ));

                System.err.println("ENTER DX > 0");

            } else {
                // Set pivot for rotation to the bottom of the ladder
                ladderVisual.getTransforms().add(new Rotate(
                        degree,
                        25, // pivot X (relative to ladder's local space)
                        0
                ));

                System.out.println(degree);

                System.err.println("ENTER DX < 0");

            }

        }

        ladderLayer.getChildren().add(ladderVisual);

    }

    public Pane getLadder() {
        return ladderLayer;
    }

}
