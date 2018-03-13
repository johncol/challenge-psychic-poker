package poker.checker;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import poker.checker.api.FaceValueGrouper;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;

abstract class RepeatableFaceValueChecker implements HandValueChecker, FaceValueGrouper {

  protected abstract int amountOfFaceValueRepetitions();

  @Override
  public final boolean checkRulesFor(List<Card> cards) {
    return cards.stream()
        .collect(Collectors.groupingBy(Card::getFaceValue, Collectors.counting()))
        .values()
        .stream()
        .anyMatch(faceRepetitions -> faceRepetitions >= amountOfFaceValueRepetitions());
  }

  @Override
  public Map<FaceValue, List<Card>> groupByFaceValue(List<Card> cards) {
    return cards.stream()
        .collect(Collectors.groupingBy(Card::getFaceValue, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(entry -> entry.getValue() >= amountOfFaceValueRepetitions())
        .map(Entry::getKey)
        .map(faceValue -> cards.stream()
            .filter(card -> card.getFaceValue().equals(faceValue))
            .limit(amountOfFaceValueRepetitions())
            .collect(Collectors.toList()))
        .collect(Collectors.toMap(list -> list.get(0).getFaceValue(), list -> list));
  }

}
