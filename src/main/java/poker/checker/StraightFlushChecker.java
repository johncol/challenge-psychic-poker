package poker.checker;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.paukov.combinatorics3.Generator;
import poker.checker.api.HandValueChecker;
import poker.domain.Hand;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class StraightFlushChecker implements HandValueChecker {

  private final HandValueChecker flushChecker = CheckerFactory.forFlush();

  private final HandValueChecker straightChecker = CheckerFactory.forStraight();

  @Override
  public boolean checkRulesFor(List<Card> cards) {
    List<List<Card>> fiveLengthCardsListCombinations = buildFiveCardsCombinations(cards);

    for (List<Card> cardsCombination : fiveLengthCardsListCombinations) {
      boolean isFlush = flushChecker.checkRulesFor(cardsCombination);
      boolean isStraight = straightChecker.checkRulesFor(cardsCombination);
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
