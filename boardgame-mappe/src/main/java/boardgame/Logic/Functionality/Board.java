package boardgame.Logic.Functionality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import boardgame.Logic.Entities.BaseEffects.LadderEffect;
import boardgame.Logic.Entities.BaseEffects.PlaceholderEffect;
import boardgame.Logic.Entities.Effect;
import boardgame.Logic.Entities.Player;
import boardgame.Logic.Entities.Rules;
import boardgame.Logic.Entities.Tile;


public class Board {

    private final Random randomGenerator = new Random();
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

    public void createLadderEffect() {

        //Two tiles are randomly selected
        Tile baseTile = tiles.get(randomGenerator.nextInt(1, 100));
        Tile targetTile = tiles.get(randomGenerator.nextInt(baseTile.getNumber(), 100));

        //Selected tiles may already have an effect; if so, reroll
        while (baseTile.getEffect() != null || targetTile.getEffect() != null) {
            baseTile = tiles.get(randomGenerator.nextInt(1, 100));
            targetTile = tiles.get(randomGenerator.nextInt(baseTile.getNumber(), 100));
        }

        LadderEffect effect = new LadderEffect(baseTile, targetTile);
        baseTile.setEffect(effect);
        targetTile.setEffect(new PlaceholderEffect());
        effects.put(effects.size(), effect);

    }

    public void setupEffects() {
        ArrayList<Effect> effectOptions = new ArrayList<>();

        //As long as total effect amount is less than 20% of tiles, this runs
        while (effects.size() < tiles.size() * 0.2) {
            effectOptions.get(randomGenerator.nextInt(0, effectOptions.size()-1));

        }
    }
}
