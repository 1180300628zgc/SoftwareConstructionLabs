package APIs;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.junit.jupiter.api.Test;

import java.nio.*;

import centralObject.CentralObject;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;
import circularOrbit.ConcreteCircularOrbit;
import circularOrbit.StellarSystem;
import extensions.Position;
import physicalObject.Planet;
import track.Track;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.util.*;

public class StellarSystemVisual extends JFrame {

  // Control the total number of tracks in the System.
  private int totalCtrl;
  public JPanel contentPane;
  public static CircularOrbit<CentralObject, Track> orbit = CircularOrbit.emptyStellarSystem();

  // wait for implement
  // JLabel planet = new JLabel("New label");

  // -------//
  MovingOBJ mov;
  // -------//

  boolean run = true;

  /** Launch the application. */
  public static void main(String[] args) {
    // writeFileByLines("src/input/OutputStellarSystemBig.txt");
    // -----//
    MovingOBJ mov = new MovingOBJ();
    // -----//

    Parse();

    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            try {
              StellarSystemVisual frame = new StellarSystemVisual(mov);
              // ----------//
              mov.setPanel(frame);
              // ----------//
              frame.setVisible(true);

            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });

    // Parse() was here
  }

  /** Create the frame. */
  public StellarSystemVisual(MovingOBJ mov) {

    setTitle("Stellar System");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 960, 640);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    orbit.Init(contentPane);

    // The Label of the Sun
    JLabel sun = new JLabel("New label");
    sun.setIcon(new ImageIcon("lib/StellarSystem/sun.png"));
    sun.setBounds(430, 270, 100, 100);
    contentPane.add(sun);

    JComboBox comboBox = new JComboBox();
    for (Track track : orbit.GetTrack()) {
      comboBox.addItem(track.GetName());
    }
    comboBox.setBounds(39, 22, 120, 27);
    contentPane.add(comboBox);

    JComboBox comboBox_1 = new JComboBox();
    for (Track track : orbit.GetTrack()) {
      comboBox_1.addItem(track.GetName());
    }
    comboBox_1.setBounds(39, 61, 120, 27);
    contentPane.add(comboBox_1);

    JButton btnNewButton = new JButton("Calculate");
    btnNewButton.setBounds(39, 100, 120, 29);
    contentPane.add(btnNewButton);

    Position position = new Position(0, 0);
    Position position_1 = new Position(0, 0);

    JTextPane textPane = new JTextPane();
    textPane.setBounds(39, 141, 120, 27);
    contentPane.add(textPane);

    btnNewButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            for (Track track : orbit.GetTrack()) {
              if (track.GetName().equals(comboBox.getSelectedItem())) {
                for (Planet planet : track.getPhysicalObjects()) {
                  position.SetPosition(planet.GetPosition());
                }
              }
              if (track.GetName().equals(comboBox_1.getSelectedItem())) {
                for (Planet planet : track.getPhysicalObjects()) {
                  position_1.SetPosition(planet.GetPosition());
                }
              }
            }
            // System.out.println("Click " + position.GetDegree());
            // System.out.println("Click2 " + position_1.GetDegree());
            textPane.setText(
                String.valueOf(
                    Math.sqrt(
                        position.GetRadius() * position.GetRadius()
                            + position_1.GetRadius() * position_1.GetRadius()
                            - 2
                                * position.GetRadius()
                                * position_1.GetRadius()
                                * Math.cos(
                                    (position.GetDegree() - position_1.GetDegree())
                                        * Math.PI
                                        / 180))));
          }
        });

    // -----------//

    this.mov = mov;
    mov.start();
    // -----------//

    /*
     * // The Label of the background JLabel bg = new JLabel("New label");
     * bg.setIcon(new ImageIcon(
     * "/Users/tony/Documents/Software Construction/Handin/Lab3-1170300316/lib/StellarSystem/universe.png"
     * )); bg.setBounds(0, 0, 960, 640); contentPane.add(bg);
     */
  }

  /** Assign the reading path */
  public static void Parse() {
    String file1 = "src/input/StellarSystemBig.txt";
    // All of the following three methods are available.
    // readFileByLines(file1);
    // readFileByBufferedReader(file1);
    readFileByLines(file1);
    String file2 = "src/input/OutputStellarSystemBig4.txt";
    // writeFileByLines(file2);
    // writeFileByReadAllBytes(file2);
    // writeFileByBufferedReader(file2);
    writeFileByBufferedReader(file2);
  }

  /**
   * Read the file from the given string using the buffered reader. 使用缓冲流 I/O 读取文件
   *
   * @param fileName
   */
  private static void readFileByBufferedReader(String fileName) {

    Planet[] planet = new Planet[330000];
    Charset charset = Charset.forName("UTF-8");
    Path path = Path.of(fileName);
    try {
    	long startTime = System.currentTimeMillis();
      BufferedReader reader = Files.newBufferedReader(path, charset);
      String tempString = null;
      int line = 1;

      while ((tempString = reader.readLine()) != null) {
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          // System.out.println(tempString);
          // System.out.println("line " + line + ":" + tempString);
          if (tempString.startsWith("Stellar")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              // Add to THE STELLAR
              Stellar stellar =
                  new Stellar(m.group(1), Double.valueOf(m.group(2)), Double.valueOf(m.group(3)));

              orbit.AddObjectToCentre(stellar);
            }
            // System.out.println("Found value: " + m.group(0));
          }

          if (tempString.startsWith("Planet")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              // Add to planet
              planet[line] =
                  new Planet(
                      m.group(1),
                      m.group(2),
                      m.group(3),
                      Double.valueOf(m.group(4)),
                      Double.valueOf(m.group(5)),
                      Double.valueOf(m.group(6)),
                      m.group(7),
                      Double.valueOf(m.group(8)));

              orbit.AddObjectToTrack(planet[line]);
            }
          }
          line++;
        }
      }
      // System.out.println("All " + line + " lines has been read.");
      long stopTime = System.currentTimeMillis();
      System.out.println("readFileByBufferedReader " + (stopTime - startTime));
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Read files by implementing readAllLines(Path, Charset).
   *
   * @param fileName is the path of the file.
   */
  private static void readFileByReadAllBytes(String fileName) {
    Path file = Path.of(fileName);
    Planet[] planet = new Planet[330000];

    long startTime = System.currentTimeMillis();
    
    java.util.List<String> stringList = new ArrayList();
    try {
      stringList = Files.readAllLines(file);
      for (int i = 0; i < stringList.size(); i++) {
        String tempString = stringList.get(i);
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          // System.out.println(tempString);
          // System.out.println("line " + (i + 1) + ":" + tempString);
          if (tempString.startsWith("Stellar")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              // Add to THE STELLAR
              Stellar stellar =
                  new Stellar(m.group(1), Double.valueOf(m.group(2)), Double.valueOf(m.group(3)));

              orbit.AddObjectToCentre(stellar);
            }
            // System.out.println("Found value: " + m.group(0));
          }

          if (tempString.startsWith("Planet")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              // Add to planet
              planet[i + 1] =
                  new Planet(
                      m.group(1),
                      m.group(2),
                      m.group(3),
                      Double.valueOf(m.group(4)),
                      Double.valueOf(m.group(5)),
                      Double.valueOf(m.group(6)),
                      m.group(7),
                      Double.valueOf(m.group(8)));

              orbit.AddObjectToTrack(planet[i + 1]);
            }
          }
          // line++;
        }
      }
      long stopTime = System.currentTimeMillis();
      System.out.println("readFileByReadAllBytes " + (stopTime - startTime));
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Read the files from .txt Build the CircularOrbit 使用 I/O 流读取文件
   *
   * @param fileName is the path of the file to read.
   */
  private static void readFileByLines(String fileName) {

    BufferedReader reader = null;
    Planet[] planet = new Planet[330000];

    try {
      System.out.println("Start reading from files.");
      long startTime = System.currentTimeMillis();
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
      String tempString = null;
      int line = 1;

      try {
        while ((tempString = reader.readLine()) != null) {
          String temp2 = tempString.replace(" ", "");
          if (tempString.length() != 0) {
            // System.out.println(tempString);
            // System.out.println("line " + line + ":" + tempString);
            if (tempString.startsWith("Stellar")) {
              Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
              Matcher m = regex.matcher(tempString);
              if (m.find()) {
                // Add to THE STELLAR
                Stellar stellar =
                    new Stellar(m.group(1), Double.valueOf(m.group(2)), Double.valueOf(m.group(3)));

                orbit.AddObjectToCentre(stellar);
              }
              // System.out.println("Found value: " + m.group(0));
            }

            if (tempString.startsWith("Planet")) {
              Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?)>");
              Matcher m = regex.matcher(tempString);
              if (m.find()) {
                // Add to planet
                planet[line] =
                    new Planet(
                        m.group(1),
                        m.group(2),
                        m.group(3),
                        Double.valueOf(m.group(4)),
                        Double.valueOf(m.group(5)),
                        Double.valueOf(m.group(6)),
                        m.group(7),
                        Double.valueOf(m.group(8)));

                orbit.AddObjectToTrack(planet[line]);
              }
            }
            line++;
          }
        }
        // System.out.println("All " + line + " lines has been read.");
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      long stopTime = System.currentTimeMillis();
      System.out.println("readFileByLines " + (stopTime - startTime));
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Write file by I/O Stream into the fileName.
   *
   * @param fileName is to which file is written to.
   */
  private static void writeFileByLines(String fileName) {

    Path p = Paths.get(fileName);

    // Sorting PART
    Track[] sortedTracks = new Track[orbit.GetTrack().size()];
    System.arraycopy(orbit.SortedTracks(), 0, sortedTracks, 0, orbit.GetTrack().size());

    // Planet ::= <p0,Gas,Grey,9449.29,4.1488e12,4683,CCW,141>
    long startTime = System.currentTimeMillis();
    
    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND))) {
      // for (Track track : orbit.GetTrack()) {
      for (Track track : sortedTracks) {
        for (Planet planet : track.getPhysicalObjects()) {
          byte data[] =
              ("Planet ::= <"
                      + track.GetName()
                      + ","
                      + planet.form
                      + ","
                      + planet.color
                      + ","
                      + planet.planetRadius
                      + ","
                      + planet.trackRadius
                      + ","
                      + planet.trackSpeed
                      + ","
                      + planet.trackOrientation
                      + ","
                      + planet.initDegree
                      + ">")
                  .getBytes();
          out.write(data, 0, data.length);
        }

        byte enter[] = "\n".getBytes();
        out.write(enter, 0, enter.length);
      }

    } catch (IOException x) {
      System.err.println(x);
    }
    long stopTime = System.currentTimeMillis();
    System.out.println("WriteFileByLines " + (stopTime - startTime));
    
  }

  private static void writeFileByReadAllBytes(String fileName) {
    Path file = Path.of(fileName);

    Track[] sortedTracks = new Track[orbit.GetTrack().size()];
    System.arraycopy(orbit.SortedTracks(), 0, sortedTracks, 0, orbit.GetTrack().size());

    long startTime = System.currentTimeMillis();
    
    for (Track track : sortedTracks) {
      for (Planet planet : track.getPhysicalObjects()) {
        byte data[] =
            ("Planet ::= <"
                    + track.GetName()
                    + ","
                    + planet.form
                    + ","
                    + planet.color
                    + ","
                    + planet.planetRadius
                    + ","
                    + planet.trackRadius
                    + ","
                    + planet.trackSpeed
                    + ","
                    + planet.trackOrientation
                    + ","
                    + planet.initDegree
                    + ">")
                .getBytes();
        try {
          Files.write(file, data, APPEND);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

      byte enter[] = "\n".getBytes();
      try {
        Files.write(file, enter, APPEND);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    long stopTime = System.currentTimeMillis();
    System.out.println("WriteFileByReadAllBytes " + (stopTime - startTime));
  }

  private static void writeFileByBufferedReader(String fileName) {
    Path file = Path.of(fileName);
    Charset charset = Charset.forName("UTF-8");

    Track[] sortedTracks = new Track[orbit.GetTrack().size()];
    System.arraycopy(orbit.SortedTracks(), 0, sortedTracks, 0, orbit.GetTrack().size());

    long startTime = System.currentTimeMillis();
    
    try {
      BufferedWriter writer = Files.newBufferedWriter(file, charset);
      for (Track track : sortedTracks) {
        for (Planet planet : track.getPhysicalObjects()) {
          writer.write(
              ("Planet ::= <"
                  + track.GetName()
                  + ","
                  + planet.form
                  + ","
                  + planet.color
                  + ","
                  + planet.planetRadius
                  + ","
                  + planet.trackRadius
                  + ","
                  + planet.trackSpeed
                  + ","
                  + planet.trackOrientation
                  + ","
                  + planet.initDegree
                  + ">"),
              0,
              ("Planet ::= <"
                      + track.GetName()
                      + ","
                      + planet.form
                      + ","
                      + planet.color
                      + ","
                      + planet.planetRadius
                      + ","
                      + planet.trackRadius
                      + ","
                      + planet.trackSpeed
                      + ","
                      + planet.trackOrientation
                      + ","
                      + planet.initDegree
                      + ">")
                  .length());
        }

        byte enter[] = "\n".getBytes();
        writer.write("\n", 0, "\n".length());
      }

    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    long stopTime = System.currentTimeMillis();
    System.out.println("WriteFileByReadAllBytes " + (stopTime - startTime));
    
  }
}

/**
 * The main frame refresh function Implement the actions done in one frame
 *
 * @author tony
 */
class MovingOBJ extends Thread {
  //  int x = 0;
  //  int y = 400;
  StellarSystemVisual p;
  int time = 10;

  public void setPanel(StellarSystemVisual p) {
    this.p = p;
  }

  @Override
  public void run() {

    while (true) {
      p.contentPane.removeAll();

      /*
       * JButton btnNewButton = new JButton("Stop"); btnNewButton.setBounds(39, 100,
       * 120, 29); p.contentPane.add(btnNewButton);
       *
       * btnNewButton.addActionListener(new ActionListener() {
       *
       * @SuppressWarnings("deprecation") public void actionPerformed(ActionEvent e) {
       * p.run = false; } });
       */

      // The Label of the Sun

      JComboBox comboBox = new JComboBox();
      for (Track track : p.orbit.GetTrack()) {
        comboBox.addItem(track.GetName());
      }
      comboBox.setBounds(39, 22, 120, 27);
      p.contentPane.add(comboBox);

      JComboBox comboBox_1 = new JComboBox();
      for (Track track : p.orbit.GetTrack()) {
        comboBox_1.addItem(track.GetName());
      }
      comboBox_1.setBounds(39, 61, 120, 27);
      p.contentPane.add(comboBox_1);

      JButton btnNewButton = new JButton("Calculate");
      btnNewButton.setBounds(39, 100, 120, 29);
      p.contentPane.add(btnNewButton);

      Position position = new Position(0, 0);
      Position position_1 = new Position(0, 0);

      JTextPane textPane = new JTextPane();
      textPane.setBounds(39, 141, 120, 27);
      p.contentPane.add(textPane);

      textPane.setText("Click calculator to Pause");

      btnNewButton.addActionListener(
          new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              time = 100000;
              for (Track track : p.orbit.GetTrack()) {
                if (track.GetName().equals(comboBox.getSelectedItem())) {
                  for (Planet planet : track.getPhysicalObjects()) {
                    position.SetPosition(planet.GetPosition());
                  }
                }
                if (track.GetName().equals(comboBox_1.getSelectedItem())) {
                  for (Planet planet : track.getPhysicalObjects()) {
                    position_1.SetPosition(planet.GetPosition());
                  }
                }
              }

              textPane.setText(
                  String.valueOf(
                      Math.sqrt(
                          position.GetRadius() * position.GetRadius()
                              + position_1.GetRadius() * position_1.GetRadius()
                              - 2
                                  * position.GetRadius()
                                  * position_1.GetRadius()
                                  * Math.cos(
                                      (position.GetDegree() - position_1.GetDegree())
                                          * Math.PI
                                          / 180))));
            }
          });

      JLabel sun = new JLabel("New label");
      sun.setIcon(new ImageIcon("lib/StellarSystem/sun.png"));
      sun.setBounds(430, 270, 100, 100);
      p.contentPane.add(sun);

      p.orbit.Init(p.contentPane);

      // The Label of the background
      JLabel bg = new JLabel("New label");
      bg.setIcon(new ImageIcon("lib/StellarSystem/universe.png"));
      bg.setBounds(0, 0, 960, 640);
      p.contentPane.add(bg);

      p.contentPane.updateUI();
      try {
        sleep(time * 5);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
