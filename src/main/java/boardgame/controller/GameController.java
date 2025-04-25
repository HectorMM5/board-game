package boardgame.controller;

import java.util.List;

import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.model.boardFiles.Tile;
import boardgame.model.effectFiles.MovementEffect;
import boardgame.utils.LoopingIterator;
import boardgame.visual.scenes.Ingame;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class GameController {
    private final Board board;
    private final List<Tile> tiles;
    private final List<Player> players;
    private Player playerWhoseTurn;
    private final LoopingIterator<Player> playerIterator;
    private Player playerToSkip = null;
    private Ingame ingame;

    public GameController(Board board, List<Player> players) {
        this.board = board;
        this.tiles = board.getTiles();
        System.out.println("Reached GameController with player list size: " + players.size());
        this.players = players;
        this.playerIterator = new LoopingIterator<>(players);
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

                if (targetTile.getEffect() instanceof MovementEffect) {
                    ingame.moveToken(player, ((MovementEffect) targetTile.getEffect()).getTargetTileIndex());
                    
                }
            });
            pause.play();

        }

    }

    public void setIngame(Ingame ingame) {
        this.ingame = ingame;
    }



    //public void moveBy(Player player, int steps, ButtonVisual buttonVisual) {
    //    int nextPosition = player.getPosition() + steps;
//
    //    movePlayerThroughPath(player, nextPosition);
    //    PauseTransition finalPause = new PauseTransition(Duration.millis((nextPosition - player.getPosition() + 1) * 200));
    //    finalPause.setOnFinished(event -> {
    //        movePlayer(player, nextPosition);
    //        getDiceButton().setDisable(false);
    //        
    //    });
    //    finalPause.play();
//
    //}

    //public void handleRollDice(ButtonVisual buttonVisual) {
    //    int diceRoll = dice.roll();
    //    
    //    moveBy(playerWhoseTurn, diceRoll, buttonVisual);
    //    visualController.displayRoll(diceRoll);
//
    //    advanceTurn();
//
    //}

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


}
