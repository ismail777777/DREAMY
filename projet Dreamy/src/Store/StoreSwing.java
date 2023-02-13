package Store;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.Main;
import Menu.MenuPrincipalSwing;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import Game.Action;

public class StoreSwing extends JFrame {

	private JPanel contentPane;
	public static Coins coins;
	public static Action action1 = new Action();
	public static Action action2 = new Action();
	public static Action action3 = new Action();
	public static Action action4 = new Action();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					coins = new Coins();
					StoreSwing frame = new StoreSwing(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		new StoreSwing();
	}

	/**
	 * Create the frame.
	 */

	public StoreSwing() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					coins = new Coins();
					StoreSwing frame = new StoreSwing(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public StoreSwing(int i) {
		if (i == 1) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, 1746, 1069);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			
			JLabel lblNewLabel_1 = new JLabel("Coins  :");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(50, 31, 85, 27);
			contentPane.add(lblNewLabel_1);

			JLabel labelCoins = new JLabel("");
			labelCoins.setIcon(new ImageIcon(getClass().getResource("resources/money.png")));
			labelCoins.setHorizontalAlignment(SwingConstants.CENTER);
			labelCoins.setBounds(65, 31, 70, 100);
			contentPane.add(labelCoins);
			
			JLabel lblNewLabel_2 = new JLabel(String.valueOf(coins.getValue()));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(179, 31, 58, 27);
			contentPane.add(lblNewLabel_2);

			JButton btnNewButton = new JButton("Acheter pour 850");
			btnNewButton.setBounds(503, 523, 165, 21);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
				coins.updateValue(850);;
				lblNewLabel_2.setText(Integer.toString(   coins.getValue()));
				action1.setState(true);
				action1.setNumber(1);
			}
		});
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Acheter pour 750");
			btnNewButton_1.setBounds(503, 359, 165, 21);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
				coins.updateValue(750);;
				lblNewLabel_2.setText(Integer.toString(   coins.getValue()));
				action2.setState(true);
				action2.setNumber(2);
			}
		});
			contentPane.add(btnNewButton_1);
			
			JButton btnNewButton_5 = new JButton("Acheter pour 1000");
			btnNewButton_5.setBounds(503, 699, 165, 21);
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
				coins.updateValue(1000);;
				lblNewLabel_2.setText(Integer.toString(   coins.getValue()));
				action3.setState(true);
				action3.setNumber(3);
			}
		});
			contentPane.add(btnNewButton_5);
			
			JButton btnNewButton_6 = new JButton("Acheter pour 500");
			btnNewButton_6.setBounds(503, 179, 165, 21);
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
				coins.updateValue(500);;
				lblNewLabel_2.setText(Integer.toString(   coins.getValue()));
				action4.setState(true);
				action4.setNumber(4);
			}
		});
			contentPane.add(btnNewButton_6);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(getClass().getResource("resources/pistache1.png")));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(770, 113, 110, 138);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_7 = new JLabel("");
			lblNewLabel_7.setIcon(new ImageIcon(getClass().getResource("resources/pecan1.png")));
			lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_7.setBounds(789, 273, 70, 162);
			contentPane.add(lblNewLabel_7);
			
			JLabel lblNewLabel_7_1 = new JLabel("");
			lblNewLabel_7_1.setIcon(new ImageIcon(getClass().getResource("resources/cashew1.png")));
			lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_7_1.setBounds(743, 470, 165, 138);
			contentPane.add(lblNewLabel_7_1);
			
			JLabel lblNewLabel_7_2 = new JLabel("");
			lblNewLabel_7_2.setIcon(new ImageIcon(getClass().getResource("resources/almond1.png")));
			lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_7_2.setBounds(727, 631, 200, 162);
			contentPane.add(lblNewLabel_7_2);

			JButton btnRetour = new JButton("Retour");
			btnRetour.setBounds(50, 699, 75, 30);
			btnRetour.addActionListener(new ActionRetour());
			contentPane.add(btnRetour);

			JButton btnNewButton_2 = new JButton("Page suivante");
			btnNewButton_2.setBounds(654, 50, 131, 21);
			btnNewButton_2.addActionListener(new ActionSuivante());
			contentPane.add(btnNewButton_2);
		} else if (i == 2) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 0, 1746, 1069);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			JLabel lblNewLabel_1 = new JLabel("Coins  :");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(50, 31, 85, 27);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel(String.valueOf(coins.getValue()));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(179, 31, 58, 27);
			contentPane.add(lblNewLabel_2);
			
			JButton btnPrecedent = new JButton("Page précédente");
			btnPrecedent.setBounds(654, 50, 148, 21);
			btnPrecedent.addActionListener(new ActionPrecedente());
			contentPane.add(btnPrecedent);
		}
		
	}

	class ActionSuivante implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			dispose();
			StoreSwing frame = new StoreSwing(2);
			frame.setVisible(true);
		}
	}

	class ActionPrecedente implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			dispose();
			StoreSwing frame = new StoreSwing(1);
			frame.setVisible(true);
		}
	}

	class ActionRetour implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			dispose();
			new MenuPrincipalSwing();
		}
	}

}
