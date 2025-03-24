package boardgame.Logic.Functionality;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import boardgame.Logic.Entities.Effect;
import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Tile;


public class Board {

    public static final Random randomGenerator = new Random();
    private static final ArrayList<Tile> tiles = new ArrayList<>();
    private static final ArrayList<Effect> effects = new ArrayList<>();
    //private static final Rules rules = new Rules(effects, 100);
    

    static {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> tiles.add(new Tile(i)));
    }

    public static ArrayList<Tile> getTiles() {
        return tiles;
    }

    //public static Rules getRules() {
    //    return rules;
    //}

    public static void movePlayer(Player player, Tile tile) {
        player.setPosition(tile.getNumber());

        if (!(tile.getEffect() == null)) {
            tile.getEffect().execute(player);
        }
    }

    public static Tile getTileInIndex(int index) {
        return tiles.get(index);
    }


    public void createMovementEffect(effectType type) {
        int targetTileIndex = randomGenerator.nextInt(0, 100);
        //Selected tiles may already have an effect; if so, reroll
        while (Board.getTileInIndex(targetTileIndex).getEffect() != null) {
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
