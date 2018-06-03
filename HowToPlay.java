import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class HowToPlay {
	public HowToPlay() throws IOException {
		JFrame htp = new JFrame("How To Play?");
		Framer.method(htp, "img/bg_pages.png");
		JPanel p = new JPanel();
		JButton mainmenu = new JButton("");
		Framer.setButtonP(mainmenu, "img/mainmenu.png", "img/mainmenu_rollover.png", "img/mainmenu_selected.png", 200, 500);
		mainmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				htp.dispose();
				try {
					PlaySudoku.main(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		htp.add(mainmenu);
		htp.add(p);
		htp.setVisible(true);
	}
}
