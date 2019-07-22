import java.util.Timer;
import java.util.TimerTask;

public class Monkey implements Runnable {

  // AF:
  // ID + moving direction + velocity + ladder to climb on -> Monkey.
  // RI:
  // direction should be either "L->R" or "R->L".
  // velocity(v) > 0.
  // Safety from rep exposure:
  // All variables are set to private and only mutators can change them.
  
  private Thread t;

  private int ID;
  private String direction;
  private int v;
  Ladder ladder;

  private int position = 0;
  private int line = 99; // Should be assigned in the StrategyAdd.
  
  private long destinationTime;
  private long startTime;

  private boolean flag = true;
  
  public Monkey(int ID, String direction, int v, Ladder ladder) {
    this.ID = ID;
    this.direction = new String(direction);
    this.v = v;
    this.ladder = ladder;
    if (!getDirection()) {
      position = ladder.getLength() - 1;
    }
    setStartTime(System.currentTimeMillis());
  }

  /**
   * Get the direction in boolean form.
   *
   * @return true represents L->R while false represents R->L.
   */
  public boolean getDirection() {
    if (direction.equals("L->R")) {
      return true;
    }
    return false;
  }

  /**
   * Update the position of the monkey and the ladder.
   *
   * @param the ladder monkey is climbing.
   */
  public synchronized void update() {
    if (getDirection()) {
      if (position < ladder.getLength()) {
        int preposition = position;
        this.position = ladder.upgradePosition(line, position, position + v); 
        System.out.println("Monkey " + ID + " moved from " + preposition + " to " + position);
        RiverGUI.textArea.append("Monkey " + ID + " moved from " + preposition + " to " + position + "\n");
      } else {
        if (flag) {
          setDestinationTime(System.currentTimeMillis());
          flag = false;
        }
      }
    } else {
      if (position > -1) {
        int preposition = position;
        this.position = ladder.upgradePosition(line, position, position - v);
        System.out.println("Monkey " + ID + " moved from " + preposition + " to " + position);
        RiverGUI.textArea.append("Monkey " + ID + " moved from " + preposition + " to " + position + "\n");
      } else {
        if (flag) {
          setDestinationTime(System.currentTimeMillis());
          flag = false;
        }
      }
    }
  }

  @Override
  public void run() { // TODO Auto-generated method stub
    TimerTask task =
        new TimerTask() {
          @Override
          public void run() {
            update();
          }
        };
    new Timer().schedule(task, 0, 1000);
  }

  /**
   * Start the monkey thread.
   */
  public void start() {
    System.out.println("Start the thread Monkey " + ID);
    RiverGUI.textArea.append("Start the thread Monkey " + ID + "\n");
    if (t == null) {
      t = new Thread(this, String.valueOf(ID));
      t.start();
    }
  }

  /**
   * Output the data.
   */
  public String toString() {
    return "Monkey " + ID + " " + direction + " Speed " + v + " line " + line;
  }

  /**
   * Get the speed of monkey.
   * @return speed.
   */
  public int getSpeed() {
    return this.v;
  }

  /**
   * Get the position of monkey.
   * @return position.
   */
  public int getPosition() {
    return this.position;
  }

  /**
   * Assign the monkey to a specific line.
   * @param line to assign.
   */
  public void assignLine(int line) {
    this.line = line;
  }

  /**
   * Get the ID of monkey.
   * @return ID.
   */
  public int getID() {
    return this.ID;
  }
  
  /**
   * Set the time when monkey arrives at the destination.
   * @param destinationTime.
   */
  public void setDestinationTime(long destinationTime) {
    this.destinationTime = destinationTime;
  }
  
  /**
   * Get the time when monkey arrives at the destination.
   * @return destination time.
   */
  public long getDestinationTime() {
    return this.destinationTime;
  }
  
  /**
   * Set the time when monkey starts.
   * @param startTime.
   */
  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }
  
  /**
   * Get the start time.
   * @return start time.
   */
  public long getStartTime() {
    return this.startTime;
  }
}
