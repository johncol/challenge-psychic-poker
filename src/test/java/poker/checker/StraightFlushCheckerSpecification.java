package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class StraightFlushCheckerSpecification {

  private HandValueChecker checker = new StraightFlushChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenFiveCardsConformStraightAndAllBelongToTheSameSuit() {
    List<Card> sequentialSameSuitCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.FIVE, Suit.HEARTS),
        Card.of(FaceValue.THREE, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.HEARTS)
    );

    boolean isStraightFlush = checker.checkHandValueRulesFor(sequentialSameSuitCardsList);

    assertThat(isStraightFlush, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenCardsConformStraightButDoNotBelongToTheSameSuit() {
    List<Card> sequentialButDifferentSuitCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.DIAMONDS),
        Card.of(FaceValue.FIVE, Suit.CLUBS),
        Card.of(FaceValue.THREE, Suit.SPADES),
        Card.of(FaceValue.FOUR, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.DIAMONDS)
    );

    boolean isStraightFlush = checker.checkHandValueRulesFor(sequentialButDifferentSuitCardsList);

    assertThat(isStraightFlush, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenFiveCardsBelongToTheSameSuitButDoNotConformAStraight() {
    List<Card> nonSequentialSameSuitCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.KING, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.HEARTS)
    );

    boolean isStraightFlush = checker.checkHandValueRulesFor(nonSequentialSameSuitCardsList);

    assertThat(isStraightFlush, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenCardsNeitherConformStraightNorBelongToTheSameSuit() {
    List<Card> nonSequentialDifferentSuitCardsList = List.of(
        Card.of(FaceValue.KING, Suit.DIAMONDS),
        Card.of(FaceValue.FIVE, Suit.CLUBS),
        Card.of(FaceValue.THREE, Suit.SPADES),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.DIAMONDS)
    );

    boolean isStraightFlush = checker.checkHandValueRulesFor(nonSequentialDifferentSuitCardsList);

    assertThat(isStraightFlush, is(false));
  }

}
