package boardgame.visual.elements;

import java.util.ArrayList;
import java.util.stream.IntStream;

import boardgame.model.effectFiles.LadderEffect;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LadderVisual extends Group {
    
    public LadderVisual(LadderEffect ladder) {
        Rectangle leftPillar = new Rectangle();
        leftPillar.setY(5);
        leftPillar.setX(5);
        leftPillar.setWidth(10);
        leftPillar.setHeight(100);
        leftPillar.setFill(Color.RED);

        Rectangle rightPillar = new Rectangle();
        rightPillar.setY(5);
        rightPillar.setX(35);
        rightPillar.setWidth(10);
        rightPillar.setHeight(100);
        rightPillar.setFill(Color.RED);

        int midpoint = 30;

        int height = 100;

        ArrayList<Rectangle> steps = new ArrayList<>();

        int stepAmount = height/25;

        IntStream.rangeClosed(0, stepAmount - 2)
            .forEach(i -> {
                Rectangle step = new Rectangle();
                step.setX(15);
                step.setY(25 + i*25);
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
