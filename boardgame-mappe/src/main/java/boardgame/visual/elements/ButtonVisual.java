package boardgame.visual.elements;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ButtonVisual {
    private Runnable onDiceRoll;

    public ButtonVisual(Runnable onDiceRoll) {
        this.onDiceRoll = onDiceRoll;
    }

    public HBox getPane() {

        Button rollDiceButton = new Button("Roll dice");
        rollDiceButton.setOnAction(e -> {
            onDiceRoll.run();
            
        });

        HBox hbox = new HBox(rollDiceButton);
        hbox.setSpacing(10);
        return hbox;
        
    }

}

