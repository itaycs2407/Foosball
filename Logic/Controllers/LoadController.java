package Controllers;

import Models.LoadMdl;
import Models.LoadTableData;

import java.util.List;

public class LoadController {

    static LoadMdl model;

    public LoadController(){
        model = new LoadMdl();
    }
    public LoadTableData getTable() {
        return model.getTable();
    }

    public int getTableSize() { return model.getTableSize(); }

    public boolean loadGameFromList(int gameIndex) {
        if(model.loadGameFromList(gameIndex))
            return true;
        else
            return false;
    }

}
