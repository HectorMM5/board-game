package boardgame.Logic.Entities;

import java.util.ArrayList;

import boardgame.Logic.Functionality.Dice;

public class Rules {
    private final ArrayList<Effect> effects;
    private final int diceSize;
    private final Dice dice;



    Rules(ArrayList<Effect> effects, int diceSize) {
        this.effects = effects;
        this.diceSize = diceSize;
        this.dice = new Dice(diceSize);
        
    }

    
   
}
