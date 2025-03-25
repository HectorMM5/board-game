package boardgame.model;

public interface Effect {

    

    public Effect setup(Tile tile);

    public void execute(Player player);
    
}