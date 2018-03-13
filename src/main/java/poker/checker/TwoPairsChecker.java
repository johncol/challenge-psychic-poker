package poker.checker;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class TwoPairsChecker extends HandValueChecker {

  private final FaceValueRepetitionsChecker onePairChecker = CheckerFactory.forOnePair();

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    return onePairChecker.groupCardsByFaceValue(cards).keySet().size() >= 2;
  }
}
