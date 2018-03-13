package poker.checker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import poker.checker.api.HandValueChecker;
import poker.domain.card.Card;
import poker.domain.card.FaceValue;
import poker.domain.card.Suit;
import utils.Any;

public class HighestCardCheckerSpecification {

  private final HandValueChecker checker = new HighestCardChecker();

  @Test
  public void CheckResultIsTrueWhenCheckingAnyListOfCards() {
    List<Card> anyCardsList = List.of(
        Card.of(Any.of(FaceValue.values()), Any.of(Suit.values())),
        Card.of(Any.of(FaceValue.values()), Any.of(Suit.values())),
        Card.of(Any.of(FaceValue.values()), Any.of(Suit.values())),
        Card.of(Any.of(FaceValue.values()), Any.of(Suit.values())),
        Card.of(Any.of(FaceValue.values()), Any.of(Suit.values()))
    );

    boolean isHighestCard = checker.checkRulesFor(anyCardsList);

    assertThat(isHighestCard, is(true));
  }

}
