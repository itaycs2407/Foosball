package Models;

public class Result {
	int HomeTeamScore;
	int AwayTeamScore;
	
	public Result(){
		HomeTeamScore = 0;
		AwayTeamScore = 0;
	}
	
	public Result(int homeScore, int awayScore){
		SetScore(homeScore, awayScore);
	}

    public Result(Result result) {
		HomeTeamScore = result.HomeTeamScore;
		AwayTeamScore = result.AwayTeamScore;
    }

    public int GetHomeTeamScore(){return HomeTeamScore;}
	public int GetAwayTeamScore(){return AwayTeamScore;}
	
	public void SetHomeTeamScore(int homeScore){HomeTeamScore = homeScore;}
	public void SetAwayTeamScore(int awayScore){AwayTeamScore = awayScore;}
	public void SetScore(int homeScore, int awayScore){
		HomeTeamScore = homeScore;
		AwayTeamScore = awayScore;
	}
}
