package boardgame.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Board {
    public final Random randomGenerator;
    private final ArrayList<Tile> tiles;
    private final ArrayList<Effect> effects;
    //private static final Rules rules = new Rules(effects, 100);

    public Board() {
        this.randomGenerator = new Random();;
        this.tiles = new ArrayList<>();
        this.effects = new ArrayList<>();

        IntStream.rangeClosed(1, 100)
            .forEach(i -> tiles.add(new Tile(i)));

    }
    
    public ArrayList<Tile> getTiles() {
        return tiles;
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

    public Tile getTileInIndex(int index) {
        return tiles.get(index);
    }


    public void createMovementEffect(effectType type) {
        int targetTileIndex = randomGenerator.nextInt(0, 100);
        //Selected tiles may already have an effect; if so, reroll
        while (getTileInIndex(targetTileIndex).getEffect() != null) {
            targetTileIndex = randomGenerator.nextInt(0, 100);
        }

        switch (type) {
            
            case LADDER:
                createMovementEffect(effectType.LADDER);
                
            case SNAKE:
                createMovementEffect(effectType.SNAKE);

            default:
                throw new IllegalArgumentException("Illegal effect type.");
        }
    }

   


}
