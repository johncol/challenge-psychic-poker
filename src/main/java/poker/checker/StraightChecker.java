package poker.checker;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class StraightChecker implements HandValueChecker {

  public static final int MIN_CARDS_REQUIRED_FOR_STRAIGHT = 5;

  @Override
  public boolean checkRulesFor(List<Card> cards) {
    List<Integer> values = buildListOfCardsOrdinalValues(cards);

    int sequentialCards = 1;
    for (int i = 1; i < values.size() && sequentialCards < MIN_CARDS_REQUIRED_FOR_STRAIGHT; i++) {
      sequentialCards = lastTwoCardsAreSequential(values, i) ? sequentialCards + 1 : 1;
    }

    return sequentialCards == MIN_CARDS_REQUIRED_FOR_STRAIGHT;
  }

  private List<Integer> buildListOfCardsOrdinalValues(List<Card> cards) {
    List<Integer> values = cards.stream()
        .map(Card::getFaceValue)
        .map(FaceValue::ordinal)
        .distinct()
        .sorted()
        .collect(Collectors.toList());
    if (values.get(0).equals(FaceValue.ACE.ordinal())) {
      values.add(FaceValue.KING.ordinal() + 1);
    }
    return Collections.unmodifiableList(values);
  }

  private boolean lastTwoCardsAreSequential(List<Integer> values, int index) {
    return values.get(index) - values.get(index - 1) == 1;
  }

}
