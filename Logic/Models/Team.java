package Models;

import java.util.List;

public class Team {
	public List<ITeamPlayersRow> players;
	private ICommand moveUpCommand;
	private ICommand moveDownCommand;
	
	public Team(List<ITeamPlayersRow> players){
		this.players = players;
		moveDownCommand = new MoveDownCommand();
		moveUpCommand = new MoveUpCommand();
		for (ITeamPlayersRow row :
				players) {
			moveUpCommand.addObserver(row);
			moveDownCommand.addObserver(row);
		}
	}

	public void addTeamRow(ITeamPlayersRow row){
		moveUpCommand.addObserver(row);
		moveDownCommand.addObserver(row);
		players.add(row);
	}

	public void ResetPosition() {

	}

	public void Move(Direction direction, int moveSize){
		if (direction == Direction.UP)
			moveUpCommand.execute();
		else
			moveDownCommand.execute();
	}



	public List<ITeamPlayersRow> getPlayers() {return players;}
}
