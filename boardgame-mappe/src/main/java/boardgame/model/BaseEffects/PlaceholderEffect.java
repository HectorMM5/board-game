package boardgame.model.BaseEffects;

import boardgame.model.Effect;
import boardgame.model.Player;
import boardgame.model.Tile;

public class PlaceholderEffect implements Effect {
    
    @Override
    public Effect setup(Tile tile) {
        return this;
        
    }

    @Override
    public void execute(Player player) {
        
    }
    
}
