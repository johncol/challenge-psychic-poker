package poker.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import poker.domain.card.Card;

@ToString
@EqualsAndHashCode
public class Hand {

  public static final int CARDS_IN_HAND = 5;

  @Getter(AccessLevel.PRIVATE)
  private final List<Card> cards;

  @Getter(AccessLevel.PUBLIC)
  private final HandValue value;

  private Hand(List<Card> cards) {
    if (cards == null || cards.size() != CARDS_IN_HAND) {
      throw new IllegalArgumentException("Amount of cards in hand must be " + CARDS_IN_HAND);
    }
    this.cards = Collections.unmodifiableList(cards);
    this.value = HandValue.of(cards);
  }

  public static Hand with(List<Card> cards) {
    return new Hand(new ArrayList<>(cards));
  }

  public CardInHandReplacer replace(Card... cardsInHand) {
    List<Card> notInHand = Arrays.stream(cardsInHand)
        .filter(card -> cards.indexOf(card) < 0)
        .collect(Collectors.toList());
    if (!notInHand.isEmpty()) {
      throw new IllegalArgumentException("All cards to be replaced need to be in hand: " + notInHand);
    }
    return new CardInHandReplacer(this, cardsInHand);
  }

  public Card getCard(int index) {
    return cards.get(index);
  }

  public static class CardInHandReplacer {

    private final Hand hand;
    private final List<Card> cardsInHand;

    public CardInHandReplacer(Hand hand, Card[] cardsInHand) {
      this.hand = hand;
      this.cardsInHand = Arrays.asList(cardsInHand);
    }

    public Hand with(Card... cardsInDeck) {
      if (cardsInHand.size() != cardsInDeck.length) {
        String message = String.format("%d card(s) cannot be replaced with %s card(s)", cardsInHand.size(), cardsInDeck.length);
        throw new IllegalArgumentException(message);
      }
      List<Card> newCardsInHand = hand.getCards().stream()
          .filter(card -> !cardsInHand.contains(card))
          .collect(Collectors.toList());
      newCardsInHand.addAll(Arrays.asList(cardsInDeck));
      return Hand.with(newCardsInHand);
    }
  }

}
