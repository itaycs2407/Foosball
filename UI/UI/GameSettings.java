package UI;
import Controllers.GameSettingsController;
import Utils.FileUtils;
import Utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameSettings {
    private JFrame frame = new JFrame();
    private Font pageFont;
    private JTextField ply1Name;
    private JTextField ply2Name;
    private Tuple<JComboBox, DefaultComboBoxModel> ply1Form;
    private Tuple<JComboBox, DefaultComboBoxModel> ply2Form;
    private Tuple<JComboBox, DefaultComboBoxModel> ply1Color;
    private Tuple<JComboBox, DefaultComboBoxModel> ply2Color;
    private Tuple<JComboBox, DefaultComboBoxModel> maxScore;
    private Tuple<JComboBox, DefaultComboBoxModel> maxTime;
    private String[] formOptions;
    private String[] colorOptions1;
    private String[] colorOptions2;
    private GameSettingsController gsCtrl;


    public GameSettings() throws IOException {
        formOptions = new String[] {"2 2", "3 1", "1 3"};

        colorOptions1 = new String[] {"red", "blue", "yellow"};
        colorOptions2 = new String[] {"blue", "red", "yellow"};
        ply1Name = new JTextField();
        ply2Name = new JTextField();
        ply1Form = new Tuple<>(new JComboBox(),new DefaultComboBoxModel(formOptions));
        ply2Form = new Tuple<>(new JComboBox(),new DefaultComboBoxModel(formOptions));
        ply1Color = new Tuple<>(new JComboBox(),new DefaultComboBoxModel(colorOptions1));
        ply2Color= new Tuple<>(new JComboBox(),new DefaultComboBoxModel(colorOptions2));
        maxScore= new Tuple<>(new JComboBox(),new DefaultComboBoxModel(new String[] {"3", "1","2", "4","5", "6","7", "8"}));
        maxTime= new Tuple<>(new JComboBox(),new DefaultComboBoxModel(new String[] {"3", "1","2", "4","5", "6","7", "8"}));
        pageFont = new Font("Tahoma", Font.PLAIN, 30);
        gsCtrl = new GameSettingsController();
        initialize();
    }

    private void initFrame() throws IOException {
        UIGenericCreator.defaultFrame(frame);
    }

    private void initLabels(){
        UIGenericCreator.addLabelToFrame(frame, "Player 1",pageFont,50, 95, 150, 45);
        UIGenericCreator.addLabelToFrame(frame,"Formation",pageFont, 50, 165, 150, 45);
        UIGenericCreator.addLabelToFrame(frame,"color",pageFont,50, 235, 150, 45 );
        UIGenericCreator.addLabelToFrame(frame,"Player 2",pageFont, 450, 95, 150, 45);
        UIGenericCreator.addLabelToFrame(frame,"Formation",pageFont,450, 165, 150, 45);
        UIGenericCreator.addLabelToFrame(frame,"color",pageFont,450, 235, 150, 45 );
        UIGenericCreator.addLabelToFrame(frame,"Game settings",pageFont,  340, 25, 194, 45);
        UIGenericCreator.addLabelToFrame(frame,"Game time",pageFont,235, 350, 150, 45 );
        UIGenericCreator.addLabelToFrame(frame,"Max score",pageFont,450, 350, 150, 45 );
    }

    private void initTextFields(){
        UIGenericCreator.setTextField(frame, ply1Name,pageFont,205, 95, 150, 45);
        UIGenericCreator.setTextField(frame,ply2Name,pageFont,605, 95, 150, 45);
    }

    private void initComboBoxes(){
        UIGenericCreator.addComboBoxToFrame(frame, ply1Form,205, 165, 60, 45);
        UIGenericCreator.addComboBoxToFrame(frame,ply1Color,205, 235, 60, 45);
        UIGenericCreator.addComboBoxToFrame(frame,ply2Form,605, 165, 60, 45);
        UIGenericCreator.addComboBoxToFrame(frame,ply2Color,605, 235, 60, 45);
        UIGenericCreator.addComboBoxToFrame(frame,maxTime,235, 400 ,150, 45);
        UIGenericCreator.addComboBoxToFrame(frame,maxScore,450, 400, 150, 45);
    }

    private void initBtns(){
        JButton startGameBtn = new JButton("Go!");
        UIGenericCreator.addJButtonToFrame(frame, startGameBtn,pageFont,Color.WHITE,Color.BLACK ,450, 500, 150, 45);
        startGameBtn.addActionListener(e ->{
            gsCtrl.initNewGame( ply1Name.getText(), ply2Name.getText(),(String)ply1Form.GetX().getSelectedItem(),(String)ply2Form.GetX().getSelectedItem(),(String)ply1Color.GetX().getSelectedItem(),(String)ply2Color.GetX().getSelectedItem(),(String)maxTime.GetX().getSelectedItem(),(String)maxScore.GetX().getSelectedItem());
            frame.dispose();
            StartScreen.frame.dispose();
            GameScreen gs = new GameScreen();
        });
        JButton backBtn = new JButton("Back");
        UIGenericCreator.addJButtonToFrame(frame, backBtn, pageFont,Color.WHITE, Color.BLACK, 235, 500, 150, 45);
        backBtn.addActionListener(arg0 -> {
            StartScreen.frame.setVisible(true);
            frame.dispose();
        });
    }

    private void initialize() throws IOException {
        initFrame();
        initLabels();
        initTextFields();
        initComboBoxes();
        initBtns();
        frame.setVisible(true);
    }

}