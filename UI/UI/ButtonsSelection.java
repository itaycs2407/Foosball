package UI;

import Controllers.BtnSelectCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonsSelection {

	private JFrame frame = new JFrame();
	private JTextField txtW = new JTextField();
	private JTextField txtS = new JTextField();
	private JTextField txtO = new JTextField();
	private JTextField txtL = new JTextField();
	private String tempP1u = txtW.getText();
	private String tempP1d = txtS.getText();
	private String tempP2u = txtO.getText();
	private String tempP2d = txtL.getText();
	BtnSelectCtrl btnControler = new BtnSelectCtrl();
	private Font pageFont = new Font("Tahoma", Font.PLAIN, 40);
	private Font playerAndButtonsFont = new Font("Tahoma", Font.PLAIN, 30);
	JButton btnSave = new JButton("Save");
	JButton btnBack = new JButton("Back");
	JButton btnChange = new JButton("Change Buttons");

	public ButtonsSelection() throws IOException {
		initialize();
	}

	private void initFrame() throws IOException {
		UIGenericCreator.defaultFrame(frame);
	}

	private void initLabels() {
		UIGenericCreator.addLabelToFrame(frame, "Button Selection",pageFont,290, 50, 350, 80);
		UIGenericCreator.addLabelToFrame(frame, "Player 1",playerAndButtonsFont,135, 135, 110, 45);
		UIGenericCreator.addLabelToFrame(frame, "Player 2",playerAndButtonsFont,600, 135, 110, 45);
	}

	private void initJbuttons() {
		UIGenericCreator.addJButtonToFrame(frame, btnSave,playerAndButtonsFont,Color.white,Color.BLACK,450,500,160,40);
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!btnControler.changesToStore(txtW.getText(),txtS.getText(),txtO.getText(),txtL.getText())) {
					JOptionPane.showMessageDialog(frame,"UP/DOWN Button must conatin one character!\nAll characters must be different from each other!", "Error", JOptionPane.ERROR_MESSAGE);
					txtW.setText(tempP1u);
					txtS.setText(tempP1d);
					txtO.setText(tempP2u);
					txtL.setText(tempP2d);
				}
				else
					JOptionPane.showMessageDialog(frame,"Changes Saved!");
			}
		});

		UIGenericCreator.addJButtonToFrame(frame, btnBack,playerAndButtonsFont,Color.white,Color.BLACK,250,500,160,40);
		btnBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				StartScreen.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
	}

	private void initTextFields() {
		UIGenericCreator.setTextField(frame,txtW,playerAndButtonsFont,150,220,35,35);
		txtW.setText(btnControler.GetP1U());
		tempP1u = txtW.getText();
		txtW.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnControler.changesToStore(txtW.getText(), txtS.getText(), txtO.getText(), txtL.getText());
				txtW.setText(btnControler.GetP1U());
				frame.revalidate();
				frame.repaint();
			}
		});

		UIGenericCreator.setTextField(frame,txtS,playerAndButtonsFont,150,300,35,35);
		txtS.setText(btnControler.GetP1D());
		tempP1d = txtS.getText();
		txtS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnControler.changesToStore(txtW.getText(), txtS.getText(), txtO.getText(), txtL.getText());
				txtS.setText(btnControler.GetP1D());
				frame.revalidate();
				frame.repaint();
			}
		});

		UIGenericCreator.setTextField(frame,txtO,playerAndButtonsFont,615,220,35,35);
		txtO.setText(btnControler.GetP2U());
		tempP2u = txtO.getText();
		txtO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnControler.changesToStore(txtW.getText(), txtS.getText(), txtO.getText(), txtL.getText());
				txtO.setText(btnControler.GetP2U());
				frame.revalidate();
				frame.repaint();
			}
		});

		UIGenericCreator.setTextField(frame,txtL,playerAndButtonsFont,615,300,35,35);
		txtL.setText(btnControler.GetP2D());
		tempP2d = txtL.getText();
		txtL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnControler.changesToStore(txtW.getText(),txtS.getText(),txtO.getText(),txtL.getText());
				txtL.setText(btnControler.GetP2D());
				frame.revalidate();
				frame.repaint();
			}
		});
	}

	private void initialize() throws IOException {
		initFrame();
		initLabels();
		initJbuttons();
		initTextFields();
		frame.setVisible(true);
	}
}

