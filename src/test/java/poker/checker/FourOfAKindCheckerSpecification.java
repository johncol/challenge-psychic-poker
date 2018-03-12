package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class FourOfAKindCheckerSpecification {

  private final HandValueChecker checker = new FourOfAKindChecker();

  @Test
  public void shouldReturnCheckResultIsTrueWhenFourCardsHaveTheSameFaceValue() {
    List<Card> cardsListWithFourCardsOfTheSameFaceValue = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.ACE, Suit.SPADES),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS)
    );

    boolean isFourOfAKind = checker.checkHandValueRulesFor(cardsListWithFourCardsOfTheSameFaceValue);

    assertThat(isFourOfAKind, is(true));
  }

  @Test
  public void shouldReturnCheckResultIsFalseWhenNoFourCardsHaveTheSameFaceValue() {
    List<Card> cardsListWithoutThreeCardsOfTheSameFaceValue = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS)
    );

    boolean isFourOfAKind = checker.checkHandValueRulesFor(cardsListWithoutThreeCardsOfTheSameFaceValue);

    assertThat(isFourOfAKind, is(false));
  }

}
