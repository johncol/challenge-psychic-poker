package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class OnePairCheckerSpecification {

  private final HandValueChecker checker = new OnePairChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenAnyTwoCardsHaveTheSameFaceValue() {
    List<Card> cardsListWithOnePair = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.EIGHT, Suit.DIAMONDS)
    );

    boolean isOnePair = checker.checkHandValueRulesFor(cardsListWithOnePair);

    assertThat(isOnePair, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenNoTwoCardsHaveTheSameFaceValue() {
    List<Card> cardsListWithoutAnyPair = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.SEVEN, Suit.HEARTS),
        Card.of(FaceValue.EIGHT, Suit.DIAMONDS)
    );

    boolean isOnePair = checker.checkHandValueRulesFor(cardsListWithoutAnyPair);

    assertThat(isOnePair, is(false));
  }

}
