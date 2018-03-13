package poker.domain.card;

import java.util.Arrays;

/**
 * Base enumeration for enums which are represented by a char value
 */
public interface CharacterValuedEnum {

  char getValue();

  static <T extends CharacterValuedEnum> T getFrom(T[] values, char character) {
    return Arrays.stream(values)
        .filter(suit -> suit.getValue() == character)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Cannot create CharacterValuedEnum from character " + character));
  }

}
