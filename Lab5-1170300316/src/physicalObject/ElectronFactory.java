package physicalObject;

import java.util.HashMap;

public class ElectronFactory {
  private static final HashMap<Integer, Flyweight> electronMap = new HashMap<>();

  public static Flyweight getElectron(int level) {
    ElectronFW electronFW = (ElectronFW) electronMap.get(level);

    if (electronFW == null) {
      electronFW = new ElectronFW(level);
      electronMap.put(level, electronFW);
    }
    return electronFW;
  }
}
