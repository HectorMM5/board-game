package boardgame;

import java.util.Random;


public class Dice {
  public static final Random dice = new Random();
  int lastRolledValue;

  private int roll() {
    return dice.nextInt(1, 6);
  }

  private int roll(int max) {
    return dice.nextInt(1, max);
  }

  public int getValue() {
    return lastRolledValue;
  }
}