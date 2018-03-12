package utils;

import java.util.Random;

public class Any {

  public static <T> T of(T... values) {
    return values[new Random().nextInt(values.length)];
  }

}
