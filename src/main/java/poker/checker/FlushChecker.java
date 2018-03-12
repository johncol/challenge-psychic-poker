package poker.checker;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import poker.domain.card.Card;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class FlushChecker extends HandValueChecker {

  public static final int MIN_CARDS_REQUIRED_FOR_FLUSH = 5;

  @Override
  public boolean cardsSatisfyHandValueRules(List<Card> cards) {
    return cards.stream()
        .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()))
        .values()
        .stream()
        .anyMatch(sameSuitLength -> sameSuitLength >= MIN_CARDS_REQUIRED_FOR_FLUSH);
  }
}
