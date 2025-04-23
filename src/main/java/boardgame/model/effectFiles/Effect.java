package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;
import javafx.scene.paint.Color;

public interface Effect {

    public void execute(Player player,  GameController gameController);

    Color getColor();
}
    
