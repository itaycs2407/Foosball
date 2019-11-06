package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sapirchodorov on 16/07/2019.
 */
public class MoveDownCommand implements ICommand {

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
            row.update(Direction.DOWN);
        }
    }
}
