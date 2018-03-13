package poker.checker;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class FullHouseChecker extends HandValueChecker {

  private final FaceValueRepetitionsChecker onePairChecker = CheckerFactory.forOnePair();

  private final FaceValueRepetitionsChecker threeOfAKindChecker = CheckerFactory.forThreeOfAKind();

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    Map<FaceValue, List<Card>> trios = threeOfAKindChecker.groupCardsByFaceValue(cards);
    if (trios.entrySet().isEmpty()) {
      return false;
    }
    if (trios.entrySet().size() >= 2) {
      return true;
    }

    Map<FaceValue, List<Card>> pairs = onePairChecker.groupCardsByFaceValue(cards);
    return pairs.entrySet().size() >= 2;
  }
}
