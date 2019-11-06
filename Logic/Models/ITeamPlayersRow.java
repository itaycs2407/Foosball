package Models;

import Shapes.RectShape;
import Utils.Tuple;

import java.util.List;

public interface ITeamPlayersRow {
    public void update(Object o);
    public List<RectShape> getPlayers();
    public List<Tuple<Integer, Integer>> getRowPositions();

}
