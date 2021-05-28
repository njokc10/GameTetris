package GameTetris;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class StartUpMenu extends JFrame { 
	
	private static final long serialVersionUID = 1L;
	
	private Button startBtn;
	private Button exitBtn;
	
	public StartUpMenu() {
		
		/* JFrame properties */
		this.setSize(new Dimension(400, 300));
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		StartUpMenu startUpMenu = this;
		
		startBtn = new Button("Start Game", 109, 70);
		exitBtn = new Button("Quit", 109, 140);
		
		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameForm(startUpMenu).setVisible(true);
				setVisible(false);
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/* Closing entire process. */
				System.exit(0); 
			}
		});
		
		this.add(startBtn);
		this.add(exitBtn);
		
	}
}
