package boardgame;

import java.util.Random;

public class Die {
  public static final Random RANDOM = new Random();
  private int lastRolledValue;

  public int roll() {
    lastRolledValue = RANDOM.nextInt(1, 7);
    return lastRolledValue;
  }

  public int roll(int max) {
    lastRolledValue = Die.nextInt(1, max + 1);
    return lastRolledValue;
  }

  public int getValue() {
    return lastRolledValue;
  }
}
