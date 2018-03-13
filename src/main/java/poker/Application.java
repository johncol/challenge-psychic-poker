package poker;

import java.util.List;
import poker.io.ConsoleWriter;
import poker.io.ConsoleWriter.Input;
import poker.io.Parser;
import poker.io.PlainTextFileReader;
import poker.io.StringBasedParser;
import poker.psychic.PokerPsychic;

public class Application {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Plain text file with cards input is required to run the application");
      return;
    }

    String filePath = args[0];
    PlainTextFileReader reader = new PlainTextFileReader(filePath);
    ConsoleWriter writer = new ConsoleWriter();
    Parser<String> parser = new StringBasedParser();
    PokerPsychic psychic = new PokerPsychic(parser);

    List<String> games = reader.read();
    games.forEach(cardsInHandAndInDeck -> {
      String handValue = psychic.usePsychicSight(cardsInHandAndInDeck);
      Input input = Input.of(cardsInHandAndInDeck, handValue);
      writer.write(input);
    });
  }

}
