package poker.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static poker.domain.HandValue.*;

import java.util.List;
import java.util.Map;
import org.junit.Test;
import poker.domain.HandValue;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;
import utils.Any;

public class HandValueSpecification {

  @Test
  public void shouldPrintHandValueInLowerCaseAndUsingHyphens() {
    Map<HandValue, String> stringValuesMap = Map.of(
        HIGHEST_CARD, "highest-card",
        ONE_PAIR, "one-pair",
        TWO_PAIRS, "two-pairs",
        THREE_OF_A_KIND, "three-of-a-kind",
        STRAIGHT, "straight",
        FLUSH, "flush",
        FULL_HOUSE, "full-house",
        FOUR_OF_A_KIND, "four-of-a-kind",
        STRAIGHT_FLUSH, "straight-flush"
    );

    stringValuesMap.forEach((handValue, stringRepresentation) ->
        assertThat(handValue.toString(), is(equalTo(stringRepresentation)))
    );
  }

  @Test
  public void shouldReturnStraightFlushIsTheHighestHandValue() {
    HandValue anyOtherHandValueButStraightFlush = Any.of(
        HIGHEST_CARD,
        ONE_PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND
    );

    boolean straightFlushIsGreaterThanAnyOther = STRAIGHT_FLUSH.isGreaterThan(anyOtherHandValueButStraightFlush);

    assertThat(straightFlushIsGreaterThanAnyOther, is(true));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenANonFiveCardsListIsPassed() {
    HandValue.of(List.of(Card.of(FaceValue.ACE, Suit.HEARTS)));
  }

  @Test
  public void shouldReturnTheRightHandValueWhenAFiveCardsListIsPassed() {
    HandValue handValue = HandValue.of(List.of(
        Card.of(FaceValue.THREE, Suit.HEARTS),
        Card.of(FaceValue.ACE, Suit.HEARTS),
        Card.of(FaceValue.FOUR, Suit.HEARTS),
        Card.of(FaceValue.TWO, Suit.HEARTS),
        Card.of(FaceValue.FIVE, Suit.HEARTS)
    ));

    assertThat(handValue, is(HandValue.STRAIGHT_FLUSH));
  }

}
