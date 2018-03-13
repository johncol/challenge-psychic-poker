package poker.domain.card;

public class CardFactory {

  public static Card fromString(String cardRepresentation) {
    FaceValue faceValue = FaceValue.of(cardRepresentation.charAt(0));
    Suit suit = Suit.of(cardRepresentation.charAt(1));
    return Card.of(faceValue, suit);
  }

}
