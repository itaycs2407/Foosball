package UI;

import Controllers.LoadController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoadScreen {

    private JFrame frame;
    private JTable table;
    private LoadController controll ;
    private int tableSize;
    private JRadioButton buttons[] ;
    private Font pageFont;
    private ButtonGroup group;

    public LoadScreen() throws IOException {
        frame = new JFrame();
        controll = new LoadController();
        tableSize = controll.getTableSize();
        buttons = new JRadioButton[tableSize];
        pageFont = new Font("Tahoma", Font.PLAIN, 40);
        initialize();
    }

    private void initFrame() throws IOException {
        UIGenericCreator.defaultFrame(frame);
    }

    private void initButtons(JRadioButton[] buttons, int x, int start_y, int width, int height) {
        group = new ButtonGroup();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JRadioButton("");
            buttons[i].setBounds(x, start_y, width, height);
            start_y+=16;
            group.add(buttons[i]);
        }
        addRadioButtonsToFrame(buttons);
    }

    private void initTable() {
        table = new JTable();
        table.setColumnSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
        table.setBounds(90, 235, 727, getJtableHeight());
        frame.getContentPane().add(table);
        table.setModel(controll.getTable());
    }

    private void initLabels() {
        UIGenericCreator.addLabelToFrame(frame, "Load Game",pageFont,346, 51, 215, 82);
    }

    private void initJButtons() {
        JButton btnBack = new JButton("Back");
        UIGenericCreator.addJButtonToFrame(frame, btnBack,pageFont,Color.white,Color.BLACK,245,445,180,40);
        btnBack.addActionListener(e -> {
            try {
                StartScreen start = new StartScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
        });

        JButton btnLoad = new JButton("Load!");
        UIGenericCreator.addJButtonToFrame(frame, btnLoad,pageFont,Color.white,Color.BLACK,485,445,180,40);
        btnLoad.addActionListener(e -> {
            if(controll.loadGameFromList(getSelectedButtonIndex())) {
                GameScreen gs = new GameScreen();
                frame.setVisible(false);
            }
            else
                JOptionPane.showMessageDialog(frame,"Please select a game to load", "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    private void initialize() throws IOException {
        initFrame();
        initButtons(buttons,68,250,22,16);
        initTable();
        initLabels();
        initJButtons();
        frame.setVisible(true);
    }

    private void addRadioButtonsToFrame(JRadioButton[] buttons) {
        for (JRadioButton button: buttons)
            frame.getContentPane().add(button);
    }

    private int getJtableHeight() {
        if (buttons.length <= 0)
            return 16;
        else
            return (buttons[0].getHeight() * (buttons.length+1));
    }

    private int getSelectedButtonIndex() {
        for (int i = 0; i< buttons.length; i++) {
            if (buttons[i].isSelected())
                return i;
        }
        return -1;
    }

}
