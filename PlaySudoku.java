import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class PlaySudoku {

	public static void main(String[] args) throws IOException {
		JFrame mainFrame = new JFrame("Sudoku v2.5.1");
    	Framer.method(mainFrame, "img/bg.png");
    	JPanel mainPanel = new JPanel();
    	JButton newgame, howtoplay, highscores, exit;
		newgame = new JButton();
		Framer.setButtonP(newgame, "img/newgame.png", "img/newgame_rollover.png", "img/newgame_selected.png", 200, 300);
		newgame.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				mainFrame.dispose();
				try {
					NewGame ng = new NewGame();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		howtoplay = new JButton();
		Framer.setButtonP(howtoplay, "img/howtoplay.png", "img/howtoplay_rollover.png", "img/howtoplay_selected.png", 200, 350);
		howtoplay.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				mainFrame.dispose();
				try {
					HowToPlay htp = new HowToPlay();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		highscores = new JButton();
		Framer.setButtonP(highscores, "img/highscores.png", "img/highscores_rollover.png", "img/highscores_selected.png", 200, 400);
		highscores.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				mainFrame.dispose();
				try {
					HighScores hs = new HighScores();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		exit = new JButton();
		Framer.setButtonP(exit, "img/exit.png", "img/exit_rollover.png", "img/exit_selected.png", 200, 450);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				System.exit(0);
			}
		});
		mainFrame.add(newgame);
		mainFrame.add(howtoplay);
		mainFrame.add(highscores);
		mainFrame.add(exit);
		mainFrame.add(mainPanel);
    	mainFrame.setVisible(true);
	}
}
