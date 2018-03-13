package poker.checker;

import java.util.List;
import poker.domain.card.Card;

// TODO make interface
public abstract class HandValueChecker {

  protected abstract boolean cardsSatisfyHandValueRules(List<Card> cards);

  public boolean checkHandValueRulesFor(List<Card> cards) {
    return cardsSatisfyHandValueRules(cards);
  }

}
