package UI;

import Controllers.GameController;
import Models.*;
import Shapes.Point;
import Shapes.RectShape;
import Utils.DataUtils;
import Utils.FileUtils;
import Utils.Tuple;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Because we work with matrix over pixel - every x in matrix becomes y in pixels (same applies for y)
public class GameScreen {

	private final int pace = 1;

	private ActionListener moveBall;
	private JFrame frame;
	private List<ImageSettings> images;
	private Tuple<JLabel,ImageSettings> ball;
	private List<JLabel> homeTeamLabels;
	private List<JLabel> awayTeamLabels;
	private JLabel score;
	private JButton startBtn;
	private JButton contGameBtn;
	private JButton saveGameBtn;
	private JLabel timer;
	private GameController gc;
	private Font pageFont ;
	private Font btnFont ;
	private Timer gameTimer;
    private int timePassed;
    private Color btnsBG;
    private Color btnsFG;

    public GameScreen() {
        gc = new GameController();
        gc.onStart();
        init();
	}

	public void init(){
        frame = new JFrame();
        images = new ArrayList<>();
        homeTeamLabels = new ArrayList<>();
        awayTeamLabels = new ArrayList<>();
        pageFont = new Font("Tahoma", Font.BOLD, 30);
        btnFont = new Font("Tahoma", Font.PLAIN, 24);
        btnsBG = Color.white;
        btnsFG = Color.BLACK;
        try {
            initFrame(100, 100, 950, 700);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initLabels();
	    initBtns();
        initGameProps();
        timePassed =1;
    }

    private void initLabels() {
        score = UIGenericCreator.addLabelToFrame(frame,gc.getScore(),pageFont, Color.darkGray,Color.white,850,200,100,50);
        timer = UIGenericCreator.addLabelToFrame(frame, String.valueOf(gc.getGameTime()) + " : 00",pageFont, Color.darkGray,Color.white,850,400,100,50 );
        UIGenericCreator.addLabelToFrame(frame,gc.getTeamName(true),pageFont, Color.darkGray,Color.white, 840,100,80,50);
        UIGenericCreator.addLabelToFrame(frame,gc.getTeamName(false),pageFont, Color.darkGray,Color.white, 920,100,80,50);
    }

    private void initBtns() {
        startBtn = UIGenericCreator.addJButtonToFrame(frame, new JButton("Start!"), btnFont, btnsBG,btnsFG,300,400,150,50);
        startBtn.addActionListener(e -> {
            startBtn.setVisible(false);
            frame.remove(startBtn);
            startGame();});
        startBtn.setVisible(false);
        saveGameBtn = UIGenericCreator.addJButtonToFrame(frame, new JButton("Save!"),btnFont,btnsBG,btnsFG,500,400,150,50);
        saveGameBtn.addActionListener(e ->{
            frame.dispose();
            try {
                StartScreen s = new StartScreen();
                gc.saveGame(gc.isGameOver());
                s.frame.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }});
        saveGameBtn.setVisible(false);
        contGameBtn = UIGenericCreator.addJButtonToFrame(frame, new JButton("Play!"),btnFont,btnsBG,btnsFG,200,400,150,50);
        contGameBtn.addActionListener(e ->{
            saveGameBtn.setVisible(false);
            contGameBtn.setVisible(false);
            gameTimer.start();
        });
        contGameBtn.setVisible(false);
	}

	private void initGameProps(){
        try {
            initField(images);
            initTeams();
        } catch (IOException e) {
            e.printStackTrace();
        }
	    initBall();
    }


	private void initBall() {
        try {
            ball = new Tuple<>(new JLabel(""),new ImageSettings(FileUtils.relativePathToFullPath("\\Images\\SoccerBall.png"),300,100,100,50));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ball.GetX().setIcon(UIGenericCreator.setImageIconForFrame(ball.GetY().path));
        frame.getContentPane().add(ball.GetX());
        ball.GetX().setBounds(ball.GetY().GetX(),ball.GetY().GetY(), ball.GetY().GetWidth(),ball.GetY().GetHeight());
        initMoveBall();
        ball.GetX().setVisible(false);
	}

    private void initMoveBall() {
        moveBall = e -> {
            Ball b = gc.moveBall();
            timer.setText(getTimeLeft());
            ball.GetX().setBounds(b.ballShape.getPoint().getY(),b.ballShape.getPoint().getX(),ball.GetY().GetWidth(),ball.GetY().GetHeight());
            if(gc.checkIfGoal())
                if(!gc.isGameOver())
                    score.setText(gc.getScore());
                else
                    endGame();
            if(gc.isTimeUp())
            {
                saveGameBtn.setVisible(true);
                gameTimer.stop();
            }};
	}

    private String getTimeLeft() {
        int timeLeft =  gc.getGameTime()*60000 - timePassed++;
        return timeLeft / 60000 + " : " + (timeLeft / 1000) % 60;
    }

    private void startGame(){
        ball.GetX().setVisible(true);
        addHomePlayerKeysListeners();
        addAwayPlayerKeysListeners();
        addStopGameKeysListeners();
        gameTimer = new Timer(pace, moveBall);
        gameTimer.start();
    }

    private void initFrame(int x,int y,int width, int height) throws IOException {
        UIGenericCreator.defaultFrame(frame);
		frame.setVisible(true);
		frame.setContentPane(new JLabel(UIGenericCreator.setImageIconForFrame(FileUtils.relativePathToFullPath("\\Images\\testField.png"))));
	}

	private void addHomePlayerKeysListeners() {
		onTeamMove(homeTeamLabels,true);
	}

	private void onTeamMove(List<JLabel> labels,boolean isHome){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                (KeyEvent e) -> {
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        if(gc.moveTeam(e.getKeyChar()) == null)return false;
                        setVisible(labels,false);
                        try {

                            reinitTeam(isHome);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    return false;
                });
    }

	private void addAwayPlayerKeysListeners() {
		onTeamMove(awayTeamLabels,false);
	}

    private void addStopGameKeysListeners() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                (KeyEvent e) -> {
                    if (e.getID() == KeyEvent.KEY_PRESSED) {
                        if(gc.stopGame(e.getKeyChar())) {
                            contGameBtn.setVisible(true);
                            saveGameBtn.setVisible(true);
                            gameTimer.stop();
                        }
                    }
                    return false;
                });
    }

    private void setVisible(List<JLabel> labels, boolean isVisible){
		for (int i =0; i< labels.size();i++)
        {
            labels.get(i).setVisible(isVisible);
			frame.remove(labels.get(i));
        }
	}

	private void endGame() {
		gameTimer.stop();
        UIGenericCreator.addLabelToFrame(frame, "Game Over! "+score.getText(),pageFont,Color.darkGray,Color.white,100,100,150,50);
		saveGameBtn.setVisible(true);
	}

	private void initTeams() throws IOException {
		Tuple<Team, Team> teams = gc.getTeams();
		homeTeamLabels = addTeamsPlayersPolesImagesToFrame(teams.GetX().getPlayers(),gc.getTeamPhoto(true),homeTeamLabels);
		awayTeamLabels = addTeamsPlayersPolesImagesToFrame(teams.GetY().getPlayers(),gc.getTeamPhoto(false),awayTeamLabels);
	}
	private void reinitTeams() throws IOException {
		Tuple<Team, Team> teams = gc.getTeams();
		homeTeamLabels = renderPlayers(teams.GetX().getPlayers(),gc.getTeamPhoto(true),homeTeamLabels);
		awayTeamLabels = renderPlayers(teams.GetY().getPlayers(),gc.getTeamPhoto(false),awayTeamLabels);
	}
	private void reinitTeam(boolean isHome) throws IOException {
		Tuple<Team, Team> teams = gc.getTeams();
		if(isHome)homeTeamLabels = renderPlayers(teams.GetX().getPlayers(),gc.getTeamPhoto(true),homeTeamLabels);
		else awayTeamLabels = renderPlayers(teams.GetY().getPlayers(),gc.getTeamPhoto(false),awayTeamLabels);
	}
	//gets list of players rows, path to take image from and list to add players to. and uses addImageToFrameToAddThem
	private List<JLabel> addTeamsPlayersPolesImagesToFrame(List<ITeamPlayersRow> players, String path, List<JLabel> listToAdd){
		for (int i =0; i< players.size();i++){
			try {
				ImageSettings poleImg  = new ImageSettings(FileUtils.relativePathToFullPath("\\Images\\BlackPole.png"),players.get(i).getPlayers().get(0).getPoint().getY() + players.get(i).getPlayers().get(0).getWidth()/2, -300,10, frame.getHeight() + 700);
				UIGenericCreator.addImageToFrame(frame, poleImg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		listToAdd = renderPlayers(players,path,listToAdd);
		return listToAdd;
	}

	private List<JLabel> renderPlayers(List<ITeamPlayersRow> players, String path, List<JLabel> listToAdd) {
        List<JLabel> finalListToAdd = listToAdd;
        SwingWorker<Boolean, List<JLabel>> sw = new SwingWorker<Boolean,List<JLabel>>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                publish(finalListToAdd);
                for (int i =0; i< players.size();i++){
                    for (RectShape player:players.get(i).getPlayers()) {
                        ImageSettings img = new ImageSettings(path, player.getPoint().getY(),player.getPoint().getX(),player.getWidth(),player.getLength());
                        finalListToAdd.add(UIGenericCreator.addImageToFrame(frame, img));
                    }
                }
                return true;
            }
        };
        sw.execute();
		return listToAdd;
    }

	//initialize field members (images loading to screen)
	private void initField(List<ImageSettings> images) throws IOException {
		List<Tuple<Point, FieldCell>> specialCells = gc.getStaticSpecialCells();
        ImageSettings loadingImg = new ImageSettings(FileUtils.relativePathToFullPath(DataUtils.config.config.gameSettingsConfig.loadingGifPath),300,100,270,350);
		final JLabel loadLbl = UIGenericCreator.addgifImageToFrame(frame,loadingImg);
        SwingWorker sw = new SwingWorker<Void,List<JLabel>>() {
            @Override
            protected Void doInBackground() throws Exception {
                int goalStartingRow = (int) ((DataUtils.config.config.gameSettingsConfig.fieldRows * DataUtils.config.config.gameSettingsConfig.goalPart));
                int goalEndingRow = (int) (DataUtils.config.config.gameSettingsConfig.fieldRows * (1- DataUtils.config.config.gameSettingsConfig.goalPart));
                images.add(new ImageSettings(FileUtils.relativePathToFullPath("\\Images\\goal.png"),0,goalStartingRow,150,goalEndingRow - goalStartingRow));
                images.add(new ImageSettings(FileUtils.relativePathToFullPath("\\Images\\goal.png"),DataUtils.config.config.gameSettingsConfig.fieldColumns,goalStartingRow,150,goalEndingRow - goalStartingRow));
                for (Tuple<Point, FieldCell> specialCell : specialCells)
                    if(specialCell.GetY() == FieldCell.PADDING)
                        images.add(new ImageSettings(FileUtils.relativePathToFullPath("\\Images\\goal.png"),specialCell.GetX().getY(),specialCell.GetX().getX(),1,1));
                for (ImageSettings image:images) UIGenericCreator.addImageToFrame(frame, image);
                return null;
            }
            @Override
            protected void done(){
                startBtn.setVisible(true);
                loadLbl.setVisible(false);
                frame.remove(loadLbl);
            }
        };
        sw.execute();
	}
}
