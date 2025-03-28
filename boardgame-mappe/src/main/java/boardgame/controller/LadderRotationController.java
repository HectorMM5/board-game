package boardgame.controller;

import java.util.Iterator;

import boardgame.model.effectFiles.LadderEffect;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.LadderVisual;
import boardgame.visual.elements.TileVisual;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.util.Pair;

public class LadderRotationController {

    private LadderVisual ladderVisual;

    public LadderRotationController(LadderEffect ladder, BoardVisual boardVisual) {

        Integer baseX = null;
        Integer baseY = null;

        Integer targetX = null;
        Integer targetY = null;

        Iterator<Node> tileIterator = boardVisual.getChildren().iterator();

        while (((baseX == null) || (targetX == null)) && (tileIterator.hasNext())) {
            Node tileNode = tileIterator.next();

            if (tileNode instanceof TileVisual tileVisual) {
                if (tileVisual.getTile().getNumber() == ladder.getBaseTileIndex()) {
                    baseX = BoardVisual.getRowIndex(tileVisual);
                    baseY = BoardVisual.getColumnIndex(tileVisual);

                } else if (tileVisual.getTile().getNumber() == ladder.getTargetTileIndex()) {
                    targetX = BoardVisual.getRowIndex(tileVisual);
                    targetY = BoardVisual.getColumnIndex(tileVisual);
                    
                }
            }
        }

        Pair<Integer, Integer> directionVector = new Pair<>(targetX - baseX, targetY - baseY);

        //x / sqrt(x^2 + y^2)
        double cosValue = directionVector.getKey() / Math.sqrt(Math.pow(directionVector.getKey(), 2) + Math.pow(directionVector.getValue(), 2));

        double innerDegree = Math.acos(cosValue);
        double degree = Math.toDegrees(innerDegree);

        ladderVisual = new LadderVisual(ladder);
        ladderVisual.setRotate(degree);
        

    }

    public Group getLadder() {
        return ladderVisual.getLadder();
    }

}
