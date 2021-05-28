package GameTetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock {
	
	private int[][] shape;
	private Color color;
	private int x, y;
	private int [][][] shapes;
	private int currShape;
	
	private Color[] blockColors = {new Color(3, 65, 174), new Color(114, 203, 59), new Color(255, 213, 0), new Color(255, 151, 28), new Color(255, 50, 19)};
	
	public TetrisBlock(int[][] shape) {
		this.shape = shape;
		
		initShapes();
	}
	
	public TetrisBlock() {
		// TODO Auto-generated constructor stub
	}
	
	/* Setter methods */
	public void initShapes() {
		shapes = new int[4][][];
		
		for(int i=0; i<4; i++) {
			
			/* Swapping rows and columns */
			int rows = shape[0].length;
			int columns = shape.length;
			
			shapes[i] = new int[rows][columns];
			
			for(int a=0; a<rows; a++) {
				
				for(int b=0; b<columns; b++) {
					shapes[i][a][b] = shape[columns - 1 - b][a];
				}
			}
			
			shape = shapes[i];
		}
	}
	
	public void init_position(int gridWidth) {
		
		Random rand = new Random();
		
		currShape = rand.nextInt( shapes.length );
		shape = shapes[currShape];
		
		
		y = -getHeight();
		x = rand.nextInt(gridWidth - getWidth());
		
		color = blockColors[rand.nextInt(blockColors.length)];
	}
	
	/* Bad names */
	public void setX(int a) {
		x = a;
	}
	
	public void setY(int b) {
		y = b;
	}
	
	/* Getter methods */
	public int[][] getShape() {
		return shape;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getWidth() {
		return shape[0].length;
	}
	
	public int getHeight() {
		return shape.length;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int moveDown() {
		return y++;
	}
	
	public int moveLeft() {
		return x--;
	}
	
	public int moveRight() {
		return x++;
	}
	
	public int getBottomEdge() {
		return getY() + getHeight();
	}
	
	public int getLeftEdge() {
		return getX();
	}
	
	public int getRightEdge() {
		return getX() + getWidth();
	}
	
	public void rotate() {
		currShape++;
		if(currShape > 3) {
			currShape = 0;
		}
		shape = shapes[currShape];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
