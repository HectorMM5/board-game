package boardgame.model.boardFiles;

import java.util.ArrayList;
import java.util.List;

public class BoardCollection {
    private List<Board> boards;

    public BoardCollection() {
        this.boards = new ArrayList<>();
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public void addBoard(Board board) {
        this.boards.add(board);
    }

    
}