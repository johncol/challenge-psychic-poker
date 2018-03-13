package poker.checker;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.checker.api.FaceValueGrouper;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class TwoPairsChecker implements HandValueChecker {

  private final FaceValueGrouper pairsGrouper = GrouperFactory.forPairs();

  @Override
  public boolean checkRulesFor(List<Card> cards) {
    return pairsGrouper.groupByFaceValue(cards).keySet().size() >= 2;
  }
}
