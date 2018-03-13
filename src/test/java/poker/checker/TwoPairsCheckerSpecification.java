package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class TwoPairsCheckerSpecification {

  private final HandValueChecker checker = new TwoPairsChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenTwoPairsOfCardsMatchTheirFaceValue() {
    List<Card> cardsListWithTwoPairs = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.TEN, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS)
    );

    boolean isTwoPairs = checker.checkRulesFor(cardsListWithTwoPairs);

    assertThat(isTwoPairs, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenOnlyOnePairOfCardsMatchTheirFaceValue() {
    List<Card> cardsListWithOnlyOnePair = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.NINE, Suit.DIAMONDS),
        Card.of(FaceValue.EIGHT, Suit.DIAMONDS)
    );

    boolean isTwoPairs = checker.checkRulesFor(cardsListWithOnlyOnePair);

    assertThat(isTwoPairs, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenNoCardsMatchTheirFaceValue() {
    List<Card> cardsListWithoutAnyPair = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.JACK, Suit.HEARTS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.NINE, Suit.DIAMONDS),
        Card.of(FaceValue.EIGHT, Suit.DIAMONDS)
    );

    boolean isTwoPairs = checker.checkRulesFor(cardsListWithoutAnyPair);

    assertThat(isTwoPairs, is(false));
  }

}
