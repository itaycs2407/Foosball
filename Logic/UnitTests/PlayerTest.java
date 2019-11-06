package UnitTests;

import Models.Color;
import Models.Player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    Player testPly = new Player("Itay", Color.BLUE);

    @Test
    public void testName()
    {
       assertEquals("Itay",testPly.GetName());
    }
    @Test
    public void testColor(){
        assertEquals(Color.BLUE,testPly.GetColor());
    }
}
