package Shapes;

public class Point {
	public int x;
	public int y;


	//In case of a Matrix : x means row , y means column
	public Point(int _x , int _y ) {
		x = _x;
		y = _y;
	}
	public Point() {
		x = 0;
		y = 0;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int _y) {
		y = _y;
	}
	public int getX() {
		return x;
	}
	public void setX(int _x) {
		x = _x;
	}
	
}
