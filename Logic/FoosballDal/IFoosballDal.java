package FoosballDal;

import Models.GameToSave;
import Models.Result;

import java.util.List;

public interface IFoosballDal {
	//Returns list of all games saved in DB
	List<GameToSave> GetGames();
	//Gets game id . returns all details of the selected game
	GameToSave  GetGame(int gameId);
	//gets a game and insert it to DB
	void Insert(GameToSave game);
	//Gets game id, andits  result - and update it
	void SetGameResult(int gameId, Result reulult);
	//Gets game id, and if the game is over - and saves it to DB
	void SetIsGameOver(int gameId, boolean isOver);
	//Deletes the game with gameId
	void DeleteGame(int gameId);
	//Returns an available id for game
	int GetFreeId();
	//saves current db to file system
	void Save();
}