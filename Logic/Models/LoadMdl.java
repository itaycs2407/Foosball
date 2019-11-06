package Models;

import Utils.DataUtils;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LoadMdl {

    List<GameToSave> unfinishedGameList;
    String[] columnNames = {"GameID","Player1","Player1Color","Formation1","Player2","Player2Color","Formation2","Date","Score"};
    LoadTableData tableData;

    public LoadMdl() {
        unfinishedGameList = getListOfUnfinishedGames(DataUtils.dal.GetGames());
        tableData = new LoadTableData((unfinishedGameList.size() + 1),10 , this.columnNames);
        setTableData();
    }

    private void setTableData() {
        int row, col = 0, tableRow = 1;
        for (row=0;row<(tableData.getRowCount()-1) && row < 10;row++) {
                tableData.setValueAt(tableRow, col++, ((Integer) unfinishedGameList.get(row).GetID()));
                tableData.setValueAt(tableRow, col++, unfinishedGameList.get(row).GetHomePlayer().Name);
                tableData.setValueAt(tableRow, col++, unfinishedGameList.get(row).GetHomePlayer().Color);
                tableData.setValueAt(tableRow, col++, unfinishedGameList.get(row).GetHomePlayer().Formation);
                tableData.setValueAt(tableRow, col++, unfinishedGameList.get(row).GetAwayPlayer().Name);
                tableData.setValueAt(tableRow, col++, unfinishedGameList.get(row).GetAwayPlayer().Color);
                tableData.setValueAt(tableRow, col++, unfinishedGameList.get(row).GetAwayPlayer().Formation);
                tableData.setValueAt(tableRow, col++, unfinishedGameList.get(row).GetDate());
                tableData.setValueAt(tableRow, col++, getScore(unfinishedGameList,row));
                tableRow++;
            col = 0;
            }

        }

    public LoadTableData getTable() {
        return tableData;
    }

    public String getScore(List<GameToSave> gameList, int index) {
        return gameList.get(index).GetScore().GetHomeTeamScore() + " - " + gameList.get(index).GetScore().GetAwayTeamScore();
    }

    public int getTableSize() {
        return tableData.getRowCount()-1;
    }

    public List<GameToSave> getListOfUnfinishedGames(List<GameToSave> gameList) {
        int row;
        int size = gameList.size();
        List<GameToSave> unfinsihedGames = gameList;
        for (row = size-1; row >= 0; row--)
            if (unfinsihedGames.get(row).IsGameOver)
                unfinsihedGames.remove(row);
        return unfinsihedGames;
    }

    public boolean loadGameFromList(int gameIndex) {
        if (gameIndex == -1)
            return false;
        else {
            DataUtils.config.config.gameSettingsConfig.player1Name = unfinishedGameList.get(gameIndex).GetHomePlayer().GetName();
            DataUtils.config.config.gameSettingsConfig.player1Color = unfinishedGameList.get(gameIndex).GetHomePlayer().GetColor().toString();
            DataUtils.config.config.gameSettingsConfig.player1Formation = unfinishedGameList.get(gameIndex).GetHomePlayer().GetFormation();
            DataUtils.config.config.gameSettingsConfig.player2Name = unfinishedGameList.get(gameIndex).GetAwayPlayer().GetName();
            DataUtils.config.config.gameSettingsConfig.player2Color = unfinishedGameList.get(gameIndex).GetAwayPlayer().GetColor().toString();
            DataUtils.config.config.gameSettingsConfig.player2Formation = unfinishedGameList.get(gameIndex).GetAwayPlayer().GetFormation();
            DataUtils.config.config.gameSettingsConfig.startingResult = unfinishedGameList.get(gameIndex).Score;
            return true;
        }
    }
}
