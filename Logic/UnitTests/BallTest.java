package UnitTests;

import Models.Ball;
import Models.Color;
import Models.Direction;
import Shapes.AbstractShape;
import Shapes.CircleShape;
import Shapes.Point;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static Models.Color.RED;
import static Models.Direction.DOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BallTest {
    Color ballColor = RED;
    //Direction direct = Direction.getValue(180);
    AbstractShape shape;

    Point ballPoint = new Point(10,10);
    CircleShape circleshp = new CircleShape(ballPoint,2.5);
    Ball ballTest = new Ball(ballColor,circleshp, DOWN,1);

    @Test
    public void checkColor(){
        assertEquals(RED,ballTest.color);
    }
    @Test
    public void checkBallArea (){
        assertEquals(2.5 * 2.5 * Math.PI,ballTest.ballShape.GetShapeArea());

    }
    @Test
    public void checkBallDirection(){
        assertEquals(DOWN,ballTest.currDirection);
    }
    @Test
    public void checkBallMove(){
        ballTest.Move();
        assertEquals(9,ballTest.ballShape.getPoint().y);
    }
    @Test
    void move(){
        ballTest.setCurrDirection(Direction.UP);
        ballTest.Move();
        Assert.assertEquals(11, ballTest.ballShape.getPoint().y);
        Assert.assertEquals(10, ballTest.ballShape.getPoint().x);
    }
    @Test
    void setDirection(){
        ballTest.setCurrDirection(Direction.LEFT);
        Assert.assertEquals(Direction.LEFT,ballTest.currDirection);
    }

    @Test
    void resetPosition() {
        for(int i =0;i<5;i++)ballTest.Move();
        ballTest.ResetPosition(ballPoint);
        Assert.assertEquals(ballPoint,ballTest.ballShape.getPoint());
    }
}
