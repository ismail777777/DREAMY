package Store;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Coins implements Serializable {

	private int value;
	private transient ImageIcon image;

	public Coins() {
		this.value = 1000;
		this.image = new ImageIcon(getClass().getResource("resources/money.png"));
	}

	public int getValue() {
		return this.value;
	}

	public ImageIcon getIcon() {
		return this.image;
	}

	public void setValue(int n) {
		this.value = n;
	}

	public void updateValue(int n) {
		if (this.value >= n) {
			this.value -= n; 
		}
	}
}
