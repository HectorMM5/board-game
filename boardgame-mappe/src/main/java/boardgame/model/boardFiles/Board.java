package boardgame.model.boardFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import boardgame.model.effectFiles.Effect;
import boardgame.model.effectFiles.LadderEffect;
import boardgame.model.effectFiles.effectType;
import javafx.util.Pair;

public class Board {
    public final Random randomGenerator;
    private final ArrayList<Tile> tiles;
    private final HashMap<Tile, Effect> effectMap;
    private final int boardWidth;
    private final int boardHeight;
    private final int tileCount;

    public Board() {
        this.randomGenerator = new Random();
        this.tiles = new ArrayList<>();
        this.effectMap = new HashMap<>();

        this.boardWidth = 10;
        this.boardHeight = 9;
        this.tileCount = boardWidth * boardHeight;

        IntStream.rangeClosed(1, tileCount)
            .forEach(i -> tiles.add(new Tile(i)));

    }
    public Board(int boardWidth, int boardHeight) {
        this.randomGenerator = new Random();
        this.tiles = new ArrayList<>();
        this.effectMap = new HashMap<>();

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tileCount = boardWidth * boardHeight;

        IntStream.rangeClosed(1, tileCount)
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


    public void createMovementEffect(effectType type, int position) {
        
        int targetTileIndex;
        switch (type) {
            case LADDER:
                targetTileIndex = randomGenerator.nextInt(position, tileCount);

                //Selected tiles may already have an effect; if so, reroll
                //As long as the chosen tile has an effect, or the chosen tile is the position tile, reroll
                while ((getTileInIndex(targetTileIndex).getEffect()) != null || (targetTileIndex == position)) {
                    targetTileIndex = randomGenerator.nextInt(position, tileCount);
                }

                tiles.get(position).setEffect(new LadderEffect(this, position, targetTileIndex));

                break;
                
            case SNAKE:
                targetTileIndex = randomGenerator.nextInt(0, position);

                //Selected tiles may already have an effect; if so, reroll
                //As long as the chosen tile has an effect, or the chosen tile is the position tile, reroll
                while ((getTileInIndex(targetTileIndex).getEffect()) != null || (targetTileIndex == position)) {
                    targetTileIndex = randomGenerator.nextInt(0, position);
                }

                tiles.get(position).setEffect(new LadderEffect(this, targetTileIndex, position));
            
                break;

            default:
                throw new IllegalArgumentException("Illegal effect type.");
        }
    }
    public int getTileCount() {
        return tileCount;
    }
    public int getBoardWidth() {
        return boardWidth;
    }
    public int getBoardHeight() {
        return boardHeight;
    }

   


}
