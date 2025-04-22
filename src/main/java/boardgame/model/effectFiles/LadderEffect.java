package boardgame.model.effectFiles;


import boardgame.controller.GameController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;

public class LadderEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;
    private Board board;
    private GameController gameController;

    public LadderEffect(GameController gameController, int baseTileIndex, int targetTileIndex) {
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

    public void setBaseTileIndex(int newBaseTileIndex) {
        baseTileIndex = newBaseTileIndex;
    }

    public void setTargetTileIndex(int newTargetTileIndex) {
        baseTileIndex = newTargetTileIndex;
    }

    
}
