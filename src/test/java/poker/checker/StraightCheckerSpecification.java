package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class StraightCheckerSpecification {

  private final HandValueChecker checker = new StraightChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenAnyFiveCardsConformASequence() {
    List<Card> listWithSequentialCards = List.of(
        Card.of(FaceValue.THREE, Suit.HEARTS),
        Card.of(FaceValue.FIVE, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.HEARTS),
        Card.of(FaceValue.SIX, Suit.HEARTS)
    );

    boolean isStraight = checker.checkRulesFor(listWithSequentialCards);

    assertThat(isStraight, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsTrueWhenFiveCardsStartingWithAceConformASequence() {
    List<Card> listWithSequentialCardsStartingWithAce = List.of(
        Card.of(FaceValue.THREE, Suit.HEARTS),
        Card.of(FaceValue.FIVE, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.HEARTS)
    );

    boolean isStraight = checker.checkRulesFor(listWithSequentialCardsStartingWithAce);

    assertThat(isStraight, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsTrueWhenFiveCardsEndingWithAceConformASequence() {
    List<Card> listWithSequentialCardsEndingWithAce = List.of(
        Card.of(FaceValue.QUEEN, Suit.HEARTS),
        Card.of(FaceValue.JACK, Suit.HEARTS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.KING, Suit.HEARTS)
    );

    boolean isStraight = checker.checkRulesFor(listWithSequentialCardsEndingWithAce);

    assertThat(isStraight, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenAnyFiveCardsDoNotConformASequence() {
    List<Card> listWithNonSequentialCards = List.of(
        Card.of(FaceValue.EIGHT, Suit.HEARTS),
        Card.of(FaceValue.JACK, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.HEARTS),
        Card.of(FaceValue.FIVE, Suit.HEARTS),
        Card.of(FaceValue.KING, Suit.HEARTS)
    );

    boolean isStraight = checker.checkRulesFor(listWithNonSequentialCards);

    assertThat(isStraight, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenAnyFiveCardsConformASequenceWithAceInBetweenTheStartAndEndOfTheSequence() {
    List<Card> listWithSequentialCardsWithAceInBetween = List.of(
        Card.of(FaceValue.JACK, Suit.HEARTS),
        Card.of(FaceValue.QUEEN, Suit.HEARTS),
        Card.of(FaceValue.KING, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.HEARTS)
    );

    boolean isStraight = checker.checkRulesFor(listWithSequentialCardsWithAceInBetween);

    assertThat(isStraight, is(false));
  }

}
