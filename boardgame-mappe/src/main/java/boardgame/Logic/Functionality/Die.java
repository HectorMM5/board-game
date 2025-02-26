package boardgame.Logic.Functionality;

import java.util.Random;

public class Die {
  public static final Random RANDOM = new Random();
  private int lastRolledValue;

  public int roll() {
    lastRolledValue = RANDOM.nextInt(1, 7);
    return lastRolledValue;
  }

  public int roll(int max) {
  
    return 0;
  }

  public int getValue() {
    return lastRolledValue;
  }
}
