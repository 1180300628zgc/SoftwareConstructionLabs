package physicalObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ElectronFW implements Flyweight {

  private int level;

  public ElectronFW(int level) {
    this.level = level;
  }

  @Override
  public int GetLevel() {
    return level;
  }

  @Override
  public void SetLevel(int level) {
    this.level = level;
  }

  @Override
  public void Init(double degree, int radius, JPanel panel) {
    JLabel electron = new JLabel("New label");
    electron.setIcon(new ImageIcon("lib/AtomStructure/electron.png"));
    // planet.setBounds(555, 70, 60, 60);

    electron.setBounds(
        (int) (radius * Math.cos(degree * Math.PI / 180)) - 30 + 480,
        (int) (radius * Math.sin(degree * Math.PI / 180)) - 30 + 320,
        60,
        60);
    panel.add(electron);

    System.out.println("New electron INITIATED");
  }
}
