/**
 * 在同向的 Ladder 中选择速度差与该猴子速度差最小的梯子.
 *
 * @author tony
 */
public class EmptyLadderStrategy implements Strategy {
  @Override
  public boolean choose(Monkey monkey, Ladder ladder) {
    while(true) {
      for (int i = 0; i < ladder.getNumber(); i++) {
        if (ladder.isEmpty(i)) {
          if (ladder.addMonkey(monkey, i)) {
            monkey.assignLine(i);
            System.out.println(monkey.toString());
            RiverGUI.textArea.append(monkey.toString() + "\n");
            return true;
          }
        }
      }
    }
  }
}
