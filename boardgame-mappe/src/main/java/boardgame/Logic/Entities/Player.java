package boardgame.Logic.Entities;

public class Player {
    final String icon;
    final String name;
    int position;

    public Player(String icon, String name) {
        this.icon = icon;
        this.name = name;
        this.position = 0;
    }


    public void move(int moveAmount) {
        position += moveAmount;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public void moveToTile(Tile tile) {
        
    }

    public String getIcon() {
        return icon;
    }


}