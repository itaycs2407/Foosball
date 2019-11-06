package Models;

/**
 * Created by sapirchodorov on 16/07/2019.
 */
public interface ICommand {
    void execute();
    void addObserver(ITeamPlayersRow row);
    void removeObserver(ITeamPlayersRow row);
}
