package APIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import centralObject.CentralObject;
import centralObject.Nucleus;
import circularOrbit.AtomStructure;
import circularOrbit.CircularOrbit;
import physicalObject.Atom;
import physicalObject.Planet;
import track.Track;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AtomStructureVisual extends JFrame {

	private JPanel contentPane;
	private static CircularOrbit<CentralObject, Track> orbit = CircularOrbit.emptyAtomStructure();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Parse();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtomStructureVisual frame = new AtomStructureVisual();
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
	public AtomStructureVisual() {
		setTitle("Atom Structure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(43, 43, 120, 27);
		for (int i = 0; i < orbit.GetTrack().size(); i++) {
			comboBox.addItem(String.valueOf(i + 1));
		}
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(43, 82, 120, 27);
		for (int i = 0; i < orbit.GetTrack().size(); i++) {
			comboBox_1.addItem(String.valueOf(i + 1));
		}
		contentPane.add(comboBox_1);

		JButton btnNewButton = new JButton("JUMP");
		btnNewButton.setBounds(43, 121, 120, 29);
		contentPane.add(btnNewButton);

		orbit.Init(contentPane);
		/*
		 * JLabel lblNewLabel_2 = new JLabel("electron"); lblNewLabel_2.setIcon(new
		 * ImageIcon("/Users/tony/Documents/Software Construction/Handin/Lab3-1170300316/lib/AtomStructure/electron.png"
		 * )); lblNewLabel_2.setBounds(285, 285, 60, 60);
		 * contentPane.add(lblNewLabel_2);
		 */

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Track track : orbit.GetTrack()) {
					if (track.GetLevel() == Integer.valueOf((String) comboBox.getSelectedItem())) {
						for (Track track2 : orbit.GetTrack()) {
							if (track2.GetLevel() == Integer.valueOf((String) comboBox_1.getSelectedItem())) {
								for (Atom atom : track.getAtomObjects()) {
									track.RemoveAtomObject(atom);
									track2.AddAtomObject(atom);
									contentPane.removeAll();
									orbit.Init(contentPane);

									JLabel lblNewLabel = new JLabel("nucleus");
									lblNewLabel.setIcon(new ImageIcon(
											"lib/AtomStructure/nucleus.png"));
									lblNewLabel.setBounds(450, 290, 60, 60);
									contentPane.add(lblNewLabel);

									/*
									 * JLabel lblNewLabel_1 = new JLabel("track"); lblNewLabel_1.setIcon(new
									 * ImageIcon("/Users/tony/Documents/Software Construction/Handin/Lab3-1170300316/lib/AtomStructure/track.png"
									 * )); lblNewLabel_1.setBounds(307, 185, 383, 313);
									 * contentPane.add(lblNewLabel_1);
									 */

									JLabel lblNewLabel_3 = new JLabel("bg");
									lblNewLabel_3.setIcon(new ImageIcon(
											"lib/AtomStructure/bg.png"));
									lblNewLabel_3.setBounds(0, 0, 960, 640);
									contentPane.add(lblNewLabel_3);

									contentPane.updateUI();
									System.out.println("Jumped");
									break;
								}
							}
						}
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel("nucleus");
		lblNewLabel.setIcon(new ImageIcon(
				"lib/AtomStructure/nucleus.png"));
		lblNewLabel.setBounds(450, 290, 60, 60);
		contentPane.add(lblNewLabel);

		/*
		 * JLabel lblNewLabel_1 = new JLabel("track"); lblNewLabel_1.setIcon(new
		 * ImageIcon("/Users/tony/Documents/Software Construction/Handin/Lab3-1170300316/lib/AtomStructure/track.png"
		 * )); lblNewLabel_1.setBounds(307, 185, 383, 313);
		 * contentPane.add(lblNewLabel_1);
		 */

		JLabel lblNewLabel_3 = new JLabel("bg");
		lblNewLabel_3.setIcon(new ImageIcon(
				"lib/AtomStructure/bg.png"));
		lblNewLabel_3.setBounds(0, 0, 960, 640);
		contentPane.add(lblNewLabel_3);
	}

	/**
	 * Assign the reading path
	 */
	public static void Parse() {
		String file1 = "src/input/AtomicStructure.txt";
		readFileByLines(file1);

	}

	/**
	 * Read the files from .txt Build the CircularOrbit
	 * 
	 * @param fileName
	 */
	private static void readFileByLines(String fileName) {

		BufferedReader reader = null;
		// Planet[] planet = new Planet[100];

		try {
			System.out.println("Start reading from files.");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			String tempString = null;
			int line = 1;

			try {
				while ((tempString = reader.readLine()) != null) {
					String temp2 = tempString.replace(" ", "");
					if (tempString.length() != 0) {
						System.out.println("line " + line + ":" + tempString);
						if (tempString.startsWith("ElementName")) {
							Pattern regex = Pattern.compile("::= (.*?)$");
							Matcher m = regex.matcher(tempString);
							if (m.find()) {
								Nucleus nucleus = new Nucleus(m.group(1));
								System.out.println(m.group(1));
								orbit.AddObjectToCentre(nucleus);
							}
						}

						if (tempString.startsWith("NumberOfTracks")) {
							Pattern regex = Pattern.compile("::= (.*?)$");
							Matcher m = regex.matcher(tempString);
							if (m.find()) {
								orbit.SetNumOfTracks(Integer.valueOf(m.group(1)));
								for (int i = 0; i < orbit.GetNumOfTracks(); i++) {
									orbit.AddTrack(new Track(i + 1));
								}
								System.out.println("Track number " + Integer.valueOf(m.group(1)));
							}

						}

						if (tempString.startsWith("NumberOfElectron")) {
							Pattern regex = Pattern.compile("::= (.*?)$");
							Matcher m = regex.matcher(tempString);
							if (m.find()) {
								String[] tmpStrings = m.group(1).split(";");
								for (int i = 0; i < tmpStrings.length; i++) {
									String[] tmpStrings2 = tmpStrings[i].split("/");
									for (int j = 0; j < Integer.valueOf(tmpStrings2[1]); j++) {
										Atom atom = new Atom(Integer.valueOf(tmpStrings2[0]));
										for (Track track : orbit.GetTrack()) {
											if (track.GetLevel() == Integer.valueOf(tmpStrings2[0])) {
												track.AddAtomObject(atom);
											}
										}
									}
								}
							}
						}

						// ----DEBUG LOG-----//
						for (int i = 0; i < orbit.GetNumOfTracks(); i++) {
							for (Track track : orbit.GetTrack()) {
								System.out.println(
										"Track Level " + track.GetLevel() + " Number " + track.getAtomObjects().size());
							}
						}

						line++;
					}
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
