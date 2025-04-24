package boardgame.controller;

import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.ButtonVisual;
import boardgame.visual.elements.DiceAnimation;
import boardgame.visual.gameLayers.PlayerTokenLayer;

public class VisualController {
    private final GameController gameController;
    private final DiceAnimation diceVisual = new DiceAnimation();
    private final ButtonVisual diceButton;
    private final BoardVisual boardVisual;
    private final PlayerTokenLayer tokenLayer;


    public VisualController(GameController gameController, BoardVisual boardVisual) {
        this.gameController = gameController;
        this.boardVisual = boardVisual;
        this.diceButton = new ButtonVisual(() -> gameController.handleRollDice());
        this.tokenLayer = new PlayerTokenLayer(gameController, this);
    }

    public void updateEntireBoard() {
        boardVisual.updateEntireBoard();
    }

    public void displayRoll(int diceRoll) {
        diceVisual.displayRoll(diceRoll);
    }

    public ButtonVisual getDiceButton() {
        return diceButton;
    }

    public DiceAnimation getDiceAnimation() {
        return diceVisual;
    }

    public PlayerTokenLayer getPlayerTokenLayer() {
        return tokenLayer;
    }

    public BoardVisual getBoardVisual() {
        return boardVisual;
    }
}
