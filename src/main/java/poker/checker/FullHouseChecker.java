package poker.checker;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class FullHouseChecker extends HandValueChecker {

  private final HandValueChecker onePairChecker = CheckerFactory.forOnePair();

  private final HandValueChecker threeOfAKindChecker = CheckerFactory.forThreeOfAKind();

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    List<Card> threeOfAKindCards = threeOfAKindChecker.subsetThatSatisfyRulesFor(cards);

    if (isEffectivelyThreeOfAKind(threeOfAKindCards)) {
      List<Card> possiblePairOfCardsList = remainingCardsWithoutTheThreeOfAKind(cards, threeOfAKindCards);
      return onePairChecker.checkHandValueRulesFor(possiblePairOfCardsList);
    }

    return false;
  }

  private List<Card> remainingCardsWithoutTheThreeOfAKind(List<Card> cards, List<Card> threeOfAKindCards) {
    return cards.stream()
        .filter(card -> !threeOfAKindCards.contains(card))
        .collect(Collectors.toList());
  }

  private boolean isEffectivelyThreeOfAKind(List<Card> threeOfAKindCards) {
    return threeOfAKindCards.size() == ThreeOfAKindChecker.CARD_REPETITIONS_FOR_THREE_OF_A_KIND;
  }

}
