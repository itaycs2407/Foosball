package Controllers;

import java.util.Timer;

import Models.Color;
import Models.GameSettingsMdl;
import Models.Player;

public class GameSettingsController {

	private GameSettingsMdl gameSettingMdl = new GameSettingsMdl();

	public void initNewGame(String ply1Name, String ply2Name,String ply1Formation, String ply2Formation, String ply1TeamColor,String ply2TeamColor,String gameTime ,String gameMaxScore){
		gameSettingMdl.initNewGame(ply1Name,ply2Name,ply1Formation,ply2Formation,ply1TeamColor,ply2TeamColor,Integer.parseInt(gameTime),Integer.parseInt(gameMaxScore));
	}
}
