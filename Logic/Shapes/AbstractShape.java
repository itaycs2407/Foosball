package Shapes;


public abstract class AbstractShape {
	protected Point point;

	public double GetShapeArea() {
		return 0.0;
	}
	
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point _point) {
		point = _point;
	}
	
	
}
