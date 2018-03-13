package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class FlushCheckerSpecification {

  private final HandValueChecker checker = new FlushChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenFiveCardsBelongToTheSameSuit() {
    List<Card> sameSuitCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.HEARTS),
        Card.of(FaceValue.KING, Suit.HEARTS),
        Card.of(FaceValue.EIGHT, Suit.HEARTS)
    );

    boolean isFlush = checker.checkRulesFor(sameSuitCardsList);

    assertThat(isFlush, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenFiveCardsDoNotBelongToTheSameSuit() {
    List<Card> severalSuitsCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.KING, Suit.HEARTS),
        Card.of(FaceValue.EIGHT, Suit.DIAMONDS)
    );

    boolean isFlush = checker.checkRulesFor(severalSuitsCardsList);

    assertThat(isFlush, is(false));
  }

}
