package boardgame.visual.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;


public class ButtonVisual extends Button {

    public ButtonVisual(Runnable onDiceRoll) {
        super("Roll dice");
        this.setAlignment(Pos.CENTER);

        this.setOnAction(e -> {
            onDiceRoll.run();
            setDisable(true);
            
        });
        
        
    }

}

