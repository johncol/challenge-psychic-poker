package poker.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import poker.domain.card.Card;

@ToString
@EqualsAndHashCode
public class Deck {

  public static final int CARDS_IN_DECK = 5;

  private final List<Card> cards;

  private Deck(List<Card> cards) {
    if (cards == null || cards.size() != CARDS_IN_DECK) {
      throw new IllegalArgumentException("Deck size must be " + CARDS_IN_DECK);
    }
    this.cards = Collections.unmodifiableList(new ArrayList<>(cards));
  }

  public static Deck with(List<Card> cards) {
    return new Deck(cards);
  }

  public List<Card> glanceFirst(int nCards) {
    if (nCards < 0 || nCards > CARDS_IN_DECK) {
      throw new IllegalArgumentException("Cards to glance must be a number between 0 and " + CARDS_IN_DECK);
    }
    return Collections.unmodifiableList(cards.subList(0, nCards));
  }
}
