package poker.checker;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ThreeOfAKindChecker extends FaceValueRepetitionsChecker {

  public static final int CARD_REPETITIONS_FOR_THREE_OF_A_KIND = 3;

  @Override
  protected int minimumAmountOfFaceValueRepetitions() {
    return CARD_REPETITIONS_FOR_THREE_OF_A_KIND;
  }
}
