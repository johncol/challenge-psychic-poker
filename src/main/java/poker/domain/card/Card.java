package poker.domain.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Card {

  private final FaceValue faceValue;
  private final Suit suit;

  public static Card of(FaceValue faceValue, Suit suit) {
    if (faceValue == null || suit == null) {
      throw new IllegalArgumentException("Neither FaceValue nor Suit can be null");
    }
    return new Card(faceValue, suit);
  }

  @Override
  public String toString() {
    return faceValue.toString() + suit.toString();
  }
}
