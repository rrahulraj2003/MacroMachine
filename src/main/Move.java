package main;

public class Move {
	
	public int id;
	
	private int x;
	private int y;
	
	private int value;
	
	//1 Click, 3 Hover, kinda 4 Drag
	public Move(int i, double X, double Y) {
		id = i;
		x = (int) Math.round(X);
		y = (int) Math.round(Y);
	}
	
	public int getX() { return x; }
	public void setX(int s) { x = s; }
	
	public int getY() { return y; }
	public void setY(int s) { y = s; }
	
	
	//2 Wait, 5 Keystroke
	public Move(int i, int val) {
		id = i;
		value = val;
	}
	
	public int getValue() { return value; }
	public void setValue(int s) { value = s; }

}
