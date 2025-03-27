package boardgame.model;

public class Player {
    private String icon;
    final String name;
    int position;

    public Player(String icon, String name) {
        this.icon = icon;
        this.name = name;
        this.position = 0;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public void moveToTile(Tile tile) {
        position = tile.getNumber();
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }


}