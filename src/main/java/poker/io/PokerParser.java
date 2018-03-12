package poker.io;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import poker.domain.Deck;
import poker.domain.Hand;
import poker.domain.PokerGame;
import poker.domain.card.Card;
import poker.factory.CardFactory;

public class PokerParser {

  public static final int CARD_REPRESENTATION_EXPECTED_LENGTH = 2;

  public PokerGame parse(String cardsInHandAndInDeck) {
    String[] inputCards = cardsInHandAndInDeck.trim().split(StringUtils.SPACE);

    int expectedAmountOfCards = getExpectedAmountOfCards();
    assertAmountOfCards(inputCards, expectedAmountOfCards);
    assertsCardsAreUnique(inputCards, expectedAmountOfCards);

    List<Card> cardsInHand = buildCardsList(inputCards, 0, Hand.CARDS_IN_HAND);
    List<Card> cardsInDeck = buildCardsList(inputCards, Hand.CARDS_IN_HAND, Deck.CARDS_IN_DECK);

    return PokerGame.with(Hand.with(cardsInHand), Deck.with(cardsInDeck));
  }

  private List<Card> buildCardsList(String[] array, int start, int amount) {
    return Arrays.stream(array)
        .skip(start)
        .limit(amount)
        .peek(this::assertCardRepresentationLength)
        .map(CardFactory::fromString)
        .collect(Collectors.toList());
  }

  private void assertAmountOfCards(String[] inputCards, int expectedAmountOfCards) {
    if (inputCards.length != expectedAmountOfCards) {
      throw new IllegalArgumentException("Amount of cards should be " + expectedAmountOfCards);
    }
  }

  private void assertsCardsAreUnique(String[] inputCards, int expectedAmountOfCards) {
    long amountOfDifferentCards = Arrays.stream(inputCards).distinct().count();
    if (amountOfDifferentCards != expectedAmountOfCards) {
      throw new IllegalArgumentException("Cards cannot repeat");
    }
  }

  private void assertCardRepresentationLength(String cardRepresentation) {
    if (cardRepresentation.length() != CARD_REPRESENTATION_EXPECTED_LENGTH) {
      throw new IllegalArgumentException("Card representation must be of 2 characters length");
    }
  }

  private int getExpectedAmountOfCards() {
    return Deck.CARDS_IN_DECK + Hand.CARDS_IN_HAND;
  }
}
