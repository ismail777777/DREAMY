package Menu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import Game.Main;
import Store.Coins;
import Store.StoreSwing;
import Sound.SoundHandler;

public class MenuPrincipalSwing {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel contentPane2;
	private Main main;
	private SoundHandler soundHandler;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MenuPrincipalSwing();
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipalSwing() {
		frame = new JFrame();
		Dimension Dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1752, 1103);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Lancer une partie");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnStart.setBounds(650, 300, 200, 40);
		btnStart.addActionListener(new ActionStart());
		contentPane.add(btnStart);
		
		JButton btnInvite = new JButton("Inviter un ami");
		btnInvite.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnInvite.setBounds(650, 380, 200, 40);
		btnInvite.addActionListener(new ActionInvite());
		contentPane.add(btnInvite);
		
		JButton btnStore = new JButton("Boutique");
		btnStore.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnStore.setBounds(650, 460, 200, 40);
		btnStore.addActionListener(new ActionStore());
		contentPane.add(btnStore);
		Coins c = null;
	    try {
	    	FileInputStream fileIn = new FileInputStream("tmp/coin.ser");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        c = (Coins) in.readObject();
	        in.close();
	        fileIn.close();
	    } catch (IOException i) {
	       i.printStackTrace();
	    } catch (ClassNotFoundException c1) {
	       c1.printStackTrace();
	    }
	    Store.StoreSwing.coins = c;
	    System.out.println("Coins: " + c.getValue());
		
		JButton btnParam = new JButton("Paramètres et réglages");
		btnParam.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnParam.setBounds(650, 540, 200, 40);
		btnParam.addActionListener(new ActionParam());
		contentPane.add(btnParam);
		//soundHandler.startSound("resources/sound.wav");
		frame.pack();
		frame.setMaximumSize(Dim);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	class ActionStart implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			frame.dispose();
			new AvatarSelection();
			/*Main main = new Main();
			main.setVisible(true);*/
		}
	}
	class ActionInvite implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
		}
	}
	class ActionStore implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			frame.dispose();
			new StoreSwing();
		}
	}
	class ActionParam implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ev) {
			frame.dispose();
		}
	}
}
