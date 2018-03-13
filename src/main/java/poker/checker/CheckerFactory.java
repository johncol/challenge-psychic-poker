package poker.checker;

public class CheckerFactory {

  public static HandValueChecker forHighestCard() {
    return new HighestCardChecker();
  }

  public static FaceValueRepetitionsChecker forOnePair() {
    return new OnePairChecker();
  }

  public static HandValueChecker forTwoPairs() {
    return new TwoPairsChecker();
  }

  public static FaceValueRepetitionsChecker forThreeOfAKind() {
    return new ThreeOfAKindChecker();
  }

  public static HandValueChecker forStraight() {
    return new StraightChecker();
  }

  public static HandValueChecker forFlush() {
    return new FlushChecker();
  }

  public static HandValueChecker forFullHouse() {
    return new FullHouseChecker();
  }

  public static FaceValueRepetitionsChecker forFourOfAKind() {
    return new FourOfAKindChecker();
  }

  public static HandValueChecker forStraightFlush() {
    return new StraightFlushChecker();
  }
}
