package APIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class LogFinder extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogFinder frame = new LogFinder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogFinder() {
		setTitle("Log Finder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(24, 200, 419, 321);

		// contentPane.add(textArea);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 200, 419, 321);
		// 把定义的JTextArea放到JScrollPane里面去 

		// 分别设置水平和垂直滚动条自动出现 
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentPane.add(scroll);

		JButton btnShowText = new JButton("Show Text");
		btnShowText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f1 = new File("src/output/log.txt");
				try {
					BufferedReader in = new BufferedReader(new FileReader(f1));
					String str1 = in.readLine();
					while (str1 != null) {
						textArea.append("\n" + str1);
						str1 = in.readLine();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnShowText.setBounds(24, 145, 398, 29);
		contentPane.add(btnShowText);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(24, 27, 182, 29);
		comboBox.addItem("IllegalNumberException");
		comboBox.addItem("MissingObjectException");
		comboBox.addItem("SameLabelException");
		comboBox.addItem("FalseDependencyException");
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(24, 68, 182, 29);
		comboBox_1.addItem("Track Add");
		comboBox_1.addItem("Physical Object Add");
		comboBox_1.addItem("Reading Files");
		contentPane.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(24, 109, 182, 29);
		comboBox_2.addItem("Track.java");
		comboBox_2.addItem("ConcreteCircularOrbit.java");
		contentPane.add(comboBox_2);

		JButton btnSearchByException = new JButton("Search by Exception Type");
		btnSearchByException.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f1 = new File("src/output/log.txt");
				String tmp = new String();
				try {
					textArea.setText("");
					BufferedReader in = new BufferedReader(new FileReader(f1));
					String str1 = in.readLine();
					boolean flag = false;
					while (str1 != null) {
						if (str1.startsWith("Time")) {
							tmp = new String(str1);
							flag = true;
						}
						if (comboBox.getSelectedIndex() == 0) {
							if (str1.startsWith("Error: exception.IllegalNumberException") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						if (comboBox.getSelectedIndex() == 1) {
							if (str1.startsWith("Error: exception.MissingObjectException") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						if (comboBox.getSelectedIndex() == 2) {
							if (str1.startsWith("Error: exception.SameLabelException") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						if (comboBox.getSelectedIndex() == 3) {
							if (str1.startsWith("Error: exception.FalseDependencyException") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						str1 = in.readLine();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearchByException.setBounds(218, 27, 204, 29);
		contentPane.add(btnSearchByException);

		JButton btnNewButton = new JButton("Search by Operation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f1 = new File("src/output/log.txt");
				String tmp = new String();
				try {
					textArea.setText("");
					BufferedReader in = new BufferedReader(new FileReader(f1));
					String str1 = in.readLine();
					boolean flag = false;
					while (str1 != null) {
						if (str1.startsWith("Time")) {
							tmp = new String(str1);
							flag = true;
						}
						if (comboBox_1.getSelectedIndex() == 0) {
							if (str1.contains(" has been added to the circular orbit") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						if (comboBox_1.getSelectedIndex() == 1) {
							if (str1.startsWith(" has been added to a track") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						if (comboBox_1.getSelectedIndex() == 2) {
							if (str1.startsWith("Start reading from files.") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						str1 = in.readLine();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(218, 68, 204, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Search by Class");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f1 = new File("src/output/log.txt");
				String tmp = new String();
				try {
					textArea.setText("");
					BufferedReader in = new BufferedReader(new FileReader(f1));
					String str1 = in.readLine();
					boolean flag = false;
					while (str1 != null) {
						if (str1.startsWith("Time")) {
							tmp = new String(str1);
							flag = true;
						}
						if (comboBox_2.getSelectedIndex() == 0) {
							if (str1.contains("Track") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						if (comboBox_2.getSelectedIndex() == 1) {
							if (str1.startsWith("ConcreteCircularOrbit") && flag) {
								textArea.append("\n" + tmp);
								textArea.append("\n" + str1 + "\n");
								flag = false;
							}
						}
						str1 = in.readLine();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(218, 109, 204, 29);
		contentPane.add(btnNewButton_1);
	}
}
