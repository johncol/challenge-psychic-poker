package poker.checker;

import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.checker.api.FaceValueGrouper;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class FullHouseChecker implements HandValueChecker {

  private final FaceValueGrouper pairsGrouper = GrouperFactory.forPairs();

  private final FaceValueGrouper threeOfAKindGrouper = GrouperFactory.forThreeOfAKind();

  @Override
  public boolean checkRulesFor(List<Card> cards) {
    Map<FaceValue, List<Card>> trios = threeOfAKindGrouper.groupByFaceValue(cards);
    if (trios.entrySet().isEmpty()) {
      return false;
    }
    if (trios.entrySet().size() >= 2) {
      return true;
    }

    Map<FaceValue, List<Card>> pairs = pairsGrouper.groupByFaceValue(cards);
    return pairs.entrySet().size() >= 2;
  }
}
