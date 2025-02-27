package boardgame.Logic.Functionality;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import boardgame.Logic.Entities.BaseEffects.LadderEffect;
import boardgame.Logic.Entities.Effect;
import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Rules;  
import boardgame.Logic.Entities.Tile;


public class Board {
    private Random targetGenerator = new Random();
    private final HashMap<Integer, Tile> tiles;
    private final HashMap<Integer, Effect> effects;
    private final Rules rules;

    public Board(int tileAmount, Rules rules) {
        this.tiles = new HashMap<>();
        this.effects = new HashMap<>();
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

    public void movePlayer(Player player, Tile tile) {
        player.setPosition(tile.getNumber());
        tile.getEffect().execute(player);

    } 

    public void createLadderEffects() {
        //As long as total effect amount is less than 10% of tiles, this runs
        while (effects.size() < tiles.size() * 0.1) {

            //Two tiles are randomly selected
            Tile baseTile = tiles.get(targetGenerator.nextInt(1, 100));
            Tile targetTile = tiles.get(targetGenerator.nextInt(baseTile.getNumber(), 100));

            //Selected tiles may already have an effect; if so, reroll
            while (baseTile.getEffect() != null || targetTile != null)  {
                baseTile = tiles.get(targetGenerator.nextInt(1, 100));
                targetTile = tiles.get(targetGenerator.nextInt(baseTile.getNumber(), 100));
                
            }

            LadderEffect effect = new LadderEffect(baseTile, targetTile);

            baseTile.setEffect(effect);
            
            effects.put(effects.size(), effect);


        }

    }
    
}