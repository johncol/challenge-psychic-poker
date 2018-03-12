package poker.checker;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;

public class ThreeOfAKindCheckerSpecification {

  private final HandValueChecker checker = new ThreeOfAKindChecker();

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
        .checkHandValueRulesFor(cardsListWithThreeCardsOfTheSameFaceValue);

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
        .checkHandValueRulesFor(cardsListWithoutThreeCardsOfTheSameFaceValue);

    assertThat(isThreeOfAKind, is(false));
  }

  @Test
  public void shouldReturnListWithThreeCardsOfTheSameFaceValueWhenAnyThreeCardsHaveTheSameFaceValue() {
    Card AC = Card.of(FaceValue.ACE, Suit.CLUBS);
    Card TH = Card.of(FaceValue.TEN, Suit.HEARTS);
    Card FD = Card.of(FaceValue.FOUR, Suit.DIAMONDS);
    Card AH = Card.of(FaceValue.ACE, Suit.HEARTS);
    Card AD = Card.of(FaceValue.ACE, Suit.DIAMONDS);

    List<Card> cardsListWithThreeOfAKind = List.of(AC, TH, FD, AH, AD);

    List<Card> threeOfAKindSubset = checker.subsetThatSatisfyRulesFor(cardsListWithThreeOfAKind);

    assertThat(threeOfAKindSubset, hasSize(3));
    assertThat(threeOfAKindSubset, containsInAnyOrder(AC, AH, AD));
  }

  @Test
  public void shouldReturnEmptyListWhenNoThreeCardsHaveTheSameFaceValue() {
    List<Card> cardsListWithoutThreeOfAKind = List.of(
        Card.of(FaceValue.ACE, Suit.CLUBS),
        Card.of(FaceValue.TEN, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.DIAMONDS),
        Card.of(FaceValue.SEVEN, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.DIAMONDS)
    );

    List<Card> cardsListWithThreeOfAKind = checker.subsetThatSatisfyRulesFor(cardsListWithoutThreeOfAKind);

    assertThat(cardsListWithThreeOfAKind, hasSize(0));
  }

}
