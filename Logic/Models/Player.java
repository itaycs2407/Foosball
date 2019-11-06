package Models;

public class Player {
	String Name;
	Color Color;
	String Formation;
	
	public Player(String name, Color color){
		Name = name;
		Color = color;
		Formation = "";
	}
	public Player(String name,String formation, Color color){
		Name = name;
		Color = color;
		Formation = formation;
	}

	public Player(Player player) {
		Name = player.GetName();
		Color = player.GetColor();
		Formation = player.GetFormation();
	}

    public String GetName(){return Name;}
	public Color GetColor(){return Color;}
	public String GetFormation(){return Formation;}
	
	public void SetName(String name){Name = name;}
	public void SetColor(Color color){Color = color;}
	public void SetFormation(String formation){Formation = formation;}
	
}
