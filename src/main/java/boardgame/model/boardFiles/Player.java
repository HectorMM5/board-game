package boardgame.model.boardFiles;

import javafx.scene.paint.Color;

public class Player {
    private String icon;
    final String name;
    int position;
    Color color;

    public Player(String icon, String name) {
        this.icon = icon;
        this.name = name;
        this.color = color;
        this.position = 1;
    }

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }

    public String getIcon() { return icon; }

    public void setIcon(String icon) { this.icon = icon; }

    public String getName() { return name; }

    public void moveToTile(Tile tile) {
        position = tile.getNumber() + 1;
    }


}