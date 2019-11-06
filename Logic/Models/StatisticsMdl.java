package Models;

import Controllers.StatisticCtrl;
import Utils.DataUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StatisticsMdl {

    private String[] colName = {"GameID","Player1 Name","Player2 Name","GameOver","Score","GameDate"};
    LoadTableData statisticsData;
    //LoadTableData statisticsDataObject;


    public StatisticsMdl(){

        //LoadTableData testObject = statisticsData;
       // statisticsData.getRowCount()
        setTableData();

    }

    private void setTableData(){
        statisticsData = new LoadTableData(getNumOfStoredGames()+1,6,colName);
        int row=0, col=0;
        statisticsData.setValueAt(0,col,colName[col++]);
        statisticsData.setValueAt(0,col,colName[col++]);
        statisticsData.setValueAt(0,col,colName[col++]);
        statisticsData.setValueAt(0,col,colName[col++]);
        statisticsData.setValueAt(0,col,colName[col++]);
        statisticsData.setValueAt(0,col,colName[col++]);
        col=0;
        for (row=0; row<DataUtils.dal.GetGames().size();row++)
        {
            if (row < DataUtils.dal.GetGames().size()) {
              int gameID1 = getGameID(row);
               String date = getGameDate(row);
               String pl1Name = getNamePl1(row);
               String pl2Name = getNamePl2(row);
               boolean isOver = getIsOver(row);
               String gameScore = getGameScore(row);

               statisticsData.setValueAt(row +1,col,String.valueOf(gameID1));
               statisticsData.setValueAt(row + 1,col+1,pl1Name);
               statisticsData.setValueAt(row + 1,col+2,pl2Name);
               statisticsData.setValueAt(row + 1,col+3,String.valueOf(isOver));
               statisticsData.setValueAt(row + 1,col+4,gameScore);
                statisticsData.setValueAt(row +1,col+5,date);
            }
        }
    }
    public void parseData(){
        setTableData();
    }
    public LoadTableData getStatTable(){
        return statisticsData;
    }
    public int getNumOfStoredGames (){
        return DataUtils.dal.GetGames().size();
    }
    public String getNamePl1(int i){
        String temp = DataUtils.dal.GetGames().get(i).GetHomePlayer().Name;
        return temp;
    }
    public String getNamePl2(int i){
        return DataUtils.dal.GetGames().get(i).GetHomePlayer().Name;
    }
    public boolean getIsOver(int i){
        return DataUtils.dal.GetGames().get(i).GetIsGameOver();
    }


    public int getGameID(int i){
        return DataUtils.dal.GetGames().get(i).GetID();
    }
    public String getGameDate(int i){
        Date gameDate = DataUtils.dal.GetGames().get(i).GetDate();
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(gameDate);
    }
    public String getGameScore(int i){
        Result currentGameResult = DataUtils.dal.GetGames().get(i).GetScore();
        int homeScore = currentGameResult.GetHomeTeamScore();
        int awayScore = currentGameResult.GetAwayTeamScore();
        String gameScoreStr =homeScore + " - "+ awayScore;
        return gameScoreStr;
    }


    public void removeGame(int game) {

        int currentRowNum = statisticsData.getRowCount();
        statisticsData.setRowsCount( currentRowNum-1 );
        DataUtils.dal.DeleteGame(game);
    }

    public void saveAction() {
        DataUtils.dal.Save();
    }
}
