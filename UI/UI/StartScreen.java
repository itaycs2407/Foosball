package UI;


import Controllers.HomeController;
import Utils.DataUtils;
import Utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartScreen {

	public static JFrame frame = new JFrame();
	private Font pageFont;
	public static int window_x;
	public static int window_y;
	public static int window_width;
	public static int window_height;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				HomeController.onLoad();
				window_x = (int) DataUtils.config.config.appSettingsConfig.windowStartFromX;
				window_y = (int) DataUtils.config.config.appSettingsConfig.windowStartFromY;
				window_width = (int) DataUtils.config.config.appSettingsConfig.windowWidth;
				window_height = (int) DataUtils.config.config.appSettingsConfig.windowHeight;

				try {
					StartScreen window = new StartScreen();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public  StartScreen() throws IOException {
		pageFont = new Font("Tahoma", Font.PLAIN, 24);
		initialize();
	}

	private void initFrame() throws IOException {
		UIGenericCreator.defaultFrame(frame);
		UIGenericCreator.addImageToFrame(frame, new ImageSettings(FileUtils.relativePathToFullPath("\\Images\\GameIcon.png"),350,100,200,200));
	}

	private void initialize() throws IOException {
		initFrame();
		initLabels();
		initButtons();
		frame.setVisible(true);


	}

	private void initLabels() {
		UIGenericCreator.addLabelToFrame(frame, "Foosball National League 2019 2K", new Font("Tahoma", Font.PLAIN, 36),175,35,650,65 );
	}

	private void initButtons(){
		JButton btnNewGame = new JButton("New game");
		UIGenericCreator.addJButtonToFrame(frame, btnNewGame,pageFont,Color.white,Color.BLACK,360,350,180,40);
		btnNewGame.addActionListener(e -> {
			try {
				GameSettings gameSettings = new GameSettings();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			frame.setVisible(false);
		});
		JButton btnLoadGame = new JButton("Load game");
		UIGenericCreator.addJButtonToFrame(frame, btnLoadGame,pageFont,Color.white,Color.BLACK,360,400,180,40);
		btnLoadGame.addActionListener(e -> {
            try {
                LoadScreen load = new LoadScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
		});

		JButton btnStatistics = new JButton("Statistics");
		UIGenericCreator.addJButtonToFrame(frame, btnStatistics,pageFont,Color.white,Color.BLACK,360,450,180,40);
		btnStatistics.addActionListener(e -> {
			GameStatistics staticsButton = new GameStatistics();
			frame.setVisible(false);
		});

		JButton btnButtons = new JButton("Set Buttons");
		UIGenericCreator.addJButtonToFrame(frame, btnButtons,pageFont,Color.white,Color.BLACK,360,500,180,40);
		btnButtons.addActionListener(e -> {
			try {
				ButtonsSelection selectButton = new ButtonsSelection();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			frame.setVisible(false);
		});

		JButton btnExit = new JButton("Exit");
		UIGenericCreator.addJButtonToFrame(frame, btnExit,pageFont,Color.RED,Color.BLACK,400,560,100,40);
		btnExit.addActionListener(e -> System.exit(0));
	}



}
