package Config;

public class FoosballConfig {
    public AppSettingsConfig appSettingsConfig;
    public GameSettingsConfig gameSettingsConfig;

    public FoosballConfig(){
        appSettingsConfig = new AppSettingsConfig();
        gameSettingsConfig = new GameSettingsConfig();
    }
}
