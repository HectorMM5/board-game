package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;

public class SnakeEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;
    private GameController gameController;

    public SnakeEffect(GameController gameController, int baseTileIndex, int targetTileIndex) {
        this.gameController = gameController;
        this.baseTileIndex = baseTileIndex;
        this.targetTileIndex = targetTileIndex;

    }

    @Override
    public void execute(Player player) {
        gameController.movePlayer(player, targetTileIndex);
    }

    public int getBaseTileIndex() {
        return baseTileIndex;
    }

    public int getTargetTileIndex() {
        return targetTileIndex;
    }
    
    
}
