package boardgame.visual.elements;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerToken extends StackPane {

    private Circle fill;
    private Circle border;

    public PlayerToken(Color color) {
        fill = new Circle(20);
        fill.setFill(color);

        border = new Circle(25);
        border.setFill(Color.BLACK);

        this.getChildren().addAll(border, fill);
    }

    
    

}
