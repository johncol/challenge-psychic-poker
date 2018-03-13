package poker;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.paukov.combinatorics3.Generator;
import poker.domain.Deck;
import poker.domain.Hand;
import poker.domain.HandValue;
import poker.domain.PokerGame;
import poker.domain.card.Card;
import poker.io.PokerParser;

@AllArgsConstructor
public class PokerPsychic {

  private final PokerParser pokerParser;

  public String usePsychicSight(String cardsInHandAndInDeck) {
    PokerGame game = pokerParser.parse(cardsInHandAndInDeck);
    HandValue bestHandValue = useSight(game);
    return bestHandValue.toString();
  }

  private HandValue useSight(PokerGame game) {
    Deck deck = game.getDeck();
    Hand hand = game.getHand();

    return Stream.iterate(0, cardsToTake -> cardsToTake + 1)
        .limit(Deck.CARDS_IN_DECK + 1)
        .flatMap(cardsToTakeFromDeck -> Generator.combination(hand.getCards())
            .simple(Hand.CARDS_IN_HAND - cardsToTakeFromDeck)
            .stream()
            .map(handCards -> addCardsFromDeck(handCards, deck, cardsToTakeFromDeck))
            .map(HandValue::of))
        .distinct()
        .sorted(Comparator.reverseOrder())
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  private List<Card> addCardsFromDeck(List<Card> cards, Deck deck, int amount) {
    List<Card> cardsFromDeck = deck.glanceFirst(amount);
    cards.addAll(cardsFromDeck);
    return cards;
  }

}
