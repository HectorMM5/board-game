package boardgame.model.effectFiles;


import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;

public class LadderEffect implements Effect {

    private final int baseTileIndex;
    private final int targetTileIndex;

    public LadderEffect(int baseTileIndex, int targetTileIndex) {
        this.baseTileIndex = baseTileIndex;
        this.targetTileIndex = targetTileIndex;

    }

    @Override
    public void execute(Player player, GameController gameController) {
        gameController.movePlayer(player, targetTileIndex);
    }

    public int getBaseTileIndex() {
        return baseTileIndex;
    }

    public int getTargetTileIndex() {
        return targetTileIndex;
    }


    
}
