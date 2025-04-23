package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;
import javafx.scene.paint.Color;

/**
 * Effect that causes a player to skip their next turn when triggered.
 * 
 * @author Hector Mendana Morales
 */
public class SkipTurnEffect implements Effect {

    @Override
    public void execute(Player player, GameController gameController) {
        gameController.markPlayerToSkip(player);
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
