package Models;

import Utils.DataUtils;

public class GameSettingsMdl {

    public void initNewGame(String ply1Name, String ply2Name,String ply1Formation, String ply2Formation, String ply1TeamColor,String ply2TeamColor,int gameTime ,int gameMaxScore )
    {
        DataUtils.config.config.gameSettingsConfig.maxScore = gameMaxScore;
        DataUtils.config.config.gameSettingsConfig.gameTimeInMinutes = gameTime;
        DataUtils.config.config.gameSettingsConfig.player1Color = ply1TeamColor;
        DataUtils.config.config.gameSettingsConfig.player2Color = ply2TeamColor;
        DataUtils.config.config.gameSettingsConfig.player1Formation = ply1Formation;
        DataUtils.config.config.gameSettingsConfig.player2Formation = ply2Formation;
        DataUtils.config.config.gameSettingsConfig.player1Name = ply1Name;
        DataUtils.config.config.gameSettingsConfig.player2Name = ply2Name;
        DataUtils.config.config.gameSettingsConfig.startingResult= new Result(0,0);


    }
}
