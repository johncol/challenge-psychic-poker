package poker.io;

import poker.domain.PokerGame;

public interface Parser<T> {

  /**
   * Builds a PokerGame instance given any representation of the cards in hand and in deck
   */
  PokerGame parse(T source);

}
