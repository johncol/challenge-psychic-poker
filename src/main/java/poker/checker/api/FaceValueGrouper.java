package poker.checker.api;

import java.util.List;
import java.util.Map;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;

public interface FaceValueGrouper {

  /**
   * Maps face values to list of cards.
   * Conditions to exclude some key-value pairs are concern of each implementation
   */
  Map<FaceValue, List<Card>> groupByFaceValue(List<Card> cards);

}
