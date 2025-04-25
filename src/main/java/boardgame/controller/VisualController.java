package boardgame.controller;

import boardgame.visual.elements.BoardVisual;

public class VisualController {
    private final BoardVisual boardVisual;


    public VisualController(BoardVisual boardVisual) {
        this.boardVisual = boardVisual;
    }

    public void updateEntireBoard() {
        boardVisual.updateEntireBoard();
    }

    public BoardVisual getBoardVisual() {
        return boardVisual;
    }
}
