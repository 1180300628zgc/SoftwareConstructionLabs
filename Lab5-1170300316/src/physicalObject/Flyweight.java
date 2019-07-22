package physicalObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface Flyweight {

  public int GetLevel();

  public void SetLevel(int level);

  public void Init(double degree, int radius, JPanel panel);
}
