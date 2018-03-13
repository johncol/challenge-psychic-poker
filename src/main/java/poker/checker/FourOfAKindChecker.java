package poker.checker;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class FourOfAKindChecker extends RepeatableFaceValueChecker {

  public static final int CARD_REPETITIONS_FOR_FOUR_OF_A_KIND = 4;

  @Override
  protected int amountOfFaceValueRepetitions() {
    return CARD_REPETITIONS_FOR_FOUR_OF_A_KIND;
  }
}
