package boardgame.model.effectFiles;

import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;

public class PlaceholderEffect implements Effect {
    
    @Override
    public Effect setup(Tile tile) {
        return this;
        
    }

    @Override
    public void execute(Player player) {
        
    }
    
}
