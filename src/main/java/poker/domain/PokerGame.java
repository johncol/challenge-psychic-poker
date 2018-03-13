package poker.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PokerGame {

  private final Hand hand;
  private final Deck deck;

  public static PokerGame with(Hand hand, Deck deck) {
    return new PokerGame(hand, deck);
  }

}
