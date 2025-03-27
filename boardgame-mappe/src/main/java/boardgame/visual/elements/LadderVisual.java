package boardgame.visual.elements;

import java.util.ArrayList;
import java.util.stream.IntStream;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LadderVisual extends Group {
    
    public LadderVisual() {
        Rectangle leftPillar = new Rectangle();
        leftPillar.setY(50);
        leftPillar.setX(20);
        leftPillar.setWidth(10);
        leftPillar.setHeight(100);
        leftPillar.setFill(Color.RED);

        Rectangle rightPillar = new Rectangle();
        rightPillar.setY(50);
        rightPillar.setX(40);
        rightPillar.setWidth(10);
        rightPillar.setHeight(100);
        rightPillar.setFill(Color.RED);

        int midpoint = 30;

        int height = 100;

        ArrayList<Rectangle> steps = new ArrayList<>();
        IntStream.rangeClosed(0, height / 30)
            .forEach(i -> {
                Rectangle step = new Rectangle();
                step.setX(20);
                step.setY(50 + i*30);
                step.setWidth(20);
                step.setHeight(5);
                step.setFill(Color.RED);
                
                steps.add(step);

            });
        
        this.getChildren().addAll(steps);
        this.getChildren().addAll(rightPillar, leftPillar);




    }

    public Group getLadder() { return this; }
    
    
}
