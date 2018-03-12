package poker.checker;

import java.util.List;
import poker.domain.Hand;
import poker.domain.card.Card;

public abstract class HandValueChecker {

  protected abstract boolean cardsSatisfyHandValueRules(List<Card> cards);

  public boolean checkHandValueRulesFor(List<Card> cards) {
    validateCardsListCondition(cards);
    return cardsSatisfyHandValueRules(cards);
  }

  protected void validateCardsListCondition(List<Card> cards) throws IllegalArgumentException {
    if (cards == null || cards.size() != Hand.CARDS_IN_HAND) {
      throw new IllegalArgumentException("Cannot safely check if cards list conforms a hand value when size is not " + Hand.CARDS_IN_HAND);
    }
  }

}
