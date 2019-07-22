/**
 * Use StrategyAdd to implement different strategy by
 *
 * @author tony
 */
public class StrategyAdd {
  private Strategy strategy;

  public StrategyAdd(Strategy strategy) {
    this.strategy = strategy;
  }

  public boolean executeStrategy(Monkey monkey, Ladder ladder) {
    return strategy.choose(monkey, ladder);
  }
}
