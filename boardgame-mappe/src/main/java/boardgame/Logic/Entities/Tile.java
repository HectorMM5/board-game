package boardgame.Logic.Entities;

public class Tile {
    private Player player;
    private Effect effect;
    private int number;

    public Tile(int number) {
        this.number = number;
        this.effect = null;
        this.player = null;
    }

    public int getNumber() {
        return number;
    }

    public Effect getEffect() {
        return effect;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player recievedPlayer) {
        this.player = recievedPlayer;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
   
}