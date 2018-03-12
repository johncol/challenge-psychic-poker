package poker.domain.card;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SuitSpecification {

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenCharacterPassedIsNotValid() {
    char unknownCharacter = 'X';

    Suit.of(unknownCharacter);
  }

  @Test
  public void shouldReturnSuitWhenCharacterPassedIsValid() {
    char diamondsCharacter = Suit.DIAMONDS.getValue();

    Suit diamondsSuit = Suit.of(diamondsCharacter);

    assertThat(diamondsSuit, is(Suit.DIAMONDS));
  }

}
