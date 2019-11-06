package Models;
import java.beans.EventSetDescriptor;
import java.util.*;

import Shapes.AbstractShape;
import Shapes.Point;
import Shapes.RectShape;
import Utils.Tuple;

//The actual player listed in field
public class TeamPlayersRow implements ITeamPlayersRow{
	
	public List<RectShape> players;
	private int xPosition;
	private int space;
	private Tuple<MoveUpCommand,Observable> moveObservables;

	public TeamPlayersRow(int firstYPosition, int startingXPosition,RectShape size, int spaceBetween, int count){
		players = new ArrayList<>();
		xPosition = startingXPosition;
		space =spaceBetween;

		for (int i = 0 ; i < count*space;i+=space) {
			RectShape rectShape = new RectShape(size.getWidth(), size.getLength());
			rectShape.setPoint(new Point(xPosition + i, firstYPosition));
			players.add(rectShape);
		}
	}

		public List<Tuple<Integer, Integer>> getRowPositions(){
		List<Tuple<Integer,Integer>> positions = new ArrayList<>();
		for (RectShape player :
				players) {
			for (int i = player.getPoint().getX();i<player.getPoint().getX()+player.getLength();i++)
				for (int j =  player.getPoint().getY();j< player.getPoint().getY() + player.getWidth();j++)
					positions.add(new Tuple<>(i, j));
		}
		return positions;
	}


	// check for the direction and update the row position
	public void Move(Direction direction, int moveSize){
		int directionVal = direction.getValue();
		if(directionVal != 90 && directionVal!= 270)return;
		for (AbstractShape player :
				players) {
			Point newPos = new Point(player.getPoint().getX() + moveSize, player.getPoint().getY());
			player.setPoint(newPos);
		}
	}

	@Override
	public void update(Object o) {
		int mul =1;
		if((Direction)o == Direction.UP)mul = -1;
		Move((Direction)o,15*mul);
	}

	@Override
	public List<RectShape> getPlayers() {
		return players;
	}
}
