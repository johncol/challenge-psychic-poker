package poker.factory;

import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class CardFactory {

  public static Card fromString(String cardRepresentation) {
    FaceValue faceValue = FaceValue.of(cardRepresentation.charAt(0));
    Suit suit = Suit.of(cardRepresentation.charAt(1));
    return Card.of(faceValue, suit);
  }

}
