package GameTetris;

public class GameThread extends Thread {
	
	private GameArea g;
	private GameInfo gameInfo;
	private int score = 0;
	
	public GameThread(GameArea g, GameForm gf, GameInfo gameInfo) {
		this.g = g;
		this.gameInfo = gameInfo;
	}
	
	@Override
	public void run() {
		while(true) {
			
			g.drawBlock();
			
			while(g.moveBlockDown()) {
				try {
					/* It sleeps for .500s */
					Thread.sleep(500);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (!g.checkGameOver()) {
				System.out.println("Game Over");
				break;
			}
			
			g.moveBlockToBackground();
			score += g.clearLines();
			gameInfo.updateScore(score);
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
