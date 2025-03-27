package boardgame.controller;


import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.diceFiles.Dice;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.ButtonVisual;
import javafx.scene.layout.HBox;


public class DiceButtonController {
    private Board board;
    private BoardVisual boardVisual;
    private ButtonVisual buttonVisual;
    private Player playerWhoseTurn;
    private Dice dice;

    
    public DiceButtonController(Board board, BoardVisual boardVisual, Player playerWhoseTurn) {
        this.board = board;
        this.boardVisual = boardVisual;
        this.buttonVisual = new ButtonVisual(this);
        this.playerWhoseTurn = playerWhoseTurn;
        this.dice = new Dice(1);

    }

    public void handleRollDice() {
        board.moveBy(playerWhoseTurn, 1);
        boardVisual.updateEntireBoard();
        
    }

    public void setPlayerWhoseTurn(Player player) {
        playerWhoseTurn = player;
    }

    public HBox getButtonVisual() {
        return buttonVisual.getPane();
    }
    


}
