package boardgame.model.boardFiles;

import java.util.ArrayList;
import java.util.List;

import boardgame.model.effectFiles.Effect;

public class Tile {
    
    private List<Player> players;
    private Effect effect;
    private final int number;

    public Tile(int number) {
        this.number = number;
        this.effect = null;
        this.players = new ArrayList<>();

    }

    public int getNumber() {
        return number;
    }

    public Effect getEffect() {
        return effect;
    }

    public List<Player> getPlayers() {
        return players;
    }


    public void addPlayer(Player recievedPlayer) {
        players.add(recievedPlayer);

    }

    public void popPlayer() {
        players.remove(0);
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
        
    }





}