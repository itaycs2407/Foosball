package Utils;
import Config.AppSettingsConfig;
import Config.FoosballConfig;

import java.lang.reflect.Field;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoosballConfigWrapper {

    private final String APPHEADER = "#app settings";
    private final String GAMEHEADER = "#game settings";

    public FoosballConfig config;
    public FoosballConfig LoadConfig(String path){
        List<String> conf = FileUtils.ReadAllText(path);
        config = new FoosballConfig();
        Deserialize(conf);
        return config;
    }

    //Deserialize Toml foosball config file to object(FoosballConfig)
    private void Deserialize(List<String> configAsText){
        if(configAsText == null)return;

        String[] configArray = new String[configAsText.size()];
        configArray = configAsText.toArray(configArray);

        int appStartingIndex = findIndex(APPHEADER, configArray);
        int gameStartingIndex = findIndex(GAMEHEADER, configArray);
        if(appStartingIndex == -1 || gameStartingIndex == -1)return;

        Map<String,String> configDict = ConvertArrayToDict(appStartingIndex,gameStartingIndex, configArray);
        config.appSettingsConfig = FileUtils.ParseSettings(configDict,config.appSettingsConfig.getClass(),config.appSettingsConfig);

        configDict = ConvertArrayToDict(gameStartingIndex, configArray.length, configArray);
        config.gameSettingsConfig = FileUtils.ParseSettings(configDict,config.gameSettingsConfig.getClass(),config.gameSettingsConfig);
    }


    private Map<String,String> ConvertArrayToDict(int startingIndex,int endingIndex, String[] configArray) {

        Map<String, String> values = new HashMap<>();
        Tuple<String, String> currRow;
        for (int i = startingIndex; i< endingIndex; i++) {
            currRow = FileUtils.SplitLine(configArray[i], "=", true, true);
            if (currRow != null) values.put(currRow.x, currRow.y);
        }
        return values;
    }

    //Gets string array, and string to find in the array. returns the index if found, else returns -1
    private int findIndex(String s, String[] strings) {
        for (int i =0 ; i<strings.length; i++)
            if(strings[i].trim().toLowerCase().contains(s))
                return i + 1;
        return -1;
    }
}
