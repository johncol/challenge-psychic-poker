package poker.checker;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import poker.domain.card.Card;

abstract class FaceValueRepetitionsChecker extends HandValueChecker {

  protected abstract int amountOfFaceValueRepetitions();

  @Override
  protected final boolean cardsSatisfyHandValueRules(List<Card> cards) {
    return cards.stream()
        .collect(Collectors.groupingBy(Card::getFaceValue, Collectors.counting()))
        .values()
        .stream()
        .anyMatch(faceRepetitions -> faceRepetitions >= amountOfFaceValueRepetitions());
  }

  @Override
  public List<Card> subsetThatSatisfyRulesFor(List<Card> cards) {
    return cards.stream()
        .collect(Collectors.groupingBy(Card::getFaceValue, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() >= amountOfFaceValueRepetitions())
        .findAny()
        .map(Entry::getKey)
        .map(faceValue -> cards.stream()
            .filter(card -> card.getFaceValue().equals(faceValue))
            .limit(amountOfFaceValueRepetitions())
            .collect(Collectors.toList()))
        .orElse(List.of());
  }

}
