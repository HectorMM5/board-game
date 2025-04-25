package boardgame.utils;

import java.util.List;

import boardgame.controller.BoardJSON;
import boardgame.controller.GameController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.visual.scenes.Ingame;
import javafx.stage.Stage;

public class GameSetup {

    private final Board board;
    private final List<Player> players;
    private final GameController gameController;
    private final Ingame ingame;

    public GameSetup(String game, int boardChoice, List<Player> players) {
        System.out.println("Reached GameSetup with player list size: " + players.size());
        this.board = BoardJSON.constructSnLBoardFromJSON(boardChoice);
        this.players = players;
        this.gameController = new GameController(board, players);
        this.ingame = new Ingame(this);
        


    }

    public Board getBoard() {
        return board;
    }

    public GameController getGameController() {
        return gameController;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void start(Stage primaryStage) {
        ingame.createGameScene(primaryStage);

    }
}
