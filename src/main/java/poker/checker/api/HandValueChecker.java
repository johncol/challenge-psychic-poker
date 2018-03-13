package poker.checker.api;

import java.util.List;
import poker.domain.card.Card;

public interface HandValueChecker {

  /**
   * Checks whether a list of cards conform a concrete hand value
   */
  boolean checkRulesFor(List<Card> cards);

}
