package boardgame.model.boardFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import boardgame.model.effectFiles.Effect;

public class Board {
    public final Random randomGenerator;
    private final ArrayList<Tile> tiles;
    private final HashMap<Tile, Effect> effectMap;

    public Board() {
        this.randomGenerator = new Random();
        this.tiles = new ArrayList<>();
        this.effectMap = new HashMap<>();
        

        IntStream.rangeClosed(1, 100)
            .forEach(i -> tiles.add(new Tile(i)));

    }
    
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile getTileInIndex(int index) {
        return tiles.get(index);
    }

    public HashMap<Tile, Effect> getEffectMap() {
        return effectMap;
    }

    

    //public static Rules getRules() {
    //    return rules;
    //}

    public void movePlayer(Player player, int tileNumber) {
        tiles.get(player.getPosition()).setPlayer(null);

        player.setPosition(tileNumber);
        Tile targetTile = tiles.get(tileNumber);
        targetTile.setPlayer(player);

        if (!(targetTile.getEffect() == null)) {
            targetTile.getEffect().execute(player);
        }
    }

    public void moveBy(Player player, int steps) {
        int nextPosition = player.getPosition() + steps;

        movePlayer(player, nextPosition);

    }


   


}
