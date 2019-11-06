package Models;

import java.util.Date;

public class GameToSave {
    int ID;
    java.util.Date Date;
    Result Score;
    boolean IsGameOver;
    Player HomePlayer;
    Player AwayPlayer;

    public GameToSave(int id, java.util.Date date, Player homePlayer, Player awayPlayer, Result score, boolean isGameOver){
        Score = new Result();
        Date = date;
        ID = id;
        HomePlayer = homePlayer;
        AwayPlayer = awayPlayer;
        Score = score;
        IsGameOver = isGameOver;
    }

    public int GetID(){return ID;}
    public Result GetScore(){return Score;}
    public boolean GetIsGameOver(){return IsGameOver;}
    public Player GetHomePlayer(){return HomePlayer;}
    public Player GetAwayPlayer(){return AwayPlayer;}
    public java.util.Date GetDate(){return Date;}

    public void SetID(int id){ID = id;}
    public void SetDate(java.util.Date date){Date = date;}
    public void SetScore(Result score){Score = score;}
    public void SetHomePlayer(Player homePlayer){HomePlayer = homePlayer;}
    public void SetAwayPlayer(Player awayPlayer){AwayPlayer = awayPlayer;}
    public void SetIsGameOver(boolean isOver) {IsGameOver = isOver;}


}