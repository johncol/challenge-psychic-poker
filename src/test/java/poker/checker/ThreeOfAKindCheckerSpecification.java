package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class ThreeOfAKindCheckerSpecification {

  private final RepeatableFaceValueChecker checker = new ThreeOfAKindChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenThreeCardsHaveTheSameFaceValue() {
    List<Card> cardsListWithThreeCardsOfTheSameFaceValue = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS)
    );

    boolean isThreeOfAKind = checker
        .checkRulesFor(cardsListWithThreeCardsOfTheSameFaceValue);

    assertThat(isThreeOfAKind, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenNoThreeCardsHaveTheSameFaceValue() {
    List<Card> cardsListWithoutThreeCardsOfTheSameFaceValue = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.EIGHT, Suit.DIAMONDS)
    );

    boolean isThreeOfAKind = checker
        .checkRulesFor(cardsListWithoutThreeCardsOfTheSameFaceValue);

    assertThat(isThreeOfAKind, is(false));
  }

}
