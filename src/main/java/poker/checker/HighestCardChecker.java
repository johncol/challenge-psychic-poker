package poker.checker;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class HighestCardChecker implements HandValueChecker {

  @Override
  public boolean checkRulesFor(List<Card> cards) {
    return true;
  }
}
