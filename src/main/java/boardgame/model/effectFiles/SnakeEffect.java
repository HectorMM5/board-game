package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;

public class SnakeEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;
    private Board board;
    private GameController gameController;

    public SnakeEffect(Board board, GameController gameController) {
        this.board = board;
        this.gameController = gameController;
        this.baseTileIndex = -1;
        this.targetTileIndex = -1;

    }

    @Override
    public SnakeEffect setup(Tile tile) {
        //Two tiles are randomly selected
        baseTileIndex = tile.getNumber();

        //MUST BE FIXED WHEN BASETILE RNG GIVES HIGH NUMBER. MAY NEED MULTIIPLE REROLLS TO GET WITHIN THE LOW %
        //eg. basetile = 97 has only 98, 99 and 100 as valid values, but rng runs from 1-100
        targetTileIndex = board.randomGenerator.nextInt(1, baseTileIndex);

        //Selected tiles may already have an effect; if so, reroll
        while (board.getTileInIndex(targetTileIndex).getEffect() != null) {
            targetTileIndex = board.randomGenerator.nextInt(1, baseTileIndex);
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
