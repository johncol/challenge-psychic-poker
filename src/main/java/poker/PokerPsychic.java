package poker;

import lombok.AllArgsConstructor;
import poker.domain.PokerGame;
import poker.io.PokerParser;

@AllArgsConstructor
public class PokerPsychic {

  private final PokerParser pokerParser;

  public String bestPossibleHandFor(String cardsInHandAndInDeck) {
    PokerGame game = pokerParser.parse(cardsInHandAndInDeck);
    PokerGame improvedGame = this.maximizeHandValue(game);
    return improvedGame.handValue();
  }

  private PokerGame maximizeHandValue(PokerGame game) {
    return game;
  }
}
