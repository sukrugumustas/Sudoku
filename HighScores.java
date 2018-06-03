import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class HighScores {
	public HighScores() throws IOException {
		JFrame hs = new JFrame("High Scores");
		Framer.method(hs, "img/bg_pages.png");
		JPanel p = new JPanel();
		JButton mainmenu = new JButton("");
		Framer.setButtonP(mainmenu, "img/mainmenu.png", "img/mainmenu_rollover.png", "img/mainmenu_selected.png", 200, 500);
		mainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hs.dispose();
				try {
					PlaySudoku.main(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		hs.add(mainmenu);
		hs.add(p);
		hs.setVisible(true);
	}
}
