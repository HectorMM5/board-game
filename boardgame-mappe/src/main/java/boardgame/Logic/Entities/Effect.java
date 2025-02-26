package boardgame.Logic.Entities;


public interface Effect {

    

    public void setup();

    public void execute(Player player, Tile tile);
    
}