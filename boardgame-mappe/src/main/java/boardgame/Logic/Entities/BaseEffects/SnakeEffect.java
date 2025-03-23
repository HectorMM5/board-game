package boardgame.Logic.Entities.BaseEffects;

import java.util.Random;

import boardgame.Logic.Entities.Effect;
import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Tile;
import boardgame.Logic.Functionality.Board;

public class SnakeEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;

    public SnakeEffect(int baseTileIndex, int targetTileIndex) {
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
        targetTileIndex = Board.randomGenerator.nextInt(1, baseTileIndex);

        //Selected tiles may already have an effect; if so, reroll
        while (Board.getTileInIndex(targetTileIndex).getEffect() != null) {
            targetTileIndex = Board.randomGenerator.nextInt(1, baseTileIndex);
        }

        Board.getTileInIndex(targetTileIndex).setEffect(new PlaceholderEffect());
        
        return this;
        
    }

    @Override
    public void execute(Player player) {
        player.setPosition(targetTileIndex);
        Board.getTileInIndex(targetTileIndex).setPlayer(player);
        
    }
    
}
