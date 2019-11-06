package Controllers;

import Models.*;
import Shapes.Point;
import Utils.DataUtils;
import Utils.FileUtils;
import Utils.Tuple;

import java.io.IOException;
import java.util.List;

public class GameController {
	private Game game;


    public void onStart(){game = new Game();

	game.setStartPosition();}

	public void onStart(Game gameToLoad){
		game = gameToLoad;
		game.setStartPosition();
	}

	public Field getField() {
		Field f = game.getField();
		return f;
	}

	public List<Tuple<Point,FieldCell>> getStaticSpecialCells(){return getField().getStaticSpecialCells();}

	public Ball moveBall(){
		Ball b= game.moveBall();
		return b;
	}

	public Team moveTeam(char keyPressed){
	    if(keyPressed == DataUtils.config.config.gameSettingsConfig.player1DownButton.charAt(0))
	        return game.moveTeam(Direction.DOWN, true);
		if(keyPressed == DataUtils.config.config.gameSettingsConfig.player1UpButton.charAt(0))
	        return game.moveTeam(Direction.UP, true);
		if(keyPressed == DataUtils.config.config.gameSettingsConfig.player2DownButton.charAt(0))
	        return game.moveTeam(Direction.DOWN, false);
        if(keyPressed == DataUtils.config.config.gameSettingsConfig.player2UpButton.charAt(0))
        	return game.moveTeam(Direction.UP, false);
        return null;
	}

	public Tuple<Team,Team> getTeams(){return game.getTeams();}

	public boolean checkIfGoal(){
		return game.checkIfGoal();
	}

	public String getScore() {
		return game.getScoreAsString();
	}

	public String getTeamPhoto(boolean  isHome) throws IOException {
		if (isHome)
			return reslovePhotoPath(DataUtils.config.config.gameSettingsConfig.player1Color);
		return reslovePhotoPath(DataUtils.config.config.gameSettingsConfig.player2Color);
	}

	public boolean saveGame(boolean isOver) {
		return game.saveGame(isOver);
	}



	private String reslovePhotoPath(String teamColor) throws IOException {
		switch (teamColor.toUpperCase()){
			case "BLUE":
				return FileUtils.relativePathToFullPath("/Images/blueKit.png");
			case "YELLOW":
				return FileUtils.relativePathToFullPath("/Images/BluePlayer.png");
			case "RED":
				return FileUtils.relativePathToFullPath("/Images/RedPlayer.png");
		}
		return null;
	}


	public boolean isGameOver() {
		return game.isGameOver();
	}

	public boolean stopGame(char keyChar) {
		if (game.isGameOver())return false;
	    return keyChar == DataUtils.config.config.gameSettingsConfig.stopBtn.charAt(0);
	}

    public String getTeamName(boolean isHomeTeam) {
        if (isHomeTeam)return DataUtils.config.config.gameSettingsConfig.player1Name;
        return DataUtils.config.config.gameSettingsConfig.player2Name;
    }

    public int getGameTime() {
        return DataUtils.config.config.gameSettingsConfig.gameTimeInMinutes;
    }

    public boolean isTimeUp() {
	    return game.isTimeUp();
    }
}
