package poker.domain.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FaceValue implements CharacterValuedEnum {

  ACE('A'),
  TWO('2'),
  THREE('3'),
  FOUR('4'),
  FIVE('5'),
  SIX('6'),
  SEVEN('7'),
  EIGHT('8'),
  NINE('9'),
  TEN('T'),
  JACK('J'),
  QUEEN('Q'),
  KING('K');

  private final char value;

  @Override
  public char getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static FaceValue of(char cardCharacter) {
    return CharacterValuedEnum.getFrom(values(), cardCharacter);
  }

}
