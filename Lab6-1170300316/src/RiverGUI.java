import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;

public class RiverGUI extends JFrame {

  private int n, h, t, N, k, MV;

  private JPanel contentPane;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;
  private JTextField textField_5;
  public static JTextArea textArea;
  public static JTextArea textArea_1;
  public static JTextArea textArea_2;
  private JLabel lblNewLabel_6;
  private JLabel lblNewLabel_7;
  private JButton btnNewButton_1;
  private JButton btnFile;
  private JButton btnNewButton_2;
  private JButton btnFile_1;

  /** Launch the application. */
  public static void main(String[] args) {
    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            try {
              RiverGUI frame = new RiverGUI();
              frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
  }

  /** Create the frame. */
  public RiverGUI() {
    setTitle("MONKEY");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 523, 360);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    textField = new JTextField();
    textField.setBounds(45, 6, 69, 26);
    contentPane.add(textField);
    textField.setColumns(10);

    textField_1 = new JTextField();
    textField_1.setBounds(45, 44, 69, 26);
    contentPane.add(textField_1);
    textField_1.setColumns(10);

    textField_2 = new JTextField();
    textField_2.setBounds(45, 82, 69, 26);
    contentPane.add(textField_2);
    textField_2.setColumns(10);

    textField_3 = new JTextField();
    textField_3.setBounds(45, 120, 69, 26);
    contentPane.add(textField_3);
    textField_3.setColumns(10);

    textField_4 = new JTextField();
    textField_4.setBounds(45, 158, 69, 26);
    contentPane.add(textField_4);
    textField_4.setColumns(10);

    textField_5 = new JTextField();
    textField_5.setBounds(45, 196, 69, 26);
    contentPane.add(textField_5);
    textField_5.setColumns(10);

    JLabel lblNewLabel = new JLabel("n");
    lblNewLabel.setBounds(21, 11, 19, 16);
    contentPane.add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("h");
    lblNewLabel_1.setBounds(21, 49, 19, 16);
    contentPane.add(lblNewLabel_1);

    JLabel lblNewLabel_2 = new JLabel("t");
    lblNewLabel_2.setBounds(21, 87, 19, 16);
    contentPane.add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("N");
    lblNewLabel_3.setBounds(21, 125, 19, 16);
    contentPane.add(lblNewLabel_3);

    JLabel lblNewLabel_4 = new JLabel("k");
    lblNewLabel_4.setBounds(21, 163, 19, 16);
    contentPane.add(lblNewLabel_4);

    JLabel lblNewLabel_5 = new JLabel("MV");
    lblNewLabel_5.setBounds(21, 201, 25, 16);
    contentPane.add(lblNewLabel_5);

    JButton btnNewButton = new JButton("Strategy 1");
    btnNewButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            n = Integer.valueOf(textField.getText());
            h = Integer.valueOf(textField_1.getText());
            t = Integer.valueOf(textField_2.getText());
            N = Integer.valueOf(textField_3.getText());
            k = Integer.valueOf(textField_4.getText());
            MV = Integer.valueOf(textField_5.getText());
            
            RiverCrossingSim sim = new RiverCrossingSim(n, h, t, N, k, MV, true);
            // textArea.append("try\n");
            // RiverCrossingSim sim = new RiverCrossingSim(5, 20, 5, 23, 5, 5);

          }
        });
    btnNewButton.setBounds(17, 234, 97, 26);
    contentPane.add(btnNewButton);

    textArea = new JTextArea();
    textArea.setBounds(126, 11, 305, 247);
    // contentPane.add(textArea);

    JScrollPane scroll = new JScrollPane(textArea);
    scroll.setBounds(126, 11, 305, 247);

    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    contentPane.add(scroll);
    
    textArea_1 = new JTextArea();
    textArea_1.setBounds(126, 293, 127, 16);
    contentPane.add(textArea_1);
    
    textArea_2 = new JTextArea();
    textArea_2.setBounds(265, 293, 127, 16);
    contentPane.add(textArea_2);
    
    lblNewLabel_6 = new JLabel("吞吐率");
    lblNewLabel_6.setBounds(126, 265, 61, 16);
    contentPane.add(lblNewLabel_6);
    
    lblNewLabel_7 = new JLabel("公平性");
    lblNewLabel_7.setBounds(265, 265, 61, 16);
    contentPane.add(lblNewLabel_7);
    
    btnNewButton_1 = new JButton("Strategy 2");
    btnNewButton_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        n = Integer.valueOf(textField.getText());
        h = Integer.valueOf(textField_1.getText());
        t = Integer.valueOf(textField_2.getText());
        N = Integer.valueOf(textField_3.getText());
        k = Integer.valueOf(textField_4.getText());
        MV = Integer.valueOf(textField_5.getText());
        
        RiverCrossingSim sim = new RiverCrossingSim(n, h, t, N, k, MV, false);
      }
    });
    btnNewButton_1.setBounds(17, 272, 97, 29);
    contentPane.add(btnNewButton_1);
    
    btnFile = new JButton("File 1");
    btnFile.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FileReader fileReader = new FileReader("src/input/Competition_1.txt");
        n = fileReader.getn();
        h = fileReader.geth();
        t = fileReader.getTimeInterval();
        N = fileReader.getN();
        k = fileReader.getk();
        
        System.out.println(n + " " + h + " " + t + " " + N + " " + k);
        RiverCrossingSim sim = new RiverCrossingSim(n, h, t, N, k, 5, true, fileReader);
      }
    });
    btnFile.setBounds(443, 11, 74, 72);
    contentPane.add(btnFile);
    
    btnNewButton_2 = new JButton("File 2");
    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FileReader fileReader = new FileReader("src/input/Competition_2.txt");
        n = fileReader.getn();
        h = fileReader.geth();
        t = fileReader.getTimeInterval();
        N = fileReader.getN();
        k = fileReader.getk();
        
        System.out.println(n + " " + h + " " + t + " " + N + " " + k);
        RiverCrossingSim sim = new RiverCrossingSim(n, h, t, N, k, 5, true, fileReader);
      }
    });
    btnNewButton_2.setBounds(443, 95, 74, 72);
    contentPane.add(btnNewButton_2);
    
    btnFile_1 = new JButton("File 3");
    btnFile_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        FileReader fileReader = new FileReader("src/input/Competition_3.txt");
        n = fileReader.getn();
        h = fileReader.geth();
        t = fileReader.getTimeInterval();
        N = fileReader.getN();
        k = fileReader.getk();
        
        System.out.println(n + " " + h + " " + t + " " + N + " " + k);
        RiverCrossingSim sim = new RiverCrossingSim(n, h, t, N, k, 5, true, fileReader);
      }
    });
    btnFile_1.setBounds(443, 179, 74, 72);
    contentPane.add(btnFile_1);
  }
}
