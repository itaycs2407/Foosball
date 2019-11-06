package Shapes;


public class CircleShape extends AbstractShape {
	public double radius;
	public double area;
	
	public CircleShape(Point _point, double _radius) {
		point = _point;
		radius = _radius;
		area = 0;
	}
	
	public double GetShapeArea() {
		area = radius * radius * Math.PI;
		return area;
	}
	
	
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double _radius) {
		radius = _radius;
	}
}
