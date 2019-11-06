package Models;
import Shapes.Point;
import Utils.DataUtils;
import Utils.Tuple;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private final double GATEHEIGHT = 0.25;
    private final int BUFFER = 2;
    private int _width;
    private int _height;
    private FieldCell [][] gameField;
    private List<Tuple<Point,FieldCell>> staticSpecialCells;

    public Field(int height,int width){
        _height = height + BUFFER;
        _width = width + BUFFER;
        staticSpecialCells = new ArrayList<>();
        gameField = new FieldCell[_height][_width];
        for (int i =0; i< gameField.length; i++)
            for (int j =0 ; j < gameField[i].length; j++)
                gameField[i][j] = FieldCell.EMPTY;
        padField();
        addGoals();
    }

    public int get_height() {
        return _height;
    }
    public int get_width() {
        return _width;
    }

    public  void addGoals(){
        int gateStartingRow = (int)(_height * DataUtils.config.config.gameSettingsConfig.goalPart);
        int gateEndingRow = gameField.length - (int)(_height *  DataUtils.config.config.gameSettingsConfig.goalPart);
        for (int j = gateStartingRow ;j < gateEndingRow;j++) {
            gameField[j][(BUFFER / 2) - 1] = FieldCell.LEFTGOAL;
            staticSpecialCells.add(new Tuple<>(new Point(j,(BUFFER / 2) - 1),FieldCell.LEFTGOAL));
            gameField[j][gameField[0].length - (BUFFER / 2)] = FieldCell.RIGHTGOAL;
            staticSpecialCells.add(new Tuple<>(new Point(j,gameField[0].length - (BUFFER / 2)),FieldCell.RIGHTGOAL));
        }
    }
    public void addTeam(Team team, boolean isHomeTeam){
        FieldCell currCells;
        if(isHomeTeam)currCells = FieldCell.LEFTPLAYER;
        else currCells = FieldCell.RIGHTPLAYER;
        for (ITeamPlayersRow row :
                team.getPlayers()) {
            for (Tuple<Integer, Integer> playerPosition :row.getRowPositions()) {
                try{
                gameField[playerPosition.GetX()][playerPosition.GetY()] = currCells;}
                catch (Exception e){}
            }
        }
    }

    public void removeTeam(Team team) {
        for (ITeamPlayersRow row :
                team.getPlayers()) {
            for (Tuple<Integer, Integer> playerPosition :row.getRowPositions()) {
                try{
                    gameField[playerPosition.GetX()][playerPosition.GetY()] = FieldCell.EMPTY;}
                catch (Exception e){}
            }
        }
    }
    public FieldCell getSpecificCell(int x, int y){

       if(x >= gameField.length || x < 0 || y<0 || y>= gameField[0].length)
           return null;
        try{return gameField[x][y];}
        catch (Exception e){
            return null;
        }
    }
    public List<Tuple<Point,FieldCell>> getStaticSpecialCells(){return staticSpecialCells;}
    public void setSpecificCell (int x,int y, FieldCell type){
       if(x >= gameField.length || x < 0 || y<0 || y>= gameField[0].length)
           return;
       gameField[x][y] = type;

    }
    public Point getCenter(){
        return new Point(get_height()/2, get_width()/2);
    }

    // building the field  + gate boundries
    private void padField(){

        for (int i = 0; i< gameField.length; i++) {
            gameField[i][0] = FieldCell.PADDING;
            gameField[i][gameField[0].length -1] = FieldCell.PADDING;
            staticSpecialCells.add(new Tuple<>(new Point(i,0),FieldCell.PADDING));
            staticSpecialCells.add(new Tuple<>(new Point(i,gameField[0].length -1),FieldCell.PADDING));
        }
        for (int i=0;i<gameField[0].length;i++){
            gameField[0][i] = FieldCell.PADDING;
            gameField[gameField.length-1][i] = FieldCell.PADDING;
            staticSpecialCells.add(new Tuple<>(new Point(0,i),FieldCell.PADDING));
            staticSpecialCells.add(new Tuple<>(new Point(gameField.length-1,i),FieldCell.PADDING));
        }
    }

}
