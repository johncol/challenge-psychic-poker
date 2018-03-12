package poker.io;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import poker.domain.Deck;
import poker.domain.Hand;
import poker.domain.PokerGame;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class PokerParserSpecification {

  private final PokerParser parser = new PokerParser();

  @Test
  public void shouldPutTheRightCardsInHandWhenBuildingThePokerGame() {
    PokerGame pokerGame = parser.parse("TH JH QC QD QS QH KH AH 2S 6S");

    Hand hand = pokerGame.getHand();

    assertThat(hand.getCard(0), is(equalTo(Card.of(FaceValue.TEN, Suit.HEARTS))));
    assertThat(hand.getCard(1), is(equalTo(Card.of(FaceValue.JACK, Suit.HEARTS))));
    assertThat(hand.getCard(2), is(equalTo(Card.of(FaceValue.QUEEN, Suit.CLUBS))));
    assertThat(hand.getCard(3), is(equalTo(Card.of(FaceValue.QUEEN, Suit.DIAMONDS))));
    assertThat(hand.getCard(4), is(equalTo(Card.of(FaceValue.QUEEN, Suit.SPADES))));
  }

  @Test
  public void shouldPutTheRightCardsInDeckWhenBuildingThePokerGame() {
    PokerGame pokerGame = parser.parse("TH JH QC QD QS QH KH AH 2S 6S");

    Deck deck = pokerGame.getDeck();

    assertThat(deck.getCard(0), is(equalTo(Card.of(FaceValue.QUEEN, Suit.HEARTS))));
    assertThat(deck.getCard(1), is(equalTo(Card.of(FaceValue.KING, Suit.HEARTS))));
    assertThat(deck.getCard(2), is(equalTo(Card.of(FaceValue.ACE, Suit.HEARTS))));
    assertThat(deck.getCard(3), is(equalTo(Card.of(FaceValue.TWO, Suit.SPADES))));
    assertThat(deck.getCard(4), is(equalTo(Card.of(FaceValue.SIX, Suit.SPADES))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenANonExpectedCharacterIsFound() {
    parser.parse("TH JH QC QD ++ QH KH AH 2S 6S");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenLessThanTenCardsAreFound() {
    parser.parse("TH JH QC QD QS");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenMoreThanTenCardsAreFound() {
    parser.parse("TH JH QC QD QS TH JH QC QD QS QH KH AH 2S 6S");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTheSameCardIsFoundMoreThanOnce() {
    parser.parse("TH JH QC QD QS TH JH QC QD QS");
  }

}
