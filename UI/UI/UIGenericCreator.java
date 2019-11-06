package UI;

import Utils.FileUtils;
import Utils.Tuple;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UIGenericCreator {

    public static void defaultFrame(JFrame frame) throws IOException {
        ImageIcon imageIcon = new ImageIcon(FileUtils.relativePathToFullPath("\\Images\\GameIcon.png"));
        frame.setIconImage(imageIcon.getImage());
        frame.setBounds(StartScreen.window_x, StartScreen.window_y, StartScreen.window_width, StartScreen.window_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setContentPane(new JLabel(UIGenericCreator.setImageIconForFrame(FileUtils.relativePathToFullPath("\\Images\\HomBG2.png"))));
        JLabel icon = new JLabel("");
        icon.setIcon(UIGenericCreator.setImageIconForFrame(FileUtils.relativePathToFullPath("\\Images\\GameIcon.png")));
        frame.getContentPane().add(icon);
    }

    public static JButton addJButtonToFrame(JFrame frame, JButton button, Font font, Color background, Color foreGround, int x, int y, int width, int height){
        button.setForeground(foreGround);
        button.setBackground(background);
        button.setFont(font);
        button.setBounds(x,y,width,height);
        frame.getContentPane().add(button);
        return button;
    }
    public static JComboBox addComboBoxToFrame(JFrame frame, Tuple<JComboBox, DefaultComboBoxModel> comboTuple, int x, int y, int width, int height) {
        comboTuple.GetX().setModel(comboTuple.GetY());
        comboTuple.GetX().setBounds(x,y,width,height);
        frame.getContentPane().add(comboTuple.GetX());
        return comboTuple.GetX();
    }
    public static JLabel addLabelToFrame(JFrame frame, String lblText, Font font, int x, int y, int width, int height){
        JLabel lbl = new JLabel(lblText);
        lbl.setFont(font);
        lbl.setBounds(x, y, width, height);
        frame.getContentPane().add(lbl);
        return lbl;
    }
    public static JLabel addLabelToFrame(JFrame frame, String lblText, Font font, Color background, Color foreGround, int x, int y, int width, int height){
        JLabel lbl = new JLabel(lblText);
        lbl.setForeground(foreGround);
        lbl.setBackground(background);
        lbl.setFont(font);
        lbl.setBounds(x, y, width, height);
        frame.getContentPane().add(lbl);
        return lbl;
    }
    public static void setTextField(JFrame frame, JTextField txtField, Font font, int x, int y, int width, int height){
        txtField.setFont(font);
        txtField.setBounds(x, y,width,height);
        frame.getContentPane().add(txtField);
        txtField.setColumns(10);
    }

    public static JLabel addImageToFrame(JFrame frame,ImageSettings image) {
        JLabel label;
        label = new JLabel("");
        label.setIcon(setImageIconForFrame(image.path));
        frame.getContentPane().add(label);
        label.setBounds(image.x, image.y, image.width, image.height);
        return label;
    }

    public static JLabel addgifImageToFrame(JFrame frame, ImageSettings gif){
        JLabel label = new JLabel("");
        URL url = frame.getClass().getResource(gif.path);
        try {
            BufferedImage image = ImageIO.read(new URL("file:" + gif.path));
            label.setIcon(new ImageIcon(new URL("file:" + gif.path)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.getContentPane().add(label);
        label.setBounds(gif.x, gif.y, gif.width, gif.height);
        return label;
    }

    public static ImageIcon setImageIconForFrame(String path){
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            BufferedImage bfimg = ImageIO.read(fis);
            return new ImageIcon(bfimg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static JCheckBox addCheckboxToFrame(JFrame frame, JCheckBox checkBox, int x, int y, int width, int height){
        checkBox.setBounds(x, y, width, height);
        frame.getContentPane().add(checkBox);
        return checkBox;
    }

}
