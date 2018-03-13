package poker.checker;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;

// TODO create interface to describe new methods
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

  // TODO check if required in production code
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

  // TODO unit testing
  public Map<FaceValue, List<Card>> groupCardsByFaceValue(List<Card> cards) {
    Map<FaceValue, List<Card>> faceValueListMap = cards.stream()
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
    return faceValueListMap;
  }

}
