package boardgame.visual.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ButtonVisual extends HBox {

    public ButtonVisual(Runnable onDiceRoll) {
        this.setAlignment(Pos.CENTER);

        Button rollDiceButton = new Button("Roll dice");
        rollDiceButton.setOnAction(e -> {
            onDiceRoll.run();
            
        });
        
        this.getChildren().add(rollDiceButton);
        this.setSpacing(10);
        
    }

}

