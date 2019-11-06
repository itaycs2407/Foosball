package Shapes;

public class RectShape extends AbstractShape{
	public int length,width,area;

	public RectShape( int _width, int _length) {
		length = _length;
		width = _width;
		area = 0;
	}
	
	public double GetShapeArea() {
		area = length * width;
		return area;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int _length) {
		length = _length;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int _width) {
		width = _width;
	}
}
