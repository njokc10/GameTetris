package GameTetris;

import java.awt.Font;

import javax.swing.JLabel;

public class GameInfo extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	private int s = 0;
	
	public GameInfo() {
		
		/* JLabel properties */
		this.setFont(new Font("Courier", Font.BOLD, 24));
		this.setBounds(762, 84, 181, 100);
		this.setVisible(true);
		
		updateScore(s);
		
	}
	
	
	public void updateScore(int s) {
		setText("Score: " + s);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
