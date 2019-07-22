import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;

public class RiverCrossingSim {

  private int n, h, t, N, k, MV;
  private Ladder ladder;
  private MonkeyGenerator monkeyGenerator;

  private int totalJust = 0;
  private int totalNum = 0;
  
  private FileReader fileReader;
  /**
   * Initialize the simulator with the input from GUI.
   *
   * @param n same ladders.
   * @param h is the length of each ladder.
   * @param t is the interval of generating two * k monkeys.
   * @param N is the total number of monkeys.
   * @param k is the number of monkeys generated each time.
   * @param MV is the largest possible speed of monkey.
   */
  public RiverCrossingSim(int n, int h, int t, int N, int k, int MV, boolean flag) {
    this.n = n;
    this.h = h;
    this.t = t;
    this.N = N;
    this.k = k;
    this.MV = MV;
    
    ladder = new Ladder(n, h);

    int totalJust = 0;
    int totalNum = 0;
    
    monkeyGenerator = new MonkeyGenerator(t, k, N, MV, ladder, flag);

    monkeyGenerator.setStartTime(System.currentTimeMillis());
    while (true) {
      if (RiverGUI.textArea.getText().contains("Arrived Monkeys " + String.valueOf(N))) {
        monkeyGenerator.setTerminationTime(System.currentTimeMillis());
        for (Monkey monkey : ladder.getArrivedMonkey()) {
          for (Monkey monkey2 : ladder.getArrivedMonkey()) {
            if (monkey != null && monkey2 != null) {
              if (monkey.getID() < monkey2.getID()
                  && monkey.getDestinationTime() < monkey2.getDestinationTime()) {
                totalJust++;
                // System.out.println(monkey.getStartTime() + " " + monkey2.getStartTime() + "right");
              }
              if (monkey.getID() < monkey2.getID()
                  && monkey.getDestinationTime() > monkey2.getDestinationTime()) {
                totalJust--;
                // System.out.println("right");
              }
            }
          }
        }
        break;
      }
    }
    // System.out.println(totalJust + " " + totalNum);
    totalNum = N;
    System.out.println(N + " " + monkeyGenerator.getTerminationTime());
    RiverGUI.textArea_1.setText(String.valueOf((double)N * 1000 / monkeyGenerator.getTerminationTime()) + "ms/monkey");
    RiverGUI.textArea_2.setText(String.valueOf(2 * (double)totalJust / ((double)totalNum * ((double)totalNum - 1))));
  }

  public RiverCrossingSim(int n, int h, int t, int N, int k, int MV, boolean flag, FileReader fileReader) {
    this.n = n;
    this.h = h;
    this.t = t;
    this.N = N;
    this.k = k;
    this.MV = MV;

    this.fileReader = fileReader;
        
    ladder = new Ladder(n, h);

    int totalJust = 0;
    int totalNum = 0;
    
    monkeyGenerator = new MonkeyGenerator(t, k, N, MV, ladder, flag, fileReader);

    monkeyGenerator.setStartTime(System.currentTimeMillis());
    while (true) {
      if (RiverGUI.textArea.getText().contains("Arrived Monkeys " + String.valueOf(N))) {
        monkeyGenerator.setTerminationTime(System.currentTimeMillis());
        for (Monkey monkey : ladder.getArrivedMonkey()) {
          for (Monkey monkey2 : ladder.getArrivedMonkey()) {
            if (monkey != null && monkey2 != null) {
              if (monkey.getID() < monkey2.getID()
                  && monkey.getDestinationTime() < monkey2.getDestinationTime()) {
                totalJust++;
                // System.out.println(monkey.getStartTime() + " " + monkey2.getStartTime() + "right");
              }
              if (monkey.getID() < monkey2.getID()
                  && monkey.getDestinationTime() > monkey2.getDestinationTime()) {
                totalJust--;
                // System.out.println("right");
              }
            }
          }
        }
        break;
      }
    }
    // System.out.println(totalJust + " " + totalNum);
    totalNum = N;
    System.out.println(N + " " + monkeyGenerator.getTerminationTime());
    RiverGUI.textArea_1.setText(String.valueOf((double)N * 1000 / monkeyGenerator.getTerminationTime()) + "ms/monkey");
    RiverGUI.textArea_2.setText(String.valueOf(2 * (double)totalJust / ((double)totalNum * ((double)totalNum - 1))));
  }
  
  public static void main(String[] args) {
    RiverCrossingSim sim = new RiverCrossingSim(5, 20, 5, 23, 5, 5, true);
    TimerTask task =
        new TimerTask() {

          @Override
          public void run() {
            System.out.println("Final " + sim.ladder.getArrivedMonkey().size());
          }
        };

    new Timer().schedule(task, 60 * 1000);
  }
}
