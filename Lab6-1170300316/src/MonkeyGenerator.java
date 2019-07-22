import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MonkeyGenerator {
  private int t; // Time interval.
  private int k; // Generate k monkeys each time.
  private int N; // The total number of monkeys to generate.
  private int maxV; // The largest possible speed of monkeys.
  private int currentN;
  private ArrayList<Monkey> monkeys = new ArrayList<>();
  private int remain;
  private Ladder ladder;

  private long startTime = 0;
  private long terminationTime = 0;
  
  private boolean strategySwitch;
  // private StrategyAdd strategyAdd;
  private FileReader fileReader;
  
  public MonkeyGenerator(int t, int k, int N, int maxV, Ladder ladder, boolean flag) {
    this.t = t;
    this.k = k;
    this.N = N;
    this.remain = N;
    this.maxV = maxV;
    this.currentN = 0;
    this.ladder = ladder;
    this.strategySwitch = flag;
    // strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
    generate();
  }

  public MonkeyGenerator(int t, int k, int N, int maxV, Ladder ladder, boolean flag, FileReader fileReader) {
    this.t = t;
    this.k = k;
    this.N = N;
    this.remain = N;
    this.maxV = maxV;
    this.currentN = 0;
    this.ladder = ladder;
    this.strategySwitch = flag;
    this.fileReader = fileReader;
    // strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
    generate();
  }
  
  public ArrayList<Monkey> getGeneratedMonkeys() {
    return monkeys;
  }
  
  /** Generate monkeys at a certain time period. */
  public void generate() {
    TimerTask task =
        new TimerTask() {
          @Override
          public void run() {
            if (remain > 0) {
              operate();
            }
          }
        };
    new Timer().schedule(task, 0, t * 1000);
  }

  /**
   * Implement operations on the remaining monkeys.
   *
   */
  private void operate() {
    if (remain > k) {
      for (int i = 0; i < k; i++) {
        System.out.println("yes1");
        monkeys.add(new Monkey(currentN, fileReader.getDirection(currentN), fileReader.getSpeed(currentN), ladder));
        StrategyAdd strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
        strategyAdd.executeStrategy(monkeys.get(currentN), ladder); 
        monkeys.get(currentN).start();
        currentN ++;
        /*
        if ((int) (Math.random() * 2) == 1) {
          monkeys.add(new Monkey(currentN, "L->R", 1 + (int) (Math.random() * maxV), ladder));
          if (strategySwitch) {
            StrategyAdd strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
            strategyAdd.executeStrategy(monkeys.get(currentN), ladder); 
          } else {
            StrategyAdd strategyAdd = new StrategyAdd(new EmptyLadderStrategy());
            strategyAdd.executeStrategy(monkeys.get(currentN), ladder); 
          }
          // strategyAdd.executeStrategy(monkeys.get(currentN), ladder);   
          monkeys.get(currentN).start();
        } else {
          monkeys.add(new Monkey(currentN, "R->L", 1 + (int) (Math.random() * maxV), ladder));
          if (strategySwitch) {
            StrategyAdd strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
            strategyAdd.executeStrategy(monkeys.get(currentN), ladder); 
          } else {
            StrategyAdd strategyAdd = new StrategyAdd(new EmptyLadderStrategy());
            strategyAdd.executeStrategy(monkeys.get(currentN), ladder); 
          }
          // strategyAdd.executeStrategy(monkeys.get(currentN), ladder); 
          monkeys.get(currentN).start();
        }
        currentN++;
        */
      }
      
    } else {
      for (int i = currentN; i < N; i++) {
        System.out.println("yes2");
        monkeys.add(new Monkey(i, fileReader.getDirection(i), fileReader.getSpeed(i), ladder));
        StrategyAdd strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
        strategyAdd.executeStrategy(monkeys.get(i), ladder); 
        monkeys.get(i).start();
        /*
        if ((int) (Math.random() * 2) == 1) {
          monkeys.add(new Monkey(i, "L->R", 1 + (int) (Math.random() * maxV), ladder));
          if (strategySwitch) {
            StrategyAdd strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
            strategyAdd.executeStrategy(monkeys.get(i), ladder); 
          } else {
            StrategyAdd strategyAdd = new StrategyAdd(new EmptyLadderStrategy());
            strategyAdd.executeStrategy(monkeys.get(i), ladder); 
          }
          // strategyAdd.executeStrategy(monkeys.get(i), ladder);
          monkeys.get(i).start();
        } else {
          monkeys.add(new Monkey(i, "R->L", 1 + (int) (Math.random() * maxV), ladder));
          if (strategySwitch) {
            StrategyAdd strategyAdd = new StrategyAdd(new NoMonkeyStrategy());
            strategyAdd.executeStrategy(monkeys.get(i), ladder); 
          } else {
            StrategyAdd strategyAdd = new StrategyAdd(new EmptyLadderStrategy());
            strategyAdd.executeStrategy(monkeys.get(i), ladder); 
          }
          // strategyAdd.executeStrategy(monkeys.get(i), ladder);
          monkeys.get(i).start();
        }
        */
      }
    }
    remain -= k;
    System.out.println(remain);
    RiverGUI.textArea.append("Remains " + remain + "\n");
  }

  public static void main(String[] args) {
    MonkeyGenerator monkeyGenerator = new MonkeyGenerator(1, 5, 23, 5, new Ladder(4, 6), true);
    monkeyGenerator.generate();
  }
  
  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }
  
  public void setTerminationTime(long terminationTime) {
    this.terminationTime = terminationTime;
  }
  
  public long getTerminationTime() {
    return terminationTime - startTime;
  }
  
  public int setStrategy(boolean flag) {
    if (flag) {
      return 1;
    } else {
      return 2;
    }
  }
}
