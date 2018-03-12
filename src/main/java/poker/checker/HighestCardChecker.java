package poker.checker;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class HighestCardChecker extends HandValueChecker {

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    return true;
  }
}
