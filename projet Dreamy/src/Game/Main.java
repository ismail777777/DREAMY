package Game;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import Menu.MenuPrincipalSwing;
import Store.Coins;

public class Main extends JFrame {
	
	public Main(int i) {
		initialize(i);
	}

	public void initialize(int i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dreamy GO");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		pack();
		setSize(1500,757);
		setBounds(0,0, 1500, 757);
		if (i == 1) {
			GameScreen gameScreen = new GameScreen();
			add(gameScreen);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					gameScreen.start();
				}
			});
		} else if (i == 2) {
			GameScreen2 gameScreen2 = new GameScreen2();
			add(gameScreen2);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					gameScreen2.start();
				}
			});
		}
	}
	public static void main(String args[]) {
		//Main main = new Main();
		//main.setVisible(true);
		new MenuPrincipalSwing();
	}
}