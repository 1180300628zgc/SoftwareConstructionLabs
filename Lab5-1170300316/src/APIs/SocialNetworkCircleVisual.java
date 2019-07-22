package APIs;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import centralObject.CentralObject;
import centralObject.Person;
import circularOrbit.CircularOrbit;
import physicalObject.Friend;
import track.Track;
import extensions.FriendRelation;
import extensions.Graph;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

import static java.nio.file.StandardOpenOption.*;

public class SocialNetworkCircleVisual extends JFrame {

  private static CircularOrbit<CentralObject, Track> orbit =
      CircularOrbit.emptySocialNetworkCircle();
  private static Set<Friend> set = new HashSet<Friend>();
  private static Graph<Friend> graph = Graph.empty(set);

  static FriendRelation relation = new FriendRelation(orbit.GetFriends());
  static Set<Friend> addedFriends = new HashSet<Friend>();

  private JPanel contentPane;

  /** Launch the application. */
  public static void main(String[] args) {

    Parse();

    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            try {
              SocialNetworkCircleVisual frame = new SocialNetworkCircleVisual();
              frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
  }

  /** Create the frame. */
  public SocialNetworkCircleVisual() {
    setTitle("Social Network");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 960, 640);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    orbit.Init(contentPane);

    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setIcon(new ImageIcon("lib/SocialNetwork/human.png"));
    lblNewLabel.setBounds(450, 290, 60, 60);
    contentPane.add(lblNewLabel);

    for (Friend friend : orbit.GetFriends()) {
      System.out.println("These are friends!" + friend.GetName());
    }

    JComboBox comboBox = new JComboBox();
    comboBox.setBounds(30, 28, 120, 27);
    for (Friend friend : orbit.GetFriends()) {
      comboBox.addItem(friend.GetName());
    }
    contentPane.add(comboBox);

    JTextPane textPane = new JTextPane();
    textPane.setBounds(30, 108, 120, 16);
    contentPane.add(textPane);

    JButton btnNewButton = new JButton("Info Diffusion");

    btnNewButton.setBounds(30, 67, 117, 29);

    btnNewButton.addActionListener(
        new ActionListener() {
          int infoDiffusion = 0;

          public void actionPerformed(ActionEvent e) {
            for (Friend friend : orbit.GetFriends()) {
              if (friend.GetName().equals(comboBox.getSelectedItem())) {
                for (Friend friend2 : orbit.GetFriends()) {
                  if (relation.isRelated(friend, friend2)) {
                    infoDiffusion++;
                  }
                }
              }
            }
            textPane.setText(String.valueOf(infoDiffusion));
            infoDiffusion = -1;
          }
        });

    contentPane.add(btnNewButton);

    JComboBox comboBox_1 = new JComboBox();
    comboBox_1.setBounds(30, 163, 120, 27);
    for (Friend friend : orbit.GetFriends()) {
      comboBox_1.addItem(friend.GetName());
    }
    contentPane.add(comboBox_1);

    JComboBox comboBox_2 = new JComboBox();
    comboBox_2.setBounds(30, 202, 120, 27);
    for (Friend friend : orbit.GetFriends()) {
      comboBox_2.addItem(friend.GetName());
    }
    contentPane.add(comboBox_2);

    JButton btnNewButton_1 = new JButton("Add Relation");
    btnNewButton_1.setBounds(30, 241, 117, 29);
    contentPane.add(btnNewButton_1);

    btnNewButton_1.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            for (Friend friend : orbit.GetFriends()) {
              if (friend.GetName().equals(comboBox_1.getSelectedItem())) {
                for (Friend friend2 : orbit.GetFriends()) {
                  if (friend2.GetName().equals(comboBox_2.getSelectedItem())) {
                    contentPane.removeAll();
                    relation.addRelation(friend, friend2);

                    // The revised file should be added to the same file.
                    FileWriter fw = null;
                    try {
                      // 如果文件存在，则追加内容；如果文件不存在，则创建文件
                      File f = new File("src/input/SocialNetworkCircleBig.txt");
                      fw = new FileWriter(f, true);
                    } catch (IOException e1) {
                      e1.printStackTrace();
                    }
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(
                        "\nSocialTie ::= <"
                            + friend.GetName()
                            + ", "
                            + friend2.GetName()
                            + ", 0.99>");
                    pw.flush();
                    try {
                      fw.flush();
                      pw.close();
                      fw.close();
                    } catch (IOException e1) {
                      e1.printStackTrace();
                    }
                    SocialNetworkCircleVisual.main(null);
                    contentPane.updateUI();
                  }
                }
              }
            }

            contentPane.removeAll();

            orbit.Init(contentPane);

            contentPane.updateUI();
          }
        });
  }

  /** Assign the reading path */
  public static void Parse() {
    String file1 = "src/input/SocialNetworkCircleBig.txt";
    // readFileByLines(file1);
    // readFileByBufferedReader(file1);
    // Some Bugs
    // readFileByReadAllBytes(file1);
    readFileByLines(file1);
  }

  /**
   * Read the file from the given string using the buffered reader 使用缓冲流 I/O 读取文件
   *
   * @param fileName
   */
  private static void readFileByBufferedReader(String fileName) {
    Charset charset = Charset.forName("UTF-8");
    Path path = Path.of(fileName);

    Friend centerFriend;

    try {
      BufferedReader reader = Files.newBufferedReader(path, charset);
      BufferedReader reader2 = Files.newBufferedReader(path, charset);
      String tempString = null;
      int line = 1;

      while ((tempString = reader.readLine()) != null) {
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          System.out.println("line " + line + ":" + tempString);
          if (tempString.startsWith("CentralUser")) {
            Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              Person person = new Person(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
              centerFriend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
              orbit.AddObjectToCentre(person);
              orbit.NewFriend(centerFriend);

              addedFriends.add(centerFriend);
            }
          }

          if (tempString.startsWith("Friend")) {
            System.out.println("Stuck here");
            Pattern regex = Pattern.compile("<(.*?), (.*?), (.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              Friend friend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
              orbit.NewFriend(friend);
            }
          }

          // ----DEBUG LOG-----//

          line++;
        }
      }

      line = 1;

      while ((tempString = reader2.readLine()) != null) {
        String temp2 = tempString.replace(" ", "");
        if (tempString.length() != 0) {
          if (tempString.startsWith("SocialTie")) {
            Pattern regex = Pattern.compile("<(.*?), (.*?), (.*?)>");
            Matcher m = regex.matcher(tempString);
            if (m.find()) {
              relation.NewRelation(new Friend(m.group(1), 1, "M"), new Friend(m.group(2), 1, "F"));
            }
          }

          // ----DEBUG LOG-----//

          line++;
        }
      }

      for (Friend friend : relation.GetMap().keySet()) {
        System.out.println("MAP " + friend.GetName());
        System.out.println(relation.GetMap().get(friend));
      }

      //
      Track[] track = new Track[5];

      int i = 0;
      track[i] = new Track(i);

      for (Friend friend : addedFriends) {
        track[i].AddFriendObject(friend);
      }

      for (Friend friend : track[i].GetFriendObject()) {
        System.out.println(friend.GetName());
      }

      orbit.AddTrack(track[i]);

      i++;

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

    Friend centerFriend;

    System.out.println("Start reading from files.");
    java.util.List<String> stringList = new ArrayList();
    java.util.List<String> stringList2 = new ArrayList();

    try {
      stringList = Files.readAllLines(file);
      stringList2 = Files.readAllLines(file);
      String emptyString = null;
      stringList.add(emptyString);
      stringList2.add(emptyString);
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String tempString = null;
    int line = 1;

    while (stringList.get(line - 1) != null) {
      tempString = new String(stringList.get(line - 1));
      String temp2 = tempString.replace(" ", "");
      if (tempString.length() != 0) {
        System.out.println("line " + line + ":" + tempString);
        if (tempString.startsWith("CentralUser")) {
          Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
          Matcher m = regex.matcher(tempString);
          if (m.find()) {
            Person person = new Person(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
            centerFriend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
            orbit.AddObjectToCentre(person);
            orbit.NewFriend(centerFriend);
            addedFriends.add(centerFriend);
          }
        }

        if (tempString.startsWith("Friend")) {
          // Stuck before because of the empty line.
          System.out.println("Stuck here");
          Pattern regex = Pattern.compile("<(.*?), (.*?), (.*?)>");
          Matcher m = regex.matcher(tempString);
          if (m.find()) {
            Friend friend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
            orbit.NewFriend(friend);
          }
        }
        System.out.println("line++");
        // ----DEBUG LOG-----//
        line++;
      }
    }

    line = 1;

    while (stringList2.get(line - 1) != null) {
      tempString = new String(stringList2.get(line - 1));
      String temp2 = tempString.replace(" ", "");
      if (tempString.length() != 0) {
        if (tempString.startsWith("SocialTie")) {
          Pattern regex = Pattern.compile("<(.*?), (.*?), (.*?)>");
          Matcher m = regex.matcher(tempString);
          if (m.find()) {
            relation.NewRelation(new Friend(m.group(1), 1, "M"), new Friend(m.group(2), 1, "F"));
          }
        }

        // ----DEBUG LOG-----//

        line++;
      }
    }

    for (Friend friend : relation.GetMap().keySet()) {
      System.out.println("MAP " + friend.GetName());
      System.out.println(relation.GetMap().get(friend));
    }

    //
    Track[] track = new Track[5];

    int i = 0;
    track[i] = new Track(i);

    for (Friend friend : addedFriends) {
      track[i].AddFriendObject(friend);
    }

    for (Friend friend : track[i].GetFriendObject()) {
      System.out.println(friend.GetName());
    }

    orbit.AddTrack(track[i]);

    i++;
  }

  /**
   * Read the files from .txt Build the CircularOrbit
   *
   * @param fileName
   */
  private static void readFileByLines(String fileName) {

    BufferedReader reader = null;
    BufferedReader reader2 = null;

    Friend centerFriend;
    // Planet[] planet = new Planet[100];

    try {
      System.out.println("Start reading from files.");
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
      reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
      String tempString = null;
      int line = 1;

      try {
        while ((tempString = reader.readLine()) != null) {
          String temp2 = tempString.replace(" ", "");
          if (tempString.length() != 0) {
            System.out.println("line " + line + ":" + tempString);
            if (tempString.startsWith("CentralUser")) {
              Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
              Matcher m = regex.matcher(tempString);
              if (m.find()) {
                Person person = new Person(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
                centerFriend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
                orbit.AddObjectToCentre(person);
                orbit.NewFriend(centerFriend);

                addedFriends.add(centerFriend);
              }
            }

            if (tempString.startsWith("Friend")) {
              Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
              Matcher m = regex.matcher(tempString);
              if (m.find()) {
                Friend friend = new Friend(m.group(1), Integer.valueOf(m.group(2)), m.group(3));
                orbit.NewFriend(friend);
              }
            }
            line++;
          }
        }

        line = 1;

        while ((tempString = reader2.readLine()) != null) {
          String temp2 = tempString.replace(" ", "");
          if (tempString.length() != 0) {
            if (tempString.startsWith("SocialTie")) {
              Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
              Matcher m = regex.matcher(tempString);
              if (m.find()) {
                // Deleted Verifying Part
                relation.NewRelation(
                    new Friend(m.group(1), 1, "M"), new Friend(m.group(2), 1, "F"));
              }
            }
            line++;
          }
        }

        System.out.println("Here");
        for (Friend friend : relation.GetMap().keySet()) {
          System.out.println("MAP " + friend.GetName());
          System.out.println(relation.GetMap().get(friend));
        }

        //
        Track[] track = new Track[5];

        int i = 0;
        track[i] = new Track(i);

        for (Friend friend : addedFriends) {
          track[i].AddFriendObject(friend);
        }

        for (Friend friend : track[i].GetFriendObject()) {
          System.out.println(friend.GetName());
        }

        orbit.AddTrack(track[i]);

        i++;

        // Delete GUI Part
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 无缓冲的输出流
   *
   * @param fileName
   */
  private static void writeFileByLines(String fileName) {
    Path p = Paths.get(fileName);
    try {
      OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND));
      for (Friend friend : orbit.GetFriends()) {
        byte data[] =
            ("Friend ::= <" + friend.GetName() + "," + friend.age + "," + friend.gender + ">")
                .getBytes();
        out.write(data, 0, data.length);
      }
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /** 使用缓冲流 I/O */
  private static void writeFileByBufferedWriter(String fileName) {
    Charset charset = Charset.forName("UTF-8");
    Path p = Paths.get(fileName);
    try {
      BufferedWriter writer = Files.newBufferedWriter(p, charset);
      for (Friend friend : orbit.GetFriends()) {
        String data =
            ("Friend ::= <" + friend.GetName() + "," + friend.age + "," + friend.gender + ">");
        writer.write(data, 0, data.length());
      }
    } catch (IOException e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void writeFileByAllBytes(String fileName) {
    Path file = Path.of(fileName);
    for (Friend friend : orbit.GetFriends()) {
      byte data[] =
          ("Friend ::= <" + friend.GetName() + "," + friend.age + "," + friend.gender + ">")
              .getBytes();
      try {
        Files.write(file, data);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
