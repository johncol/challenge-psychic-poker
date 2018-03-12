package poker.checker;

import java.util.List;
import java.util.Optional;
import poker.domain.card.Card;

public abstract class HandValueChecker {

  protected abstract boolean cardsSatisfyHandValueRules(List<Card> cards);

  public boolean checkHandValueRulesFor(List<Card> cards) {
    return cardsSatisfyHandValueRules(cards);
  }

  public List<Card> subsetThatSatisfyRulesFor(List<Card> cards) {
    throw new UnsupportedOperationException("Default behavior is not implemented nor required");
  }

}
