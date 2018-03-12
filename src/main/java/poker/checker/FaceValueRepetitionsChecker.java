package poker.checker;

import java.util.List;
import java.util.stream.Collectors;
import poker.domain.card.Card;

abstract class FaceValueRepetitionsChecker extends HandValueChecker {

  protected abstract int minimumAmountOfFaceValueRepetitions();

  @Override
  protected final boolean cardsSatisfyHandValueRules(List<Card> cards) {
    return cards.stream()
        .collect(Collectors.groupingBy(Card::getFaceValue, Collectors.counting()))
        .values()
        .stream()
        .anyMatch(faceValueRepetitions -> faceValueRepetitions >= minimumAmountOfFaceValueRepetitions());
  }
}
