package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;

public interface Effect {

    public void execute(Player player,  GameController gameController);
    
}