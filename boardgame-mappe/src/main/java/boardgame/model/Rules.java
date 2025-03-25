package boardgame.model;

import java.util.ArrayList;

public class Rules {
    private final ArrayList<Effect> effects;
    private final int diceSize;
    private final Dice dice;



    Rules(ArrayList<Effect> effects, int diceSize) {
        this.effects = effects;
        this.diceSize = diceSize;
        this.dice = new Dice(diceSize);
        
    }

    public Dice getDice() {
        return dice;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    

    
   
}
