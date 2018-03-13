package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.checker.api.HandValueChecker;
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

    boolean isStraightFlush = checker.checkRulesFor(sequentialSameSuitCardsList);

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

    boolean isStraightFlush = checker.checkRulesFor(sequentialButDifferentSuitCardsList);

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

    boolean isStraightFlush = checker.checkRulesFor(nonSequentialSameSuitCardsList);

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

    boolean isStraightFlush = checker.checkRulesFor(nonSequentialDifferentSuitCardsList);

    assertThat(isStraightFlush, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenFiveCardsConformFlushAndFiveConformStraightButAreNotExactlyTheSameSetOfCards() {
    List<Card> cardsList = List.of(
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.DIAMONDS),
        Card.of(FaceValue.THREE, Suit.DIAMONDS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.FIVE, Suit.DIAMONDS),
        Card.of(FaceValue.SEVEN, Suit.DIAMONDS)
    );

    boolean isStraightFlush = checker.checkRulesFor(cardsList);

    assertThat(isStraightFlush, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenFiveCardsConformFlushAndOtherFiveConformStraightButAreTotallyDifferentSetOfCards() {
    List<Card> cardsList = List.of(
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.SPADES),
        Card.of(FaceValue.THREE, Suit.DIAMONDS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.FIVE, Suit.CLUBS),
        Card.of(FaceValue.SEVEN, Suit.HEARTS),
        Card.of(FaceValue.NINE, Suit.HEARTS),
        Card.of(FaceValue.JACK, Suit.HEARTS),
        Card.of(FaceValue.QUEEN, Suit.HEARTS),
        Card.of(FaceValue.KING, Suit.HEARTS)
    );

    boolean isStraightFlush = checker.checkRulesFor(cardsList);

    assertThat(isStraightFlush, is(false));
  }

}
