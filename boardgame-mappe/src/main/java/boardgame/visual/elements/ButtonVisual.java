package boardgame.visual.elements;

import boardgame.controller.DiceButtonController;
import boardgame.model.Dice;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ButtonVisual {
    private DiceButtonController controller;

    public ButtonVisual(DiceButtonController controller) {
        this.controller = controller;
    }

    public HBox getPane() {

        Dice dice = new Dice(1);

        Button rollDiceButton = new Button("Roll dice");
        rollDiceButton.setOnAction(e -> {
            controller.handleRollDice();
            
        });

        HBox hbox = new HBox(rollDiceButton);
        hbox.setSpacing(10);
        return hbox;
        
    }

}

