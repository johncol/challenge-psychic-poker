package poker.checker;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.paukov.combinatorics3.Generator;
import poker.domain.Hand;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class StraightFlushChecker extends HandValueChecker {

  private final HandValueChecker flushChecker = CheckerFactory.forFlush();

  private final HandValueChecker straightChecker = CheckerFactory.forStraight();

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    List<List<Card>> fiveLengthCardsListCombinations = buildFiveCardsCombinations(cards);

    for (List<Card> cardsCombination : fiveLengthCardsListCombinations) {
      boolean isFlush = flushChecker.checkHandValueRulesFor(cardsCombination);
      boolean isStraight = straightChecker.checkHandValueRulesFor(cardsCombination);
      if (isFlush && isStraight) {
        return true;
      }
    }

    return false;
  }

  private List<List<Card>> buildFiveCardsCombinations(List<Card> cards) {
    return Generator.combination(cards)
        .simple(Hand.CARDS_IN_HAND)
        .stream()
        .collect(Collectors.toList());
  }
}
