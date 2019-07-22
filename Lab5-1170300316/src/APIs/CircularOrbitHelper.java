package APIs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CircularOrbitHelper extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CircularOrbitGUI frame = new CircularOrbitGUI();
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
	public CircularOrbitHelper() {
		setTitle("Orbits");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(107, 65, 221, 27);
		comboBox.addItem("Stellar System");
		comboBox.addItem("Atom Structure");
		comboBox.addItem("Social Network");
		contentPane.add(comboBox);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (String.valueOf(comboBox.getSelectedItem())) {
				case "Stellar System":
					StellarSystemVisual.main(null);
					break;
				case "Atom Structure":
					AtomStructureVisual.main(null);
					break;
				case "Social Network":
					SocialNetworkCircleVisual.main(null);
					break;
				default:
					break;
				}
				
			}
		});
		btnSelect.setBounds(155, 186, 117, 29);
		contentPane.add(btnSelect);
	}
}
