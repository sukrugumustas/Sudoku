import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class GamePage {
	private int showRandomly = 0;
	private int score = 0;
	private Random randomGenerator = new Random();
	private int[][] newGame = SudokuGenerator.array();
	private int[][] copyGame = newGame;
	private String[][] game = convertToString(newGame);
	public GamePage() throws IOException {
	}
	public GamePage (String difficulty) throws IOException {
		
		/*Here we create a random number according to game's
		 * difficulty. Via this number we can create a random
		 * game.*/
		if (difficulty.equalsIgnoreCase("Easy")) {
			showRandomly = 42 + randomGenerator.nextInt(9);
		}
		else if (difficulty.equalsIgnoreCase("Normal")) {
			showRandomly = 30 + randomGenerator.nextInt(9);
		}
		else {
			showRandomly = 18 + randomGenerator.nextInt(9);
		}
		int i = 0;
		while (i<81-showRandomly) {
			int tempRow = randomGenerator.nextInt(9);
			int tempColumn = randomGenerator.nextInt(9);
			if (copyGame[tempRow][tempColumn] == 0) {
				i+=0;
			}
			else {
				copyGame[tempRow][tempColumn] = 0;
				i++;
			}
		}
		
		//Frame
		JFrame frame = new JFrame("Game");
		Framer.method(frame, "img/bg_pages.png");
		
		//Panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(9, 9));
		panel.setBounds(75, 10, 450, 450);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		//Buttons
		JButton check = new JButton("Check"), solve = new JButton("Solve"),
				createNewGame = new JButton("New Game"), exit = new JButton("Exit");
		check.setBounds(75, 480, 150, 30);
		solve.setBounds(75, 520, 150, 30);
		createNewGame.setBounds(375, 480, 150, 30);
		exit.setBounds(375, 520, 150, 30);
		
		//Textfield to store the numbers and display on the screen.
		JTextField[][] textField = new JTextField[9][9];
		tFProperties(textField, game, copyGame, panel);
		
		//Button actions
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = Validity(textField, game);
				if (result != 0) {
					if (difficulty.equalsIgnoreCase("Easy")) {
						score = score - (3*result);
					}
					else if (difficulty.equalsIgnoreCase("Normal")) {
						score = score - (2*result);
					}
					else {
						score = score - result;
					}
					JOptionPane.showMessageDialog(null, "You have got wrong answers, please check them!", "Wrong Answers!", JOptionPane.ERROR_MESSAGE);
				}else {
					if (difficulty.equalsIgnoreCase("Easy")) {
						score = (81-showRandomly)*5;
					}
					else if (difficulty.equalsIgnoreCase("Normal")) {
						score = (81-showRandomly)*10;
					}
					else {
						score = (81-showRandomly)*15;
					}
					JOptionPane.showMessageDialog(null, "Well Done! Your score is " + score + ".", "Correct", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = Validity(textField, game);
				if (result != 0) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if (difficulty.equalsIgnoreCase("Easy")) {
							score = score - 3*(81-showRandomly);
						}
						else if (difficulty.equalsIgnoreCase("Normal")) {
							score = score - 2*(81-showRandomly);
						}
						else {
							score = score + showRandomly - 81;
						}
						solver(textField, game, panel);
						JOptionPane.showMessageDialog(null, "You have lost the game. Your score is " + score + ".", "You Lost", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
					else {
					}	
				} else {
						JOptionPane.showMessageDialog(null, "Well Done! Your score is " + score + ".", "Correct", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		createNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "New Game?", JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION) {
					frame.dispose();
					try {
						new GamePage(difficulty);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit?", JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		frame.add(check);
		frame.add(solve);
		frame.add(createNewGame);
		frame.add(exit);
		frame.add(panel);
		frame.setVisible(true);
	}
	/*This method will convert our arrayed game to strings thus
	 * we can place these strings to the screen and when we get
	 * values from user it will be lot easier to check the results.*/
	private static String[][] convertToString (int[][] game) {
		String[][] result = new String[9][9];
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				result[i][j] = Integer.toString(game[i][j]);
			}
		}
		return result;
	}
	/*This method will set our JTextField appearance on the screen.*/
	private static void tFProperties (JTextField[][] textField, String[][] game, int[][] copyGame, JPanel panel) {
		for (int i = 0; i<9; i++){
			for (int j=0; j<9; j++) {
				if (copyGame[i][j] == 0) {
					textField[i][j] = new JTextField("");
					textField[i][j].setBackground(Color.decode("#ffffff"));
					textField[i][j].setForeground(Color.decode("#ff0000"));
					panel.add(textField[i][j]);
				}
				else {
					textField[i][j] = new JTextField(game[i][j]);
					textField[i][j].setEditable(false);
					panel.add(textField[i][j]);
				}
				if (j==2 || j==5){
					if (i==2 || i==5) {
						textField[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
					}
					else {
						textField[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
					}
				}
				else if ((i==2 || i==5)&& (j!=2 || j!=5)) {
					textField[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
				}
				else {
					textField[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}
				textField[i][j].setHorizontalAlignment(JTextField.CENTER);
				textField[i][j].setFont(new Font("Arial", Font.BOLD, 40));
			}
		}
	}
	/*This method will solve the game.*/
	private static void solver (JTextField[][] textField, String[][] game, JPanel panel) {
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				textField[i][j].setText(game[i][j]);
				textField[i][j].setEditable(false);
				textField[i][j].setBackground(Color.decode("#eeeeee"));
				textField[i][j].setForeground(Color.decode("#000000"));
			}
		}
	}
	/*This method will compare the values those user entered to values our original 
	 * game. If a value doesn't match we will get an integer different from zero
	 * which we will use when the check button is pressed. If a value matches prog-
	 * ram will prevent filling that blank again so we can only see wrong values.*/
	private static int Validity (JTextField[][] textField, String[][] game) {
		int falseCount = 0;
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				if (!(textField[i][j].getText().equalsIgnoreCase(game[i][j]))) {
					falseCount++;
				}
				else {
					textField[i][j].setEditable(false);
					textField[i][j].setBackground(Color.decode("#eeeeee"));
					textField[i][j].setForeground(Color.decode("#000000"));
				}
			}
		}
		return falseCount;
	}
}
