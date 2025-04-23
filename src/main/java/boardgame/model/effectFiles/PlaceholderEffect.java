package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;
import javafx.scene.paint.Color;

public class PlaceholderEffect implements Effect {

    @Override
    public void execute(Player player, GameController gameController) {
        
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }
    
}
