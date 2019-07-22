package src.P3;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MyChessAndGoGame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputPlayerName;
	private JTextField txtInputPlayerName_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyChessAndGoGame frame = new MyChessAndGoGame();
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
	public MyChessAndGoGame() {
		setTitle("Game Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Chess Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newChessGame();
			}
		});
		btnNewButton.setBounds(49, 134, 160, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton2 = new JButton("Go Game");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGoGame();
			}
		});
		btnNewButton2.setBounds(49, 201, 160, 60);
		contentPane.add(btnNewButton2);
		
		txtInputPlayerName = new JTextField();
		txtInputPlayerName.setText("Input Player1 Name");
		txtInputPlayerName.setBounds(49, 19, 173, 26);
		contentPane.add(txtInputPlayerName);
		txtInputPlayerName.setColumns(10);
		
		txtInputPlayerName_1 = new JTextField();
		txtInputPlayerName_1.setText("Input Player2 Name");
		txtInputPlayerName_1.setBounds(49, 57, 173, 26);
		contentPane.add(txtInputPlayerName_1);
		txtInputPlayerName_1.setColumns(10);
	}
	
	private void newChessGame() {
		Game.newChessGame();
	}
	
	private void newGoGame() {
		Game.newGoGame();
	}
}
