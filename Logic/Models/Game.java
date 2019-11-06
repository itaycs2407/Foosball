package Models;

import Shapes.CircleShape;
import Shapes.Point;
import Shapes.RectShape;
import Utils.DataUtils;
import Utils.Tuple;

import java.util.*;

import static Models.FieldCell.*;

public class Game {
	private final char seperatingFormChar = ' ';
	private final List<FieldCell> SETDIRECTIONTYPES = Arrays.asList(PADDING, LEFTPLAYER, RIGHTPLAYER);
	private Ball gameBall;
	public Field gameField;
	private Team homeTeam;
	private Team awayTeam;
	private Result gameScore;
	private List<Integer> directionsAsInt;
	private Point ballPoint;
	private int time;

	public Game(){
	    time = DataUtils.config.config.gameSettingsConfig.gameTimeInMinutes*6000;
		ballPoint = new Point(300,250);
		directionsAsInt = new ArrayList<>();
		for (int i=0; i<8; i++) {directionsAsInt.add(new Integer(i*45));}
		gameBall = new Ball(Color.BLUE,new CircleShape(ballPoint,1),getRandomDirection(Direction.LEFT,directionsAsInt),1);
		gameField = new Field(DataUtils.config.config.gameSettingsConfig.fieldRows,DataUtils.config.config.gameSettingsConfig.fieldColumns);
        homeTeam = new Team(new ArrayList<>());
        awayTeam = new Team(new ArrayList<>());
		homeTeam = initTeam(homeTeam,true);
		awayTeam = initTeam(awayTeam, false);
		gameScore = DataUtils.config.config.gameSettingsConfig.startingResult;
	}





	private Team initTeam(Team team,boolean isHomeTeam) {
		List<Integer> form;
		int startingY,startingX;
		if (isHomeTeam){
			form  = convertConfigFormationToInts(DataUtils.config.config.gameSettingsConfig.player1Formation);
			startingX = 100;
		}
		else {
			startingX = 250;
			form  = convertConfigFormationToInts(DataUtils.config.config.gameSettingsConfig.player2Formation);
		}
		for (int row : form) {
		    startingY = 400 - row * 100;
			team.addTeamRow(initRow(row,startingX,startingY,150));
			startingX += 400;
		}
	return team;
	}

	private List<Integer> convertConfigFormationToInts(String formation) {
		try{
	    String[] rows = formation.split(String.valueOf(seperatingFormChar));
            List<Integer> ints = new ArrayList<>();
            for (String row : rows) {
                ints.add(Integer.parseInt(row));
            }
            return ints;}
	    catch (Exception e){
		    System.out.print(e);
        return null;
		}

    }

	public boolean saveGame(boolean isOver){
		Player homeP = new Player(DataUtils.config.config.gameSettingsConfig.player1Name,DataUtils.config.config.gameSettingsConfig.player1Formation, getColor(DataUtils.config.config.gameSettingsConfig.player1Color));
		Player awayP = new Player(DataUtils.config.config.gameSettingsConfig.player2Name,DataUtils.config.config.gameSettingsConfig.player2Formation, getColor(DataUtils.config.config.gameSettingsConfig.player2Color));
		Result result = new Result(getResult());
		GameToSave gts = new GameToSave(DataUtils.dal.GetFreeId(), new Date(), homeP, awayP, result, isOver);
		DataUtils.dal.Insert(gts);
		DataUtils.dal.Save();
		return true;
	}

	public Result getResult(){return gameScore;}

	public Field getField() {return gameField;}

	public boolean isGameOver() {
		return Math.max(getResult().HomeTeamScore, getResult().AwayTeamScore) >= DataUtils.config.config.gameSettingsConfig.maxScore;
	}

	public boolean setStartPosition(){
		try{
			gameBall.ResetPosition(new Point(300,250));
			homeTeam.ResetPosition();
			awayTeam.ResetPosition();
			gameBall.setCurrDirection(getRandomDirection(gameBall.currDirection,directionsAsInt) );
			gameField.addTeam(homeTeam,true);
			gameField.addTeam(awayTeam,false);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public Tuple<Team,Team> getTeams(){
		return new Tuple<>(homeTeam, awayTeam);
	}
	//moves game ball
	public Ball moveBall(){
		try {
			gameBall.Move();
			ballPoint.setX(setOnOutOfRange(gameBall.ballShape.getPoint().getX(),gameField.get_height()));
			ballPoint.setY(setOnOutOfRange(gameBall.ballShape.getPoint().getY(),gameField.get_width()));
			gameBall.ballShape.setPoint(ballPoint);
			//TODO: replace directions for rand with a real list / function to set directions correctly
			List<Integer> directionsForRand = Arrays.asList((gameBall.currDirection.getValue()+45)%360,(gameBall.currDirection.getValue()-45)%360);
			if (didBallHitProps(gameBall)) gameBall.setCurrDirection(getRandomDirection(gameBall.currDirection,directionsForRand) );
			return gameBall;
		}
		catch (Exception e){
			ballPoint = new Point(300,250);
			gameBall.ResetPosition(ballPoint);
			return gameBall;
		}
	}
	//gets direction,and boolean of which team - and move the asked team's players by the direction given
	public Team moveTeam(Direction direction, boolean isHomeTeam){
		int dir = 0;
		Team team;
		if (direction == Direction.UP)dir =-15;
		else dir =15;
		if (isHomeTeam) team = homeTeam;
		else team = awayTeam;
		if(didTeamHitWall(team,dir))return team;
		gameField.removeTeam(team);
		team.Move(direction,dir);
		gameField.addTeam(team,true);
		return team;
	}

	public String getScoreAsString() {return (gameScore.HomeTeamScore) + " - " + gameScore.AwayTeamScore;}

	public boolean checkIfGoal() {
		if(didHitSpecCell(gameBall,LEFTGOAL)){
			setStartPosition();
			gameScore.SetAwayTeamScore(gameScore.GetAwayTeamScore() + 1);
			return true;
		}
		if(didHitSpecCell(gameBall,RIGHTGOAL)){
			setStartPosition();
			gameScore.SetHomeTeamScore(gameScore.GetHomeTeamScore() + 1);
			return true;
		}
		return false;
	}

	private Color getColor(String color) {
		color = color.toUpperCase();
		return Color.valueOf(color);
	}

	private boolean didHitSpecCell(Ball ball,FieldCell cell) {
		FieldCell testCell = gameField.getSpecificCell(ball.ballShape.getPoint().getX(),ball.ballShape.getPoint().getY());
		return testCell == cell;
	}
	//direction could be 1 to check if hit buttom or -1 for top
	private boolean didTeamHitWall(Team team, int moveSize) {
		for (ITeamPlayersRow row :
				team.getPlayers()) {
			RectShape lowest = row.getPlayers().get(0);
			RectShape highest = row.getPlayers().get(row.getPlayers().size()-1);
			FieldCell testedLowestCell = gameField.getSpecificCell(lowest.getPoint().getX() + moveSize,lowest.getPoint().getY());
			if(testedLowestCell == PADDING || testedLowestCell == null )return true;
			FieldCell testedHighestCell = gameField.getSpecificCell(highest.getPoint().getX() +highest.getLength() +  moveSize,highest.getPoint().getY());
			if(testedHighestCell  == PADDING || testedHighestCell == null )return true;
		}
		return false;
	}
	//gets number of players for row , and a starting position.
	//it returns a list og players row it created
	private List<ITeamPlayersRow> setPlayers(int playersCount,int startingYPos) {
		List<ITeamPlayersRow> asList = new ArrayList<>();
		asList.add(new TeamPlayersRow(startingYPos ,70,new RectShape(75,75),200,playersCount));
		asList.add(new TeamPlayersRow(startingYPos + 350,200,new RectShape(75,75),170,playersCount-1));
		return asList;
	}

	private ITeamPlayersRow initRow(int rowPlayersCount,int startingXPos, int startingYPos, int spaceBetween){
		ITeamPlayersRow row = new TeamPlayersRow(startingXPos, startingYPos,new RectShape(75,75),spaceBetween,rowPlayersCount);
		return row;
	}
	//Gets specific direction, list of Directions. returns random direction from the list which is not equal to currDirection
	private Direction getRandomDirection(Direction currDirection,List<Integer> directionValues) {
		Collections.shuffle(directionValues);
		if(currDirection == Direction.getValue(directionValues.get(0)))return Direction.getValue(directionValues.get(1));
		return Direction.getValue(directionValues.get(0));
	}
	//checks if current position of point is Equal to on of SETDIRECTIONTYPES, if so returns true
	private boolean didBallHitProps(Ball ball){
		FieldCell testCell = gameField.getSpecificCell(ball.ballShape.getPoint().getX(),ball.ballShape.getPoint().getY());
		if (SETDIRECTIONTYPES.contains(testCell))return true;
		return false;
	}
	//gets an index (represents an x or a y of Point)
	// checks if the index given is bigger than limit or a negative number - if so correct the index back to the bounds recursively
	private int setOnOutOfRange(int index , int limit){
		if (index < limit && index>=0)
			return index;
		if (index>0)return setOnOutOfRange(index -1, limit);
		return setOnOutOfRange(index +1, limit);
	}


    public boolean isTimeUp() {
        return time-- <= 0;
    }
}
