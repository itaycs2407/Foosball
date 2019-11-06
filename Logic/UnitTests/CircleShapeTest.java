package UnitTests;

import Shapes.CircleShape;
import Shapes.Point;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleShapeTest {
    Point cnt = new Point(1, 1);
    CircleShape circle = new CircleShape(cnt, 5);

    @Test
    void testArea() {
    assertEquals(5*5*Math.PI,circle.GetShapeArea());
    }

    @Test
    void getRadius() {
        assertEquals(5,circle.getRadius());
    }
}