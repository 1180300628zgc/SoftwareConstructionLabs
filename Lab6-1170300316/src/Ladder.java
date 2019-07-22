import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Ladder {
  
  // AF:
  // length + number of rungs + monkeys on ladders + lock of each ladder -> Ladder
  // RI:
  // h > 0, n > 0 
  // Safety from rep exposure:
  // All variables are set to private and can be only accessed by mutators.
  
  private int h; // Length of the ladder.
  private int n; // Number of the ladders.
  private Monkey[][] LadderGrid; // To verify if a specific Rung is available.
  private Object[] lock; // h lock for n ladders.

  private List<Monkey> destinationList;

  public Ladder(int n, int h) {
    this.h = h;
    this.n = n;
    lock = new Object[n];
    for (int i = 0; i < n; i++) {
      lock[i] = new Object();
    }

    LadderGrid = new Monkey[n][h];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < h; j++) {
        LadderGrid[i][j] = null;
      }
    }
    
    destinationList = Collections.synchronizedList(new ArrayList<Monkey>());
  }

  public synchronized Monkey[] getLine(int line) {
    return LadderGrid[line];
  }

  public int upgradePosition(int line, int originalPos, int destinyPos) {
    synchronized (lock[line]) {
      if (destinyPos > originalPos) {
        if (destinyPos > h - 1) {
          for (int i = originalPos + 1; i < h; i++) {
            if (LadderGrid[line][i] != null) {
              if (originalPos != (i - 1)) {
                LadderGrid[line][i - 1] = LadderGrid[line][originalPos];
                LadderGrid[line][originalPos] = null;
                return i - 1;
              } else {
                return i - 1;
              }
            }
          }
          destinationList.add(LadderGrid[line][originalPos]);
          LadderGrid[line][originalPos] = null;
          System.out.println("Arrived Monkeys " + destinationList.size());
          RiverGUI.textArea.append("Arrived Monkeys " + destinationList.size() + "\n");
          return h;
        }

        for (int i = originalPos + 1; i <= destinyPos; i++) {
          if (LadderGrid[line][i] != null) {
            LadderGrid[line][i - 1] = LadderGrid[line][originalPos];
            LadderGrid[line][originalPos] = null;
            return i - 1;
          }
        }

        LadderGrid[line][destinyPos] = LadderGrid[line][originalPos];
        LadderGrid[line][originalPos] = null;
        return destinyPos;
      } else {
        // The condition that R->L
        if (destinyPos < 0) {
          for (int i = originalPos - 1; i >= 0; i--) {
            if (LadderGrid[line][i] != null) {
              if (originalPos == (i + 1)) {
                LadderGrid[line][i + 1] = LadderGrid[line][originalPos];
                LadderGrid[line][originalPos] = null;
                return i + 1;
              } else {
                return i + 1;
              }
            }
          }

          destinationList.add(LadderGrid[line][originalPos]);
          LadderGrid[line][originalPos] = null;
          System.out.println("Arrived Monkeys " + destinationList.size());
          RiverGUI.textArea.append("Arrived Monkeys " + destinationList.size() + "\n");
          return -1;
        }

        for (int i = originalPos - 1; i >= destinyPos; i--) {
          if (LadderGrid[line][i] != null) {
            LadderGrid[line][originalPos] = null;
            return i + 1;
          }
        }

        LadderGrid[line][destinyPos] = LadderGrid[line][originalPos];
        LadderGrid[line][originalPos] = null;
        return destinyPos;
      }
    }
  }

  /**
   * Get the length of the LadderGrid.
   *
   * @return the length of the LadderGrid.
   */
  public int getLength() {
    return this.h;
  }

  /**
   * Get the number of the Ladders.
   *
   * @return the number of the Ladders.
   */
  public int getNumber() {
    return this.n;
  }

  /**
   * Decide whether a line in LadderGrid is empty with no monkey on it.
   *
   * @param line = real line - 1.
   * @return true if it is empty and false if there are one or more monkeys.
   */
  public boolean isEmpty(int line) {
    synchronized (lock[line]) {
      for (Monkey monkey : LadderGrid[line]) {
        if (monkey != null) {
          return false;
        }
      }
      return true;
    }
  }

  // BUG. NEED FIXING.
  /**
   * Add the monkey to line and put it to the correct beginning according to its direction.
   *
   * @param monkey to add.
   * @param line to choose.
   */
  public boolean addMonkey(Monkey monkey, int line) {
    synchronized (lock[line]) {
      if (monkey.getDirection()) {
        if (LadderGrid[line][0] == null) {
          LadderGrid[line][0] = monkey;
          return true;
        } else {
          return false;
        }
      } else {
        if (LadderGrid[line][h - 1] == null) {
          LadderGrid[line][h - 1] = monkey;
          return true;
        } else {
          return false;
        }
      }
    }
  }

  /**
   * Get the direction of monkeys on a specific ladder. No two monkeys of different directions show
   * up on the same ladder.
   *
   * @param line to look up.
   * @return true if it is L->R, false if it is R->L.
   */
  public synchronized boolean directionOfLine(int line) {
    for (Monkey monkey : LadderGrid[line]) {
      if (monkey != null) {
        if (monkey.getDirection()) {
          return true;
        } else {
          return false;
        }
      }
    }
    return true;
  }
  
  public List<Monkey> getArrivedMonkey() {
    return destinationList;
  }
}
