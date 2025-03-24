package boardgame.Visuals;

import boardgame.Logic.Functionality.Dice;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


public class ButtonVisual {

    public HBox getPane() {

        Dice dice = new Dice(6);

        Button rollDiceButton = new Button("Roll dice");
        //rollDiceButton.setOnAction();
        HBox hbox = new HBox(rollDiceButton);
        hbox.setSpacing(10);
        return hbox;

        
    }
}

