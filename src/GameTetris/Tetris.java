package GameTetris;

public class Tetris {
	
	private static StartUpMenu menuFrame;
	
	public static void start() {
		
		menuFrame = new StartUpMenu();
		menuFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		start();
	}
}
