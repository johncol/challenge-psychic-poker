package poker.checker;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class OnePairChecker extends FaceValueRepetitionsChecker {

  public static final int CARD_REPETITIONS_FOR_ONE_PAIR = 2;

  @Override
  protected int minimumAmountOfFaceValueRepetitions() {
    return CARD_REPETITIONS_FOR_ONE_PAIR;
  }
}
