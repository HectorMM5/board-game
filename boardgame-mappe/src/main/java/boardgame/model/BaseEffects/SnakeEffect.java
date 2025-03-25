package boardgame.model.BaseEffects;

import java.util.Random;

import boardgame.model.Board;
import boardgame.model.Effect;
import boardgame.model.Player;
import boardgame.model.Tile;

public class SnakeEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;
    private Board board;

    public SnakeEffect(Board board, int baseTileIndex, int targetTileIndex) {
        this.board = board;
        this.baseTileIndex = baseTileIndex;
        this.targetTileIndex = targetTileIndex;
    }

    @Override
    public SnakeEffect setup(Tile tile) {

        Random randomGenerator = new Random();

        //Two tiles are randomly selected
        baseTileIndex = tile.getNumber();

        //MUST BE FIXED WHEN BASETILE RNG GIVES HIGH NUMBER. MAY NEED MULTIIPLE REROLLS TO GET WITHIN THE LOW %
        //eg. basetile = 97 has only 98, 99 and 100 as valid values, but rng runs from 1-100
        targetTileIndex = randomGenerator.nextInt(1, baseTileIndex);

        //Selected tiles may already have an effect; if so, reroll
        while (board.getTileInIndex(targetTileIndex).getEffect() != null) {
            targetTileIndex = randomGenerator.nextInt(1, baseTileIndex);
        }

        board.getTileInIndex(targetTileIndex).setEffect(new PlaceholderEffect());
        
        return this;
        
    }

    @Override
    public void execute(Player player) {
        player.setPosition(targetTileIndex);
        board.getTileInIndex(targetTileIndex).setPlayer(player);
        
    }
    
    
}
