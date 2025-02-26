package boardgame.Logic.Functionality;
import java.util.HashMap;
import java.util.stream.IntStream;

import boardgame.Logic.Entities.Rules;
import boardgame.Logic.Entities.Tile;   

public class Board {
    private final HashMap<Integer, Tile> tiles;
    private final Rules rules;

    public Board(int tileAmount, Rules rules) {
    this.tiles = new HashMap<>();
    this.rules = rules;

    IntStream.rangeClosed(1, tileAmount)
        .forEach(i -> tiles.put(i, new Tile(i)));
}

    public HashMap<Integer, Tile> getTiles() {
        return tiles;
    }

    public Rules getRules() {
        return rules;
    }
    
}