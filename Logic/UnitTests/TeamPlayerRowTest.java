package UnitTests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Models.Direction;
import Models.TeamPlayersRow;
import Shapes.AbstractShape;
import Shapes.Point;
import Shapes.RectShape;


import java.util.ArrayList;
import java.util.List;

public class TeamPlayerRowTest {
    private final Point point = new Point(1,1);
    private static List<AbstractShape> players ;
    private static TeamPlayersRow team;

    @BeforeAll
    static void setUp(){
        players = new ArrayList<>();
        for (int i =0 ;i<4;i++)
            players.add(new RectShape(1,1));
        team = new TeamPlayersRow(1,1,new RectShape(10,10),10,4);
    }


    @Test
    void Move(){
        Point[] points = new Point[team.players.size()];
        int i =0;
        for (AbstractShape player :
                team.players) {
            points[i] = new Point();
            points[i].setX(player.getPoint().getX());
            points[i].setY(player.getPoint().getY());
            i++;
        }
        team.Move(Direction.LEFT,2);
        for (i =0;i< points.length;i++){
            assertEquals((points[i].getY()),team.players.get(points.length-1 - i).getPoint().getY());
        }

    }



}
