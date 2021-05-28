package GameTetris;

import javax.swing.JButton;

public class Button extends JButton {
	
	private static final long serialVersionUID = 1L;

	public Button(String name, int x, int y) {
		this.setBounds(x, y, 181, 30);
		this.setText(name);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
