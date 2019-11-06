package Controllers;

import Models.LoadTableData;
import Models.StatisticsMdl;

public class StatisticCtrl {
    private static StatisticsMdl StatMoudle;
    public StatisticCtrl(){
        StatMoudle = new StatisticsMdl();
    }

    public LoadTableData getTable() {
        return StatMoudle.getStatTable();
    }
    public void gameToRemove (int game){
        StatMoudle.removeGame(game);
    }
    public LoadTableData updateTable() {
        StatMoudle.parseData();
        return StatMoudle.getStatTable();
    }
    public int getNumOfStoredGames() {
        return StatMoudle.getNumOfStoredGames();
    }

    public void preformSave() {
        StatMoudle.saveAction();
    }
}

