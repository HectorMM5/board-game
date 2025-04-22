package boardgame.utils;

import java.util.List;

import boardgame.controller.BoardJSON;
import boardgame.controller.GameController;
import boardgame.model.boardFiles.Board;
import boardgame.model.boardFiles.Player;
import boardgame.visual.elements.BoardVisual;

public class GameSetup {

    Board board;
    BoardVisual boardVisual;
    GameController gameController;

    public GameSetup(String game, int boardChoice, List<Player> players) {
        board = BoardJSON.constructSnLBoardFromJSON(boardChoice, gameController);
        boardVisual = new BoardVisual(board);
        gameController = new GameController(board, boardVisual, players);

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

    
}