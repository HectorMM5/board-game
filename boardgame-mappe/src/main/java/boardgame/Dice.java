package boardgame;
import java.util.Random;


public class Dice {
    public static final Random dice = new Random();


    public static int rollDice() {
        return dice.nextInt(1, 6);
    }

}