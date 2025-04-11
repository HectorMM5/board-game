package boardgame.model.effectFiles;

import boardgame.controller.GameController;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;

/**
 * Effect that causes a player to skip their next turn when triggered.
 * 
 * @author Hector Mendana Morales
 */
public class SkipTurnEffect implements Effect {

    private final GameController gameController;

    public SkipTurnEffect(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public SkipTurnEffect setup(Tile tile) {
        return this;
    }

    @Override
    public void execute(Player player) {
        gameController.markPlayerToSkip(player);
    }
}
