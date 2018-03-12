package poker.domain.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Suit implements CharacterValuedEnum {

  CLUBS('C'),
  DIAMONDS('D'),
  HEARTS('H'),
  SPADES('S');

  private final char value;

  @Override
  public char getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static Suit of(char suitCharacter) {
    return CharacterValuedEnum.getFrom(values(), suitCharacter);
  }
}
