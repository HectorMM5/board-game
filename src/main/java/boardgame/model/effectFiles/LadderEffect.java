package boardgame.model.effectFiles;


import boardgame.controller.GameController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;

public class LadderEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;
    private Board board;
    private GameController gameController;

    public LadderEffect(Board board, GameController gameController) {
        this.board = board;
        this.gameController = gameController;
        this.baseTileIndex = -1;
        this.targetTileIndex = -1;

    }

    @Override
    public LadderEffect setup(Tile tile) {
        //Target tile is randomly selected
        baseTileIndex = tile.getNumber();
        targetTileIndex = board.randomGenerator.nextInt(baseTileIndex, 90);

        //Selected tiles may already have an effect; if so, reroll
        while (board.getTileInIndex(targetTileIndex).getEffect() != null) {
            targetTileIndex = board.randomGenerator.nextInt(baseTileIndex, 90);
        }

        board.getTileInIndex(targetTileIndex - 1).setEffect(new PlaceholderEffect());
        
        return this;
        
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
