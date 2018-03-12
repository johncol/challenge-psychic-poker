package poker.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import poker.checker.CheckerFactory;
import poker.checker.HandValueChecker;
import poker.domain.card.Card;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum HandValue {

  HIGHEST_CARD(CheckerFactory.forHighestCard()),

  ONE_PAIR(CheckerFactory.forOnePair()),

  TWO_PAIRS(CheckerFactory.forTwoPairs()),

  THREE_OF_A_KIND(CheckerFactory.forThreeOfAKind()),

  STRAIGHT(CheckerFactory.forStraight()),

  FLUSH(CheckerFactory.forFlush()),

  FULL_HOUSE(CheckerFactory.forFullHouse()),

  FOUR_OF_A_KIND(CheckerFactory.forFourOfAKind()),

  STRAIGHT_FLUSH(CheckerFactory.forStraightFlush());

  private static final HandValue[] valuesInDescendingOrder;

  static {
    valuesInDescendingOrder = Arrays.stream(HandValue.values())
        .sorted(Comparator.reverseOrder())
        .toArray(HandValue[]::new);
  }

  private final HandValueChecker checker;

  public static HandValue of(List<Card> cards) {
    if (cards == null || cards.size() != 5) {
      throw new IllegalArgumentException("To calculate hand value a 5-cards list needs to be provided");
    }

    for (HandValue value : valuesInDescendingOrder) {
      if (value.checker.checkHandValueRulesFor(cards)) {
        return value;
      }
    }
    throw new IllegalStateException("Non expected state exception, at least highest card checker should have returned true");
  }

  public boolean isGreaterThan(HandValue other) {
    return compareTo(other) > 0;
  }

  @Override
  public String toString() {
    return super.toString().toLowerCase().replaceAll("_", "-");
  }
}
