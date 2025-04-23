package boardgame.controller;

import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.ButtonVisual;
import boardgame.visual.elements.DiceAnimation;
import javafx.scene.layout.HBox;

public class VisualController {
    private final GameController gameController;
    private final DiceAnimation diceVisual = new DiceAnimation();
    private final ButtonVisual diceButton;
    private final BoardVisual boardVisual;


    public VisualController(GameController gameController, BoardVisual boardVisual) {
        this.gameController = gameController;
        this.boardVisual = boardVisual;
        this.diceButton = new ButtonVisual(() -> gameController.handleRollDice());
    }

    public void updateEntireBoard() {
        boardVisual.updateEntireBoard();
    }

    public void displayRoll(int diceRoll) {
        diceVisual.displayRoll(diceRoll);
    }

    public HBox getDiceButton() {
        return diceButton;
    }

    public DiceAnimation getDiceAnimation() {
        return diceVisual;
    }
}
