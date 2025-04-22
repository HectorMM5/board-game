package boardgame.controller;
import java.util.List;

import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;
import boardgame.model.diceFiles.Dice;
import boardgame.utils.LoopingIterator;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.ButtonVisual;
import javafx.scene.layout.HBox;

public class GameController {

    private final Board board;
    private final List<Tile> tiles;
    private final BoardVisual boardVisual;
    private final List<Player> players;
    private Dice dice;
    private Player playerWhoseTurn;
    private final LoopingIterator<Player> playerIterator;
    private final ButtonVisual diceButton = new ButtonVisual(() -> handleRollDice());
    private Player playerToSkip = null;

    public GameController(Board board, BoardVisual boardVisual, List<Player> players) {
        this.board = board;
        this.tiles = board.getTiles();
        this.boardVisual = boardVisual;
        this.players = players;
        this.playerIterator = new LoopingIterator<>(players);
        this.dice = new Dice(1);
        this.playerWhoseTurn = playerIterator.next();

    }

    public void start() {    
        for (Player player : players) {
            board.getTiles().get(0).addPlayer(player);
        }

    }

    public void movePlayer(Player player, int tileNumber) {

        tiles.get(player.getPosition() - 1).popPlayer();

        player.setPosition(tileNumber);
        Tile targetTile = tiles.get(tileNumber - 1);
        targetTile.addPlayer(player);

        if (!(targetTile.getEffect() == null)) {
            targetTile.getEffect().execute(player, this);
        }

        boardVisual.updateEntireBoard();
    }

    public void moveBy(Player player, int steps) {
        int nextPosition = player.getPosition() + steps;
        movePlayer(player, nextPosition);

    }

    public void handleRollDice() {
        moveBy(playerWhoseTurn, dice.roll()); 
        advanceTurn();
        
    }

    public void markPlayerToSkip(Player player) {
        playerToSkip = player;
    }

    public Player getCurrentPlayer() {
        return playerWhoseTurn;
    }
    
    public void advanceTurn() {
        playerWhoseTurn = playerIterator.next();

        if (playerToSkip != null && playerToSkip.equals(playerWhoseTurn)) {
            playerToSkip = null;
            playerWhoseTurn = playerIterator.next();

        }
    }

    public HBox getDiceButton() {
        return diceButton.getPane();
    }
       
    
}
