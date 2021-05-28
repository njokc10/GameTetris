package GameTetris;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GameArea extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int gridRows = 20;
	private int gridColumns = 10;
	private int gridCellSize = 30;
	
	/** An array of type Color */
	private Color[][] background;
	
	private TetrisBlock block;
	
	private TetrisBlock[] blocksContainer = {new LBlock(), 
			new JBlock(), new ZBlock(), new SBlock(), new TBlock(), 
			new OBlock(), new IBlock()};
	
	
	public GameArea() {
		/* JPanel properties */
		this.setBounds(0, 0, 300, 600);
		this.setLocation(362, 84);
		this.setBackground(new Color(238, 238, 238));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		/* Declared length of array */
		background = new Color[gridRows][gridColumns];
	}
	
	
	/* Creates and draws a new TetrisBlock */
	public void drawBlock() {
		Random rand = new Random();
		block = blocksContainer[rand.nextInt(blocksContainer.length)];
		block.init_position(gridColumns);
	}
	
	public boolean checkGameOver() {
		if (block.getY() < 0) {
			return false;
		}
		return true;
	}
	
	
	/* Moves block down */
	public boolean moveBlockDown() {
		if(checkBottom()) {
			block.moveDown();
			repaint();
			return true;
		}
		return false;
	}
	
	
	/* Moves block one unit right */
	public void moveBlockRight() {
		if(checkRightEdge() && checkGameOver()) {
			block.moveRight();
		}
		repaint();
	}
	
	
	/* Moves block one unit left */
	public void moveBlockLeft() {
		if(checkLeftEdge() && checkGameOver()) {
			block.moveLeft();
		}
		repaint();
	}
	
	
	/* Drops block to the bottom */
	public void dropBlockDown() {
		while(checkBottom()) {
			block.moveDown();
		}
		repaint();
	}
	
	public void rotateBlock() {
		block.rotate();
		
		if(block.getLeftEdge() < 0) {
			block.setX(0);
		} 
		if(block.getRightEdge() >= gridColumns) {
			block.setX( gridColumns - block.getWidth() );
		}
		
		
		
		repaint();
	}
	
	
	/* Checks if the bottom was reached or it's cleaned */
	public boolean checkBottom() {
		if(block.getBottomEdge() == gridRows) {
			return false;
		}
		
		int[][] shape = block.getShape();
		
		for(int column=0; column<block.getWidth(); column++) {
			for(int row=block.getHeight()-1; row>=0; row--){
				if(shape[row][column] != 0) {
					
					int nextSquareX = column + block.getX();
					int nextSquareY = row + block.getY() + 1;
					
					if (nextSquareY < 0) {
						break;
					}
					
					if(background[nextSquareY][nextSquareX] != null) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
	
	/* Checks if the left edge was reached or it's cleaned */
	public boolean checkLeftEdge() {
		if(block.getLeftEdge() == 0) {
			return false;
		}
		
		int[][] shape = block.getShape();
		
		for(int row=0; row<block.getHeight(); row++) {
			for(int column=0; column<block.getWidth(); column++) {
				if(shape[row][column] != 0) {
					
					int nextSquareX = column + block.getX() - 1;
					int nextSquareY = row + block.getY();
					
					if (nextSquareY < 0) {
						break;
					}
					
					if(background[nextSquareY][nextSquareX] != null) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
	
	/* Checks if the right edge was reached or it's cleaned */
	public boolean checkRightEdge() {
		if(block.getRightEdge() == gridColumns) {
			return false;
		}
		
		int[][] shape = block.getShape();
		
		for(int row=0; row<block.getHeight(); row++) {
			for(int column=0; column<block.getWidth(); column++) {
				if(shape[row][column] != 0) {
					
					int nextSquareX = column + block.getX() + 1;
					int nextSquareY = row + block.getY();
					
					if (nextSquareY < 0) {
						break;
					}
					
					if(background[nextSquareY][nextSquareX] != null) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
	
	public int clearLines() {
		
		/* 1) Find completed line */
		boolean fullLine;
		int points = 0;
		
		for(int row=gridRows - 1; row>=0; row--) {
			fullLine = true;
			for(int column=0; column < gridColumns; column++) {
				
				if (background[row][column] == null) {
					fullLine = false;
					break;
				}
			}
			
			/* 2) Remove the line */
			if(fullLine) {
				
				points++;
				
				for(int i=0; i < gridColumns; i++) {
					background[row][i] = null;
				}
				
				/* 3) Shift down the lines above */
				for(int r=row; r>0; r--) {
					
					for(int column=0; column < gridColumns; column++) {
						
						background[r][column] = background[r - 1][column];
					}
				}
				
				/* Clears first line */
				for(int a=0; a < gridColumns; a++) {
					background[0][a] = null;
				}
				
				row++;
				repaint();
			}
		}
		return points;
	}
	
	
	public void moveBlockToBackground() {
		
		int[][] shape = block.getShape();
		int height = block.getHeight();
		int width = block.getWidth();
		
		int xPosition = block.getX();
		int yPosition = block.getY();
		
		Color color = block.getColor();
		
		for(int r = 0; r < height; r++) {
			for(int c = 0; c < width; c++) {
				if(shape[r][c] == 1) {
					background[r+yPosition][c + xPosition] = color;
				}
			}
		}
	}
	
	
	private void drawBlock(Graphics g) {
		
		int height = block.getHeight();
		int width = block.getWidth();
		int[][] shape = block.getShape();
		Color color = block.getColor();
		int init_x = block.getX();
		int init_y = block.getY();
		
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(shape[row][col] == 1) {
					
					int x = (init_x + col) * gridCellSize;
					int y = (init_y + row) * gridCellSize;
					
					drawGridSquare(g, color, x, y);
				}
			}
		}
	}
	
	
	private void drawBackgroundBlocks(Graphics g) {
		Color color;
		
		for(int r = 0; r < gridRows; r++) {
			for(int c = 0; c < gridColumns; c++) {
				
				color = background[r][c];
				
				if(color != null) {
					int x = c * gridCellSize;
					int y = r * gridCellSize;
					
					drawGridSquare(g, color, x, y);
				}
			}
		}
	}
	
	
	private void drawGridSquare(Graphics g, Color color, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, gridCellSize, gridCellSize);
		g.setColor(Color.black);
		g.drawRect(x, y, gridCellSize, gridCellSize);
	}
	

	/* No need to call (it's done by another system). */
	@Override
	protected void paintComponent(Graphics g) {
		/** Calling method from superclass. */
		super.paintComponent(g);
		
		/* Temporary GRID (No needed for final version) */ 
		/*
		for(int y = 0; y < gridRows; y++) {
			for(int x = 0; x < gridColumns; x++) {
				g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
			}
		}
		*/
		drawBackgroundBlocks(g);
		drawBlock(g);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
