package boardgame.utils;

import java.util.List;

import boardgame.controller.BoardJSON;
import boardgame.controller.GameController;
import boardgame.controller.VisualController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.SideColumnVisual;
import javafx.scene.layout.VBox;

public class GameSetup {

    private Board board;
    private BoardVisual boardVisual;
    private GameController gameController;
    private SideColumnVisual sideColumnVisual;
    private VisualController visualController;

    public GameSetup(String game, int boardChoice, List<Player> players) {
        board = BoardJSON.constructSnLBoardFromJSON(boardChoice, gameController); // OK if gameController is unused here
        boardVisual = new BoardVisual(board);

        gameController = new GameController(board, players); // step 1
        visualController = new VisualController(gameController, boardVisual);    // step 2
        gameController.setVisualController(visualController);                    // step 3

        sideColumnVisual = new SideColumnVisual(players, visualController.getDiceAnimation());

    }

    public Board getBoard() {
        return board;
    }

    public BoardVisual getBoardVisual() {
        return boardVisual;
    }

    public GameController getGameController() {
        return gameController;
    }

    public VisualController getVisualController() {
        return visualController;
    }

    public VBox getSideColumn() {
        return sideColumnVisual.getColumn();

    }

}
