package poker;

import java.util.List;
import lombok.AllArgsConstructor;
import poker.domain.Deck;
import poker.domain.Hand;
import poker.domain.HandValue;
import poker.domain.PokerGame;
import poker.domain.card.Card;
import poker.io.PokerParser;

@AllArgsConstructor
public class PokerPsychic {

  private final PokerParser pokerParser;

  public String bestPossibleHandFor(String cardsInHandAndInDeck) {
    PokerGame game = pokerParser.parse(cardsInHandAndInDeck);
    HandValue bestPossibleHandValue = usePokerSight(game);
    return bestPossibleHandValue.toString();
  }

  private HandValue usePokerSight(PokerGame game) {
    if (game.getHand().getValue().isTheGreatestHandValueEver()) {
      return game.getHand().getValue();
    }

    Deck deck = game.getDeck();
    Hand hand = game.getHand();
    HandValue greatestHandValue = hand.getValue();

    Card cardInDeck = deck.glanceFirst(1).get(0);
    for (int cardIndex = 0; cardIndex < Hand.CARDS_IN_HAND && !greatestHandValue.isTheGreatestHandValueEver(); cardIndex++) {
      Card cardInHand = hand.getCard(cardIndex);
      HandValue newHandValue = hand.replace(cardInHand).with(cardInDeck).getValue();
      if (newHandValue.isGreaterThan(greatestHandValue)) {
        greatestHandValue = newHandValue;
      }
    }

    List<Card> deckFirstTwoCards = deck.glanceFirst(2);
    for (int cardIndex = 0; cardIndex < Hand.CARDS_IN_HAND && !greatestHandValue.isTheGreatestHandValueEver(); cardIndex++) {
      Card cardInHand = hand.getCard(cardIndex);
      HandValue newHandValue = hand.replace(cardInHand).with(cardInDeck).getValue();
      if (newHandValue.isGreaterThan(greatestHandValue)) {
        greatestHandValue = newHandValue;
      }
    }

    return greatestHandValue;
  }
}
