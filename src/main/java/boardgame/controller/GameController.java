package boardgame.controller;

import java.util.List;
import java.util.stream.IntStream;

import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;
import boardgame.model.diceFiles.Dice;
import boardgame.model.effectFiles.BackToStartEffect;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.model.effectFiles.MovementEffect;
import boardgame.model.effectFiles.SnakeEffect;
import boardgame.utils.LoopingIterator;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class GameController {

    private VisualController visualController;

    private final Board board;
    private final List<Tile> tiles;
    private final List<Player> players;
    private final Dice dice;
    private Player playerWhoseTurn;
    private final LoopingIterator<Player> playerIterator;
    ;
    private Player playerToSkip = null;

    public GameController(Board board, List<Player> players) {
        this.board = board;
        this.tiles = board.getTiles();
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
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                targetTile.getEffect().execute(player, this);

                if (targetTile.getEffect() instanceof LadderEffect || targetTile.getEffect() instanceof SnakeEffect || targetTile.getEffect() instanceof BackToStartEffect) {
                    visualController.getPlayerTokenLayer().moveToken(player, ((MovementEffect) targetTile.getEffect()).getTargetTileIndex());
                }
            });

            pause.play();

        }

        visualController.updateEntireBoard();
    }

    public void movePlayerThroughPath(Player player, int endTile) {
        IntStream.rangeClosed(0, endTile - player.getPosition() - 1).forEach(i -> {
            PauseTransition pause = new PauseTransition(Duration.millis(i * 200));
            pause.setOnFinished(event -> {
                visualController.getPlayerTokenLayer().moveStep(player, player.getPosition() + i);
            });
            pause.play();
        });

        PauseTransition finalPause = new PauseTransition(Duration.millis((endTile - player.getPosition() + 1) * 200));
        finalPause.setOnFinished(event -> {
            movePlayer(player, endTile);
            visualController.getDiceButton().setDisable(false);
            
        });
        finalPause.play();
        
    }

    public void moveBy(Player player, int steps) {
        int nextPosition = player.getPosition() + steps;
        movePlayerThroughPath(player, nextPosition);

    }

    public void handleRollDice() {
        int diceRoll = dice.roll();
        moveBy(playerWhoseTurn, diceRoll);
        visualController.displayRoll(diceRoll);

        advanceTurn();

    }

    public void markPlayerToSkip(Player player) {
        playerToSkip = player;
    }

    public Player getCurrentPlayer() {
        return playerWhoseTurn;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public void advanceTurn() {
        playerWhoseTurn = playerIterator.next();

        if (playerToSkip != null && playerToSkip.equals(playerWhoseTurn)) {
            playerToSkip = null;
            playerWhoseTurn = playerIterator.next();
        }
    }

    public void setVisualController(VisualController takenVisualController) {
        visualController = takenVisualController;
    }

}
