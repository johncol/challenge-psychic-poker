package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class FullHouseCheckerSpecification {

  private final HandValueChecker checker = new FullHouseChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenTwoCardsAreAPairAndTheOtherThreeAreThreeOfAKind() {
    List<Card> fullHouseCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.CLUBS),
        Card.of(FaceValue.TWO, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.DIAMONDS)
    );

    boolean isFullHouse = checker.checkHandValueRulesFor(fullHouseCardsList);

    assertThat(isFullHouse, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenTwoCardsAreAPairButTheOtherThreeAreNotThreeOfAKind() {
    List<Card> onePairCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.CLUBS),
        Card.of(FaceValue.THREE, Suit.SPADES),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS)
    );

    boolean isFullHouse = checker.checkHandValueRulesFor(onePairCardsList);

    assertThat(isFullHouse, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenThreeCardsAreThreeOfAKindButTheOtherTwoAreNotAPair() {
    List<Card> threeOfAKindCardsList = List.of(
        Card.of(FaceValue.KING, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.CLUBS),
        Card.of(FaceValue.TWO, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.DIAMONDS)
    );

    boolean isFullHouse = checker.checkHandValueRulesFor(threeOfAKindCardsList);

    assertThat(isFullHouse, is(false));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenNoCardsFormAnyMeaningfulGameValue() {
    List<Card> nonFullHouseCardsList = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.QUEEN, Suit.SPADES),
        Card.of(FaceValue.EIGHT, Suit.CLUBS),
        Card.of(FaceValue.FOUR, Suit.SPADES),
        Card.of(FaceValue.TWO, Suit.DIAMONDS)
    );

    boolean isFullHouse = checker.checkHandValueRulesFor(nonFullHouseCardsList);

    assertThat(isFullHouse, is(false));
  }

}
