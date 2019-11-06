package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by sapirchodorov on 16/07/2019.
 */
public class MoveUpCommand implements ICommand{

    public List<ITeamPlayersRow> players = new ArrayList<>();
    public int moveSize;
    public void addObserver(ITeamPlayersRow row){
        players.add(row);
    }

    public void removeObserver(ITeamPlayersRow row){
        players.remove(row);
    }

    @Override
    public void execute() {
        for(ITeamPlayersRow row: players){
            row.update(Direction.UP);
       }
    }

}
