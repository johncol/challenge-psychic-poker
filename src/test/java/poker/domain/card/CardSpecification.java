package poker.domain.card;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CardSpecification {

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTryingToBuildCardFromNullFaceValue() {
    FaceValue nullFaceValue = null;

    Card.of(nullFaceValue, Suit.HEARTS);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTryingToBuildCardFromNullSuit() {
    Suit nullSuit = null;

    Card.of(FaceValue.ACE, nullSuit);
  }

  @Test
  public void shouldBuildCardFromFaceValueAndSuit() {
    Card AH = Card.of(FaceValue.ACE, Suit.HEARTS);

    assertThat(AH.getFaceValue(), is(equalTo(FaceValue.ACE)));
    assertThat(AH.getSuit(), is(equalTo(Suit.HEARTS)));
  }

  @Test
  public void shouldReturnCardStringRepresentationAsATwoCharactersString() {
    Card AH = Card.of(FaceValue.ACE, Suit.HEARTS);

    assertThat(AH.toString(), is(equalTo("AH")));
  }

}
