package poker.checker;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class TwoPairsChecker extends HandValueChecker {

  private final HandValueChecker onePairChecker = CheckerFactory.forOnePair();

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    List<Card> firstPair = onePairChecker.subsetThatSatisfyRulesFor(cards);

    if (isEffectivelyPair(firstPair)) {
      List<Card> remainingCards = remainingCardsWithoutFirstPair(cards, firstPair);
      List<Card> secondPair = onePairChecker.subsetThatSatisfyRulesFor(remainingCards);
      return isEffectivelyPair(secondPair) && differentFaceValue(firstPair, secondPair);
    }

    return false;
  }

  private boolean isEffectivelyPair(List<Card> possiblePairList) {
    return possiblePairList.size() == OnePairChecker.CARD_REPETITIONS_FOR_ONE_PAIR;
  }

  private List<Card> remainingCardsWithoutFirstPair(List<Card> cards, List<Card> firstPair) {
    return cards.stream()
        .filter(card -> !firstPair.contains(card))
        .collect(Collectors.toList());
  }

  private boolean differentFaceValue(List<Card> pairOne, List<Card> pairTwo) {
    FaceValue pairOneFaceValue = pairOne.get(0).getFaceValue();
    FaceValue pairTwoFaceValue = pairTwo.get(0).getFaceValue();
    return !pairOneFaceValue.equals(pairTwoFaceValue);
  }
}
