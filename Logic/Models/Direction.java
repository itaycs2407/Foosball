package Models;

public enum Direction {
	UP(90),
	DOWN(270),
	LEFT(180),
	RIGHT(0),
	UPRIGHT(45),
	UPLEFT(135),
	DOWNRIGHT(315),
	DOWNLEFT(225);
	
	private int value;    

	  private Direction(int value) {
	    this.value = value;
	  }

	  public int getValue() {
	    return value;
	  }

	  public static  Direction getValue(int enumVal) {
	  	switch (enumVal){
			case 0:return Direction.RIGHT;
			case 45:return Direction.UPRIGHT;
			case 90:return Direction.UP;
			case 135:return Direction.UPLEFT;
			case 180:return Direction.LEFT;
			case 225:return Direction.DOWNLEFT;
			case 270:return Direction.DOWN;
			case 315:return Direction.DOWNRIGHT;
			default:return null;
		}
	  }


}
