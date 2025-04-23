package boardgame.visual.elements;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ButtonVisual extends HBox {
    private Runnable onDiceRoll;

    public ButtonVisual(Runnable onDiceRoll) {
        this.onDiceRoll = onDiceRoll;

        Button rollDiceButton = new Button("Roll dice");
        rollDiceButton.setOnAction(e -> {
            onDiceRoll.run();
            
        });
        
        this.getChildren().add(rollDiceButton);
        this.setSpacing(10);
        
    }

}

