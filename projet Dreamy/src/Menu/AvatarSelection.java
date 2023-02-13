package Menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Main;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AvatarSelection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvatarSelection frame = new AvatarSelection();
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
	public AvatarSelection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choisissez un avatar pour cette partie");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(460, 100, 605, 139);
		contentPane.add(lblNewLabel);
		
		JButton btnAvatar1 = new JButton("Avatar 1");
		btnAvatar1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAvatar1.setBounds(460, 630, 180, 44);
		btnAvatar1.addActionListener(new ActionAvatar2());
		contentPane.add(btnAvatar1);
		
		JButton btnAvatar2 = new JButton("Avatar 2");
		btnAvatar2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAvatar2.setBounds(930, 630, 180, 44);
		btnAvatar2.addActionListener(new ActionAvatar1());
		contentPane.add(btnAvatar2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(AvatarSelection.class.getResource("/Menu/resources/avatar1.png")));
		lblNewLabel_1.setBounds(430, 290, 204, 286);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(AvatarSelection.class.getResource("/Menu/resources/avatar2.png")));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(908, 290, 204, 286);
		contentPane.add(lblNewLabel_1_1);
		setVisible(true);
	}

	class ActionAvatar2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			dispose();
			Main main = new Main(1);
			main.setVisible(true);
		}
	}

	class ActionAvatar1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			dispose();
			Main main = new Main(2);
			main.setVisible(true);
		}
	}
}
