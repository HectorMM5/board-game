package boardgame.Logic.Entities;
import boardgame.Logic.Functionality.Dice;

public class Player {
    final String icon;
    final String name;
    int position;

    Player(String icon, String name) {
        this.icon = icon;
        this.name = name;
        this.position = 0;
    }

    public void advance() {
        position += Dice.rollDice();
    }

    public void moveToTile(Tile tile) {
        
    }


}