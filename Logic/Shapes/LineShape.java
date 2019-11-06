package Shapes;

public class LineShape extends AbstractShape{
	public Point start,end;
	
	public LineShape(Point x, Point y) {
		start = x;
		end = y;
	}
	
	public double GetShapeArea() {
		double len = Math.sqrt((Math.pow((start.x - end.x), 2) + Math.pow((start.y - end.y),2)));
		return len;
	}
	

}
