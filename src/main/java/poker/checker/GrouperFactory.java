package poker.checker;

import poker.checker.api.FaceValueGrouper;

public class GrouperFactory {

  public static FaceValueGrouper forPairs() {
    return new OnePairChecker();
  }

  public static FaceValueGrouper forThreeOfAKind() {
    return new ThreeOfAKindChecker();
  }

  public static FaceValueGrouper forFourOfAKind() {
    return new FourOfAKindChecker();
  }

}
