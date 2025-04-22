package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;

/**
 * Effect that causes a player to skip their next turn when triggered.
 * 
 * @author Hector Mendana Morales
 */
public class BackToStartEffect implements Effect {

    private final GameController gameController;

    public BackToStartEffect(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute(Player player) {
        gameController.movePlayer(player, 1);
    }
}
