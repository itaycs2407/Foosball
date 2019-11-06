package FoosballDal;

import Models.Color;
import Models.GameToSave;
import Models.Player;
import Models.Result;
import Utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CsvFoosballDal implements IFoosballDal{

	private String _csvGameFilePath;
	private List<GameToSave> _games;

	public CsvFoosballDal(String csvGamePath)
	{
		_csvGameFilePath = csvGamePath;
		List<String> gamesTextLoaded = FileUtils.ReadAllText(_csvGameFilePath);
		_games = parseTextToGames(gamesTextLoaded);

	}

	public List<GameToSave> GetGames()
	{
		return _games;
	}
	public GameToSave  GetGame(int gameId){
		for (GameToSave game: _games){
			if (game.GetID() == gameId){
				return game;
			}
		}
		return null;
	}

	public void Insert(GameToSave game){
		for (GameToSave g:
				_games) {
			if(g.GetID() == game.GetID())return;
		}
		_games.add(game);
	}
	public void SetGameResult(int gameId, Result result){
		for (GameToSave game: _games){
			if (game.GetID() == gameId){
				game.SetScore(result);
				break;
			}
		}
	}
	public void SetIsGameOver(int gameId, boolean isOver){
		for (GameToSave game: _games){
			if (game.GetID() == gameId){
				game.SetIsGameOver(isOver);
				break;
			}
		}
	}
	public void DeleteGame(int gameId){
		for (GameToSave game: _games){
			if (game.GetID() == gameId){
				_games.remove(game);
				break;
			}
		}
	}

	@Override
	public int GetFreeId() {
		int max = _games.get(0).GetID();
		for (int i = 1;i < _games.size();i++)
			if(max<_games.get(i).GetID())
				max = _games.get(i).GetID();
		return max + 1;
	}

	@Override
	public void Save() {
		try {
			FileOutputStream stream = new FileOutputStream(FileUtils.relativePathToFullPath(_csvGameFilePath));
			stream.write(("").getBytes());
			for(GameToSave game: _games){
				String csvRow = serializeToCsvRow(game);
				stream.write(csvRow.getBytes());
			}
			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String serializeToCsvRow(GameToSave game) {
		String Id = String.valueOf(game.GetID());
		String homeName = String.valueOf(game.GetHomePlayer().GetName());
		String homeColor = String.valueOf(game.GetHomePlayer().GetColor().ordinal());
		String homeForm = String.valueOf(game.GetHomePlayer().GetFormation());
		String awayName = String.valueOf(game.GetAwayPlayer().GetName());
		String awayColor = String.valueOf(game.GetAwayPlayer().GetColor().ordinal());
		String awayForm = String.valueOf(game.GetAwayPlayer().GetFormation());
		String date = String.valueOf(new SimpleDateFormat("d-M-y k:m",Locale.ENGLISH).format(game.GetDate()));

		String score = String.valueOf(game.GetScore().GetHomeTeamScore() + " _ " + game.GetScore().GetAwayTeamScore());
		String isFinished = String.valueOf(game.GetIsGameOver());
		String csvRow = Id + ',' + homeName + ',' + homeColor + ',' + homeForm + ',' + awayName + ',' + awayColor + ',' + awayForm + ',' + date + ',' + score + ',' + isFinished + "\n";
		return csvRow;
	}
	private List<GameToSave> parseTextToGames(List<String> linesToParse) {
		List<GameToSave> games = new ArrayList<>();
		String[] stringSplitted;
		for (String line : linesToParse)
		{
			stringSplitted = line.split(",");
			GameToSave gameToAdd = createGameType(stringSplitted);
			if(gameToAdd != null){
				games.add(gameToAdd);
			}
		}
		return games;
	}
	private GameToSave createGameType(String[] stringSplitted) {
		try
		{
			//As we parse csv we have to be careful with the string array ...
			int id = Integer.parseInt(stringSplitted[0]);
			Player hPlayer = new Player(stringSplitted[1],stringSplitted[3], Color.values()[Integer.parseInt(stringSplitted[2])]);
			Player aPlayer = new Player(stringSplitted[4],stringSplitted[6], Color.values()[Integer.parseInt(stringSplitted[5])]);

			//date format as in current csv.. will throw exception if not received this way
			DateFormat df = new SimpleDateFormat("d-M-y k:m", Locale.ENGLISH);
			java.util.Date date = df.parse(stringSplitted[7]);
			Result result = createPlayerType(stringSplitted[8]);
			boolean isOver = Boolean.parseBoolean( stringSplitted[9]);
			GameToSave game = new GameToSave(id, date, hPlayer, aPlayer, result, isOver);
			return game;
		}
		catch(Exception e){
			//Logger?
			return null;
		}
	}
	private Result createPlayerType(String stringToSplitFrom) {
		String[] strings = stringToSplitFrom.split(" _ ");
		Result res = new Result(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
		return res;
	}

}