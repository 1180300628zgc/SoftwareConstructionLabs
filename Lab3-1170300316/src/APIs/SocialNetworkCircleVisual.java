package APIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import centralObject.CentralObject;
import centralObject.Nucleus;
import centralObject.Person;
import circularOrbit.CircularOrbit;
import physicalObject.Atom;
import physicalObject.Friend;
import physicalObject.Planet;
import track.Track;
import extensions.FriendRelation;
import extensions.Graph;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class SocialNetworkCircleVisual extends JFrame {

	private static CircularOrbit<CentralObject, Track> orbit = CircularOrbit.emptySocialNetworkCircle();
	private static Set<Friend> set = new HashSet<Friend>();
	private static Graph<Friend> graph = Graph.empty(set);

	static FriendRelation relation = new FriendRelation(orbit.GetFriends());
	static Set<Friend> addedFriends = new HashSet<Friend>();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Parse();

		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
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
		
		btnNewButton.addActionListener(new ActionListener() {
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
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Friend friend : orbit.GetFriends()) {
					if (friend.GetName().equals(comboBox_1.getSelectedItem())) {
						for (Friend friend2 : orbit.GetFriends()) {
							if (friend2.GetName().equals(comboBox_2.getSelectedItem())) {
								contentPane.removeAll();
								relation.addRelation(friend, friend2);

								FileWriter fw = null;
								try {
								//如果文件存在，则追加内容；如果文件不存在，则创建文件
								File f=new File("src/input/SocialNetworkCircle.txt");
								fw = new FileWriter(f, true);
								} catch (IOException e1) {
								e1.printStackTrace();
								}
								PrintWriter pw = new PrintWriter(fw);
								pw.println("\nSocialTie ::= <" + friend.GetName() +", " + friend2.GetName() + ", 0.99>");
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

	/**
	 * Assign the reading path
	 */
	public static void Parse() {
		String file1 = "src/input/SocialNetworkCircle.txt";
		readFileByLines(file1);

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
								for (Friend friend : orbit.GetFriends()) {
									if (friend.GetName().equals(m.group(1))) {
										for (Friend friend2 : orbit.GetFriends()) {
											if (friend2.GetName().equals(m.group(2))) {
												relation.NewRelation(friend, friend2);
											}
										}
									}
								}
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

				while (i < 5) {
					track[i] = new Track(i);
					if (track[i -1 ].GetFriendObject().size() ==0) {
						break;
					}
					for (Friend friend : track[i - 1].GetFriendObject()) {
						for (Friend friend2 : orbit.GetFriends()) {
							if (!track[i - 1].GetFriendObject().contains(friend2)
									&& relation.isRelated(friend, friend2)) {
								track[i].AddFriendObject(friend2);
							}
						}
						for (int j = 0; j < i; j++) {
							track[i].GetFriendObject().removeAll(track[j].GetFriendObject());
						}
					}

					for (Friend friend : track[i].GetFriendObject()) {
						System.out.println(i+ friend.GetName());
					}
					orbit.AddTrack(track[i]);
					i++;
				}

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
}
