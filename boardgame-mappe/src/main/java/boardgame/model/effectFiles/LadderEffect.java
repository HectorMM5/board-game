package boardgame.model.effectFiles;


import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;

public class LadderEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;
    private Board board;

    public LadderEffect(Board board) {
        this.baseTileIndex = -1;
        this.targetTileIndex = -1;
        this.board = board;
    }

    @Override
    public LadderEffect setup(Tile tile) {
        //Two tiles are randomly selected
        baseTileIndex = tile.getNumber();

        //MUST BE FIXED WHEN BASETILE RNG GIVES HIGH NUMBER. MAY NEED MULTIIPLE REROLLS TO GET WITHIN THE LOW %
        //eg. basetile = 97 has only 98, 99 and 100 as valid values, but rng runs from 1-100
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
        board.movePlayer(player, targetTileIndex);
    }

    public int getBaseTileIndex() {
        return baseTileIndex;
    }

    public int getTargetTileIndex() {
        return targetTileIndex;
    }

    
}
