package UI;
import Controllers.StatisticCtrl;
import Utils.DataUtils;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class GameStatistics {
    private JFrame frame;
    private JTable table = new JTable();
    StatisticCtrl statisticCtrl = new StatisticCtrl();
    List<JCheckBox>  boxes = new ArrayList<JCheckBox>();
    List<JButton> removeBtns;
    private Font pageFont;
    private Font headerFont;

    public GameStatistics() {
        frame = new JFrame();
        try {
            UIGenericCreator.defaultFrame(frame);
        } catch (IOException e) {
            e.printStackTrace();
        }
        removeBtns = new ArrayList<>();
        headerFont = new Font("Tahoma", Font.PLAIN, 40);
        pageFont = new Font("Tahoma", Font.PLAIN, 15);
        init();
    }

    public void init(){
        initRemoveBtns();
        initCheckBoxes();
        table = tableInit();
        TableModel tempTable = statisticCtrl.getTable();
        table.setModel(tempTable);

        UIGenericCreator.addLabelToFrame(frame, "Statistics", headerFont, 350,50,165,85);

        JButton backBtn = UIGenericCreator.addJButtonToFrame(frame, new JButton("Back"),headerFont, Color.white,Color.black,383, 445,124,40);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statisticCtrl.preformSave();
                StartScreen.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        frame.setVisible(true);
    }

    private void initCheckBoxes() {
        int currY = 250;
        for (int i = 0; i < DataUtils.dal.GetGames().size(); i++){
            JCheckBox checkBox = UIGenericCreator.addCheckboxToFrame(frame, new JCheckBox(""),50,currY, 20,16);
            currY += 16;
            setCheckBoxAction(checkBox, i);
            boxes.add(checkBox);
        }
    }

    private void setCheckBoxAction(JCheckBox checkBox, int btnIndex) {
        checkBox.addActionListener(e -> {
            removeBtns.get(btnIndex).setVisible((checkBox.isSelected()));
        });
    }

    private void initRemoveBtns(){
        int currY = 250;
        for (int i =1; i<= DataUtils.dal.GetGames().size();i++){
            JButton btn = UIGenericCreator.addJButtonToFrame(frame, new JButton("remove"),pageFont, Color.white,Color.black,796, currY,85,16);
            currY+=16;
            setBtnAction(btn,i);
            btn.setVisible(false);
            removeBtns.add(btn);
        }
    }

    private void setBtnAction(JButton btn, int i) {
        btn.addActionListener(e ->{
            removeGame(Integer.parseInt(String.valueOf(table.getValueAt(i,0))));
            btn.setVisible(false);
            for (JCheckBox box :
                    boxes) {
                box.setVisible(false);
                frame.remove(box);
            }
            removeBtns = new ArrayList<JButton>();
            boxes = new ArrayList<JCheckBox>();
            initRemoveBtns();
            initCheckBoxes();
        });
    }
    //removing a game from dal when btn is clciked
    public void removeGame(int gameToRemove){
        statisticCtrl.gameToRemove(gameToRemove);
        frame.getContentPane().remove(table);
        table = tableInit();
        TableModel updatedTable = statisticCtrl.updateTable();
        table.setModel(updatedTable);
    }

    public JTable tableInit (){
        JTable jTable = new JTable();
        jTable.setColumnSelectionAllowed(true);
        jTable.setCellSelectionEnabled(true);
        jTable.setBounds(70, 235, 727, getJtableHeight());
        frame.getContentPane().add(jTable);
        return jTable;
    }

    private int getJtableHeight() {
        if (DataUtils.dal.GetGames().size() <= 0)
            return 16;
        else
            return (boxes.get(0).getHeight() * (DataUtils.dal.GetGames().size()+1));
    }
}