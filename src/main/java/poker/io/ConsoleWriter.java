package poker.io;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import poker.io.ConsoleWriter.Input;

public class ConsoleWriter implements Writer<Input> {

  public static final String MESSAGE_FORMAT = "Hand: %s Deck: %s Best hand: %s";

  @Override
  public void write(Input input) {
    int middle = input.cards.length() / 2;
    String inHand = input.cards.substring(0, middle).trim();
    String inDeck = input.cards.substring(middle).trim();
    String message = String.format(MESSAGE_FORMAT, inHand, inDeck, input.handValue);
    System.out.println(message);
  }

  @AllArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Input {

    private final String cards;
    private final String handValue;

    public static Input of(String cards, String handValue) {
      return new Input(cards, handValue);
    }
  }

}
