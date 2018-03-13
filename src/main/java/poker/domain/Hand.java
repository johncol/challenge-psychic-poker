package poker.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import poker.domain.card.Card;

@Getter
@ToString
@EqualsAndHashCode
public class Hand {

  public static final int CARDS_IN_HAND = 5;

  private final List<Card> cards;

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

}
