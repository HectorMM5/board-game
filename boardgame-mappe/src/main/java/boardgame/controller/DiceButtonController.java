package boardgame.controller;


import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.diceFiles.Dice;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.ButtonVisual;
import javafx.scene.layout.HBox;


public class DiceButtonController {
    private final Board board;
    private final BoardVisual boardVisual;
    private final ButtonVisual buttonVisual;
    private Player playerWhoseTurn;
    private final Dice dice;

    
    public DiceButtonController(Board board, BoardVisual boardVisual, Player playerWhoseTurn) {
        this.board = board;
        this.boardVisual = boardVisual;
        this.buttonVisual = new ButtonVisual(this);
        this.playerWhoseTurn = playerWhoseTurn;
        this.dice = new Dice(1);

    }

    public void handleRollDice() {
        board.moveBy(playerWhoseTurn, dice.roll());
        boardVisual.updateEntireBoard();
        
    }

    public void setPlayerWhoseTurn(Player player) {
        playerWhoseTurn = player;
    }

    public HBox getButtonVisual() {
        return buttonVisual.getPane();
    }
    


}
