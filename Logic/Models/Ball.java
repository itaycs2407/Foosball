package Models;

import Shapes.AbstractShape;
import Shapes.Point;

public class Ball {
	private int moveSize;
	
	public Color color;
	public AbstractShape ballShape;
	public Direction currDirection;

	public Ball(Color _color, AbstractShape ball, Direction startingDir, int _moveSize) {
		color = _color;
		ballShape = ball;
		ballShape.setPoint(ball.getPoint());
		currDirection = startingDir;
		moveSize = _moveSize;
	}

	public void Move() {
		int dir;
		try {
			dir = currDirection.getValue();
		}
		catch (Exception e){
			System.out.println(e);
			return;
		}
		switch (dir){
			case 0:
				ballShape.getPoint().setX((ballShape.getPoint().getX() + moveSize));
				break;
			case 45:
				ballShape.getPoint().setX((ballShape.getPoint().getX() + moveSize));
				ballShape.getPoint().setY((ballShape.getPoint().getY() + moveSize));
				break;
			case 90:
				ballShape.getPoint().setY((ballShape.getPoint().getY() + moveSize));
				break;
			case 135:
				ballShape.getPoint().setX((ballShape.getPoint().getX() + (moveSize * (-1))));
				ballShape.getPoint().setY((ballShape.getPoint().getY() + moveSize));
				break;
			case 180:
				ballShape.getPoint().setX((ballShape.getPoint().getX() + (moveSize * (-1))));
				break;
			case 225:
				ballShape.getPoint().setX((ballShape.getPoint().getX() + (moveSize * (-1))));
				ballShape.getPoint().setY((ballShape.getPoint().getY() + (moveSize*(-1))));
				break;
			case 270:
				ballShape.getPoint().setY((ballShape.getPoint().getY() + (moveSize*(-1))));
				break;
			case 315:
				ballShape.getPoint().setX((ballShape.getPoint().getX() + moveSize));
				ballShape.getPoint().setY((ballShape.getPoint().getY() + (moveSize*(-1))));
				break;
			default:
				break;
		}
	}

	public void setCurrDirection(Direction direction){
		if(direction == null){
			return;
		}
		currDirection = direction;
	}

	public void ResetPosition(Point point){
		ballShape.setPoint(point);
	}
	
	
}