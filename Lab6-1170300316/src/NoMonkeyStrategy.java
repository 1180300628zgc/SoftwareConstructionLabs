/**
 * 优先选择没有猴子的梯子，若所有梯子上都有猴子，则优先选择没有与我对向而行的猴子的梯子; 若满足该条件的梯子有很多，则选择找到的第一行;
 *
 * @author tony
 */
public class NoMonkeyStrategy implements Strategy {

  @Override
  public boolean choose(Monkey monkey, Ladder ladder) {
    while (true) {
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
      for (int i = 0; i < ladder.getNumber(); i++) {
        if (monkey.getDirection() == ladder.directionOfLine(i)) {
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
