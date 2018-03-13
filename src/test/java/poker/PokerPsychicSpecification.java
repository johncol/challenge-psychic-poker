package poker;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import poker.io.PokerParser;

public class PokerPsychicSpecification {

  private final PokerParser parser = new PokerParser();

  private final PokerPsychic psychic = new PokerPsychic(parser);

  @Test
  public void shouldReturnBestHandIsStraightFlush() {
    String bestHand = psychic.usePsychicSight("TH JH QC QD QS QH KH AH 2S 6S");
    assertThat(bestHand, is("straight-flush"));
  }

  @Test
  public void shouldReturnBestHandIsFourOfAKind() {
    String bestHand = psychic.usePsychicSight("2H 2S 3H 3S 3C 2D 3D 6C 9C TH");
    assertThat(bestHand, is("four-of-a-kind"));
  }

  @Test
  public void shouldReturnBestHandIsFullHouse() {
    String bestHand = psychic.usePsychicSight("2H 2S 3H 3S 3C 2D 9C 3D 6C TH");
    assertThat(bestHand, is("full-house"));
  }

  @Test
  public void shouldReturnBestHandIsFlush() {
    String bestHand = psychic.usePsychicSight("2H AD 5H AC 7H AH 6H 9H 4H 3C");
    assertThat(bestHand, is("flush"));
  }

  @Test
  public void shouldReturnBestHandIsStraight() {
    String bestHand = psychic.usePsychicSight("AC 2D 9C 3S KD 5S 4D KS AS 4C");
    assertThat(bestHand, is("straight"));
  }

  @Test
  public void shouldReturnBestHandIsThreeOfAKind() {
    String bestHand = psychic.usePsychicSight("KS AH 2H 3C 4H KC 2C TC 2D AS");
    assertThat(bestHand, is("three-of-a-kind"));
  }

  @Test
  public void shouldReturnBestHandIsTwoPairs() {
    String bestHand = psychic.usePsychicSight("AH 2C 9S AD 3C QH KS JS JD KD");
    assertThat(bestHand, is("two-pairs"));
  }

  @Test
  public void shouldReturnBestHandIsOnePair() {
    String bestHand = psychic.usePsychicSight("6C 9C 8C 2D 7C 2H TC 4C 9S AH");
    assertThat(bestHand, is("one-pair"));
  }

  @Test
  public void shouldReturnBestHandIsHighestCard() {
    String bestHand = psychic.usePsychicSight("3D 5S 2H QD TD 6S KH 9H AD QH");
    assertThat(bestHand, is("highest-card"));
  }

}
