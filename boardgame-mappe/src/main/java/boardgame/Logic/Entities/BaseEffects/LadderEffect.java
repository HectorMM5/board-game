package boardgame.Logic.Entities.BaseEffects;


import boardgame.Logic.Entities.Effect;
import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Tile;

public class LadderEffect implements Effect {

    private Tile baseTile;
    private Tile targetTile;

    public LadderEffect(Tile baseTile, Tile targetTile) {
        this.baseTile = baseTile;
        this.targetTile = targetTile;
    }

    @Override
    public void setup() {
        


        
    }

    @Override
    public void execute(Player player) {
        if (targetTile.getNumber() <= baseTile.getNumber()) {
            throw new IllegalArgumentException("Target tile is behind or equal to the base tile.");
        }

        
        
    }
    
}
