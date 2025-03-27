package boardgame.model.effectFiles;

import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;

public interface Effect {
    public Effect setup(Tile tile);

    public void execute(Player player);
    
}