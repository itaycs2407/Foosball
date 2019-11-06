package UnitTests;

import Models.Result;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ResultTest {
    Result testRes = new Result();
    @Test
    public void test(){
        testRes.SetAwayTeamScore(5);
        testRes.SetHomeTeamScore(5);

    }
    @Test
    public void checkIfEqual(){
        assertEquals(testRes.GetAwayTeamScore(),testRes.GetHomeTeamScore());
    }

}
