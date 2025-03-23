package boardgame.Logic.Entities.BaseEffects;

import boardgame.Logic.Entities.Effect;
import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Tile;

public class PlaceholderEffect implements Effect {
    
    @Override
    public Effect setup(Tile tile) {
        return this;
        
    }

    @Override
    public void execute(Player player) {
        
    }
    
}
