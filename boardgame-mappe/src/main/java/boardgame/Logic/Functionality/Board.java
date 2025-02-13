package boardgame.Logic.Functionality;
import java.util.ArrayList;
import java.util.HashMap;

import boardgame.Logic.Entities.Tile;   

public class Board {
    private final HashMap<Integer, Tile> tiles;

    public Board(ArrayList<Tile> tileList) {
        this.tiles = new HashMap<>();

        for (Tile tile : tileList) {
            tiles.put(tile.getNumber(), tile);
        }
    }

    // Optional: Getter to access tiles
    public HashMap<Integer, Tile> getTiles() {
        return tiles;
    }

    
}