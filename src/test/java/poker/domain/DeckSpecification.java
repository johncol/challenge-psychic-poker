package poker.domain;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class DeckSpecification {

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailToCreateNewDeckWhenSendingLessThan5Cards() {
    Deck.with(List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS)
    ));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldFailToCreateNewDeckWhenSendingMoreThan5Cards() {
    Deck.with(List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.KING, Suit.CLUBS),
        Card.of(FaceValue.KING, Suit.DIAMONDS)
    ));
  }

  @Test
  public void shouldCreateNewDeckWhenSending5Cards() {
    Deck deck = Deck.with(List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.KING, Suit.CLUBS)
    ));

    assertThat(deck, is(not(nullValue())));
  }

  @Test
  public void shouldGetTheNFirstCardsOfTheDeckWhenGlancingAtTheDeck() {
    Card AC = Card.of(FaceValue.ACE, Suit.CLUBS);
    Card AD = Card.of(FaceValue.ACE, Suit.DIAMONDS);
    Card AH = Card.of(FaceValue.ACE, Suit.HEARTS);
    Card AS = Card.of(FaceValue.ACE, Suit.SPADES);
    Card KC = Card.of(FaceValue.KING, Suit.CLUBS);
    Deck deck = Deck.with(List.of(AC, AD, AH, AS, KC));

    List<Card> cards = deck.glanceFirst(3);

    assertThat(cards, hasSize(3));
    assertThat(cards, contains(AC, AD, AH));
  }

}
