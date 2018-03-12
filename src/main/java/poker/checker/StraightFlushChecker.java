package poker.checker;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class StraightFlushChecker extends HandValueChecker {

  private final HandValueChecker flushChecker = CheckerFactory.forFlush();

  private final HandValueChecker straightChecker = CheckerFactory.forStraight();

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    return flushChecker.checkHandValueRulesFor(cards) && straightChecker.checkHandValueRulesFor(cards);
  }
}
