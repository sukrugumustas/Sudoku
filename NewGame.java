import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
@SuppressWarnings("serial")
public class NewGame {

	public NewGame() throws IOException {
		JFrame ng = new JFrame("Sudoku - New Game");
		Framer.method(ng, "img/bg_pages.png");
		JPanel ngPanel = new JPanel();
		JButton play, mainmenu;
		mainmenu = new JButton("");
		Framer.setButtonP(mainmenu, "img/mainmenu.png", "img/mainmenu_rollover.png", "img/mainmenu_selected.png", 200, 500);
		mainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ng.dispose();
				try {
					PlaySudoku p = new PlaySudoku();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		play = new JButton("Play");
		play.setBounds(260, 400, 80, 30);
		Font font, labelFont;
		font = new Font("Cambria", Font.CENTER_BASELINE, 24);
		labelFont = new Font("Kristen ITC", Font.BOLD, 27);
		JLabel labelHeader = new JLabel("Please choose your difficulty and");
		labelHeader.setFont(labelFont);
		labelHeader.setBounds(55, 30, 600, 50);
		JLabel x = new JLabel("enter your name");
		x.setFont(labelFont);
		x.setBounds(180, 70, 400, 50);
		JComboBox<String> chooseDifficulty = new JComboBox<String>();
		chooseDifficulty.setBounds(245, 200, 110, 30);
		chooseDifficulty.setFont(font);
		chooseDifficulty.addItem("Easy");
		chooseDifficulty.addItem("Normal");
		chooseDifficulty.addItem("Hard");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ng.dispose();
				try {
					String difficulty = (String)chooseDifficulty.getSelectedItem();
					GamePage gamePage = new GamePage(difficulty);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		JTextArea userName = new JTextArea();
		userName.setText("Please enter your name");
		userName.setFont(font);
		userName.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				userName.setText(null);
			}
		});
		userName.setBounds(125, 300, 350, 30);
		ng.add(labelHeader);
		ng.add(x);
		ng.add(userName);
		ng.add(chooseDifficulty);
		ng.add(play);
		ng.add(mainmenu);
		ng.add(ngPanel);
		ng.setVisible(true);
	}
}