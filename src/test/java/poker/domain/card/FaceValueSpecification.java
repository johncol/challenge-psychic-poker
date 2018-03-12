package poker.domain.card;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FaceValueSpecification {

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenCharacterPassedIsNotValid() {
    char unknownCharacter = 'X';

    FaceValue.of(unknownCharacter);
  }

  @Test
  public void shouldReturnFaceValueWhenCharacterPassedIsValid() {
    char queenCharacter = FaceValue.QUEEN.getValue();

    FaceValue queenFaceValue = FaceValue.of(queenCharacter);

    assertThat(queenFaceValue, is(FaceValue.QUEEN));
  }
}
