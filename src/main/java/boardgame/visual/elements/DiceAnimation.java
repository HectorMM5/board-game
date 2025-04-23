package boardgame.visual.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DiceAnimation {

    private GridPane diceBase;
    private List<Circle> points;

    public DiceAnimation() {
        this.diceBase = new GridPane();
        this.points = new ArrayList<>(9);
        diceBase.setHgap(0); 
        diceBase.setVgap(0);

        IntStream.rangeClosed(0, 8)
        .forEach(i -> {
            StackPane die = new StackPane();
            Rectangle background = new Rectangle(20, 20, Color.WHITE);
            background.setStroke(Color.BLACK);

            Circle circle = new Circle(5, Color.WHITE); 
            points.add(circle);

            die.getChildren().addAll(background, circle);
            diceBase.add(die, i % 3, i / 3);
        });

    }

    public GridPane getDiceBase() {
        return diceBase;
    }

    public void displayRoll(int roll) {
        points.forEach(c -> c.setFill(Color.WHITE));

        switch (roll) {
            case 1 -> setPips(4);
            case 2 -> setPips(0, 8);
            case 3 -> setPips(0, 4, 8);
            case 4 -> setPips(0, 2, 6, 8);
            case 5 -> setPips(0, 2, 4, 6, 8);
            case 6 -> setPips(0, 2, 3, 5, 6, 8);
            default -> throw new IllegalArgumentException("Roll must be between 1 and 6");
        }
    }

    private void setPips(int... indices) {
        for (int i : indices) {
            points.get(i).setFill(Color.BLACK);
        }
    }
    

    
    
}
