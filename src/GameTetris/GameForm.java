package GameTetris;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameForm extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private GameArea g;
	private GameInfo gameInfo;
	private Button btnMainMenu;
	
	public GameForm(StartUpMenu startUpMenu) {
		
		/* JFrame properties */
		this.setSize(new Dimension(1024, 768));
		this.setTitle("Anej&Mai GameTetris");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		/* Creating new GameArea */
		g = new GameArea();
		this.add(g);
		
		/* Creating new Info board */
		gameInfo = new GameInfo();
		this.add(gameInfo);
		
		/* Creating new Info board */
		btnMainMenu = new Button("Main Menu", 50, 119);
		this.add(btnMainMenu);
		
		btnMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				startUpMenu.setVisible(true);
			}
		});

		
		startGame();
		
		controls();
	}
	
	public void controls() {
		InputMap inputs = this.getRootPane().getInputMap();
		ActionMap actions = this.getRootPane().getActionMap();
		
		inputs.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		inputs.put(KeyStroke.getKeyStroke("LEFT"), "left");
		inputs.put(KeyStroke.getKeyStroke("UP"), "up");
		inputs.put(KeyStroke.getKeyStroke("DOWN"), "down");
		
		actions.put("right", new AbstractAction() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.moveBlockRight();
			}
		});
		
		actions.put("left", new AbstractAction() {
				
			private static final long serialVersionUID = 1L;
				
			@Override
			public void actionPerformed(ActionEvent e) {
				g.moveBlockLeft();
			}
		});
		
		actions.put("up", new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				g.rotateBlock();
			}
		});
		
		actions.put("down", new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				g.dropBlockDown();
			}
		});
	}
	
	
	public void startGame() {
		new GameThread(g, this, gameInfo).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new GameForm().setVisible(true);
	}
}
