package boardgame.Logic.Entities.BaseEffects;


import java.util.Random;

import boardgame.Logic.Entities.Effect;
import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Tile;
import boardgame.Logic.Functionality.Board;

public class LadderEffect implements Effect {

    private int baseTileIndex;
    private int targetTileIndex;

    public LadderEffect(int baseTileIndex, int targetTileIndex) {
        this.baseTileIndex = baseTileIndex;
        this.targetTileIndex = targetTileIndex;
    }

    @Override
    public LadderEffect setup(Tile tile) {
        //Two tiles are randomly selected
        baseTileIndex = tile.getNumber();

        //MUST BE FIXED WHEN BASETILE RNG GIVES HIGH NUMBER. MAY NEED MULTIIPLE REROLLS TO GET WITHIN THE LOW %
        //eg. basetile = 97 has only 98, 99 and 100 as valid values, but rng runs from 1-100
        targetTileIndex = Board.randomGenerator.nextInt(baseTileIndex, 100);

        //Selected tiles may already have an effect; if so, reroll
        while (Board.getTileInIndex(targetTileIndex).getEffect() != null) {
            targetTileIndex = Board.randomGenerator.nextInt(baseTileIndex, 100);
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
