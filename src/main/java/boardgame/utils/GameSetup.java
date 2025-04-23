package boardgame.utils;

import java.util.List;

import boardgame.controller.BoardJSON;
import boardgame.controller.GameController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.visual.elements.BoardVisual;
import boardgame.visual.elements.SideColumnVisual;
import javafx.scene.layout.VBox;

public class GameSetup {

    Board board;
    BoardVisual boardVisual;
    GameController gameController;
    SideColumnVisual sideColumnVisual;

    public GameSetup(String game, int boardChoice, List<Player> players) {
        board = BoardJSON.constructSnLBoardFromJSON(boardChoice, gameController);
        boardVisual = new BoardVisual(board);
        gameController = new GameController(board, boardVisual, players);
        sideColumnVisual = new SideColumnVisual(players, gameController.getDiceAnimation());

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

    public VBox getSideColumn() {
        return sideColumnVisual.getColumn();

    }

    
}