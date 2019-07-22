package APIs;

import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.regex.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Log.LogFile;
import centralObject.CentralObject;
import centralObject.Stellar;
import circularOrbit.CircularOrbit;
import circularOrbit.ConcreteCircularOrbit;
import circularOrbit.StellarSystem;
import exception.IllegalNumberException;
import exception.SameLabelException;
import extensions.Position;
import physicalObject.Planet;
import track.Track;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class StellarSystemVisual extends JFrame {

	public JPanel contentPane;
	public static CircularOrbit<CentralObject, Track> orbit = CircularOrbit.emptyStellarSystem();

	// wait for implement
	// JLabel planet = new JLabel("New label");

	// -------//
	MovingOBJ mov;
	// -------//

	boolean run = true;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// -----//
		MovingOBJ mov = new MovingOBJ();
		// -----//

		Parse();

		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
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

		btnNewButton.addActionListener(new ActionListener() {
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
				System.out.println("Click " + position.GetDegree());
				System.out.println("Click2 " + position_1.GetDegree());
				textPane.setText(String.valueOf(Math.sqrt(
						position.GetRadius() * position.GetRadius() + position_1.GetRadius() * position_1.GetRadius()
								- 2 * position.GetRadius() * position_1.GetRadius()
										* Math.cos((position.GetDegree() - position_1.GetDegree()) * Math.PI / 180))));
			}
		});

		// -----------//

		this.mov = mov;
		mov.start();
		// -----------//

	}

	/**
	 * Assign the reading path
	 * @throws IOException 
	 */
	public static void Parse() throws IOException {
		String file1 = "src/input/StellarSystem.txt";
		
		LogFile.WriteError("Start reading data from " + file1);
		
		try {
			readFileByLines(file1);
			
		} catch (IllegalNumberException e) {
			// TODO Auto-generated catch block
			System.out.println("Illegal number found in the file. Please check the input file.");
			e.printStackTrace();
			
			LogFile.WriteError(e);
			
			System.out.println(e.getMessage());
		} catch (SameLabelException e) {
			// TODO: handle exception
			System.out.println("Input file contains same planet. Please check the input file.");
			
			LogFile.WriteError(e);
			
			e.printStackTrace();
		}

	}

	/**
	 * Read the files from .txt Build the CircularOrbit
	 * 
	 * @param fileName
	 */
	private static void readFileByLines(String fileName)  throws IllegalNumberException, SameLabelException {

		BufferedReader reader = null;
		Planet[] planet = new Planet[100];

		try {
			System.out.println("Start reading from files.");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
			String tempString = null;
			int line = 1;

			try {
				while ((tempString = reader.readLine()) != null) {
					String temp2 = tempString.replace(" ", "");
					if (tempString.length() != 0) {
						// System.out.println(tempString);
						System.out.println("line " + line + ":" + tempString);
						if (tempString.startsWith("Stellar")) {
							Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?)>");
							Matcher m = regex.matcher(tempString);
							
							//Every param of regular expression must exist
							assertTrue(m.find());
							if (m.find()) {
								// Add to THE STELLAR
								Stellar stellar = new Stellar(m.group(1), Double.valueOf(m.group(2)),
										Double.valueOf(m.group(3)));
								orbit.AddObjectToCentre(stellar);
							}
							// System.out.println("Found value: " + m.group(0));
						}

						if (tempString.startsWith("Planet")) {
							Pattern regex = Pattern.compile("<(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?)>");
							Matcher m = regex.matcher(tempString);
							
							//Every param of regular expression must exist
							//assertTrue(m.find());
							
							if (m.find()) {
								
								for (Track track : orbit.GetTrack()) {
									if (track.GetName().equals(m.group(1))) {
										throw new SameLabelException(m.group(1));
									}
								}
								
								if (Double.valueOf(m.group(8)) >= 360 || Double.valueOf(m.group(8)) < 0) {
									throw new IllegalNumberException(Double.valueOf(m.group(8)));
								}
								
								if (Double.valueOf(m.group(4)) < 10000 && (m.group(4).contains("e") || m.group(4).contains("E"))) {
									throw new IllegalNumberException(Double.valueOf(m.group(4)));
								}
								
								if (Double.valueOf(m.group(4)) >= 10000 && (!m.group(4).contains("e") || !m.group(4).contains("E"))) {
									throw new IllegalNumberException(Double.valueOf(m.group(4)));
								}
								
								if (Double.valueOf(m.group(6)) < 0) {
									throw new IllegalNumberException(Double.valueOf(m.group(6)));
								}
								
								if (Double.valueOf(m.group(4)) > Double.valueOf(m.group(5))) {
									throw new IllegalNumberException(Double.valueOf(m.group(4)));
								}
								// Add to planet

								planet[line] = new Planet(m.group(1), m.group(2), m.group(3),
										Double.valueOf(m.group(4)), Double.valueOf(m.group(5)),
										Double.valueOf(m.group(6)), m.group(7), Double.valueOf(m.group(8)));

								orbit.AddObjectToTrack(planet[line]);
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

/**
 * The main frame refresh function Implement the actions done in one frame
 * 
 * @author tony
 *
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

			btnNewButton.addActionListener(new ActionListener() {
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
					System.out.println("Click " + position.GetDegree());
					System.out.println("Click2 " + position_1.GetDegree());
					textPane.setText(String.valueOf(Math.sqrt(position.GetRadius() * position.GetRadius()
							+ position_1.GetRadius() * position_1.GetRadius()
							- 2 * position.GetRadius() * position_1.GetRadius()
									* Math.cos((position.GetDegree() - position_1.GetDegree()) * Math.PI / 180))));
				}
			});

			JLabel sun = new JLabel("New label");
			sun.setIcon(new ImageIcon("lib/StellarSystem/sun.png"));
			sun.setBounds(430, 270, 100, 100);
			p.contentPane.add(sun);

			System.out.println("Running");
			// The Label of the background
			p.orbit.Update(p.contentPane);
			p.orbit.Init(p.contentPane);

			// The Label of the background
			JLabel bg = new JLabel("New label");
			bg.setIcon(new ImageIcon("lib/StellarSystem/universe.png"));
			bg.setBounds(0, 0, 960, 640);
			p.contentPane.add(bg);

			p.contentPane.updateUI();
			try {
				sleep(time * 10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}