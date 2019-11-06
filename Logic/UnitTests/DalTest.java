
package UnitTests;
import Controllers.HomeController;
import FoosballDal.CsvFoosballDal;
import FoosballDal.IFoosballDal;
import Models.*;
import UI.StartScreen;
import Utils.DataUtils;
import Utils.FileUtils;
import Utils.FoosballConfigWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DalTest {
    private static String configPath;

    static {
        try {
            configPath = FileUtils.relativePathToFullPath("\\Config\\Config.Toml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static IFoosballDal dal;
    private static FoosballConfigWrapper config;


    @BeforeAll
    static void setUp() throws IOException {
        // mockup creation

        HomeController.onLoad();
        config = new FoosballConfigWrapper();
        config.LoadConfig(configPath);
        dal = new CsvFoosballDal(DataUtils.config.config.appSettingsConfig.csvRelativePath);
    }

    @Test
    void Insert() {
        for (int i = 0; i < 5; i++) {
            GameToSave g = new GameToSave(i + 10, new Date(), new Player("Home", Color.BLUE), new Player("Awaay", Color.RED), new Result(i * 2, i + 1), i % 2 == 0);
            dal.Insert(g);
        }
        printCurrentDal();
    }

    @Test
    void SetGameResult() {
        dal.SetGameResult(1, new Result(2, 2));
        dal.SetGameResult(15, new Result(2, 2));
        dal.SetGameResult(-1, new Result(2, 2));
    }

    @Test
    void SetIsGameOver() {
        dal.SetIsGameOver(5, true);
        dal.SetIsGameOver(15, false);
        dal.SetIsGameOver(-35, true);
    }

    @Test
    void DeleteGame() {
        dal.DeleteGame(4);
        dal.DeleteGame(11);
        dal.DeleteGame(-5);
    }

    @Test
    void Save() {
        dal.Save();
        printCurrentDal();
    }

    private static void printCurrentDal() {
        List<GameToSave> games = dal.GetGames();
        for (GameToSave game :
                games) {
            System.out.println(game.GetID() + " " + game.GetScore() + " " + game.GetHomePlayer() + " " + game.GetAwayPlayer() + " " + game.GetDate().toString() + " " + game.GetIsGameOver());
        }
        System.out.println();
    }

}