package Controllers;

import FoosballDal.CsvFoosballDal;
import FoosballDal.IFoosballDal;
import Models.Game;
import Models.GameToSave;
import Utils.DataUtils;
import Utils.FileUtils;
import Utils.FoosballConfigWrapper;

import java.io.IOException;
import java.util.List;


public class HomeController {
	private List<GameToSave> _gamesLoaded;
	private IFoosballDal _dal;
	//TODO:How do we get the path?Config? if so why dont you implement that!?
	private final String _dalPath = "";
	//TODO: Do we need keyboards assingment?

	private static final String configPath = "/Config/Config.Toml";
	private static IFoosballDal dal;




	public static void onLoad()
	{
		// load game configiguration to
		DataUtils.config = new FoosballConfigWrapper();
		DataUtils.config.LoadConfig(configPath);

		// load all games from csv file to list.
		DataUtils.dal = new CsvFoosballDal(DataUtils.config.config.appSettingsConfig.csvRelativePath);
	}
	public void OnStart(){
		try{
			_dal = new CsvFoosballDal(_dalPath);
		}
		catch(Exception e){
			System.exit(1);
		}
	}
	public void OnStart(String path){
		try{
		_dal = new CsvFoosballDal(path);
		}
		catch(Exception e){
			System.exit(1);
		}
	}
	public boolean LoadAll(){
		try{
			_gamesLoaded = _dal.GetGames();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	public boolean DeleteGame(int gameId){
		try{
			_dal.DeleteGame(gameId);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	public GameToSave GetGame(int gameId){
		try{
			return _dal.GetGame(gameId);
		}
		catch(Exception e){
			return null;
		}
	}
}