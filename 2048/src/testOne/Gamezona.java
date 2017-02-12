package testOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Gamezona {
	private JLabel[][] gameField = new JLabel[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];
	JLabel labelScore;
	JLabel labelScoreTop;
	private final Logic logic = new Logic(this);
	JFrame newJ;

	private void init() {
		newJ = new JFrame("DTSV (alpha version 0.2.2)");
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(new KeyDispatcher(this.logic, newJ));

		newJ.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("2048r.PNG")));
		newJ.setSize(700, 700);
        newJ.setResizable(false);

//		 newJ.setVisible(true);

		newJ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// newJ.setLayout(new BorderLayout(1,1));
		// newJ.setLayout(new GridLayout(10,10));

		JPanel jpBroardM = new JPanel(new BorderLayout());

		JPanel jpNorth = new JPanel(new FlowLayout());
		jpNorth.setBackground(new Color(191,239	,255));
		jpNorth.setPreferredSize(new Dimension(100, 100));
		
		JLabel pustotaLeft = new JLabel(" ");
		pustotaLeft.setPreferredSize(new Dimension(0, 70));
		jpNorth.add(pustotaLeft);

		JButton buttonStart = new JButton("START");
		buttonStart.setForeground(Color.WHITE);
		buttonStart.setBackground(new Color(238,106,80));
		buttonStart.addMouseListener(new MouseDispatcher(this.logic));
		jpNorth.add(buttonStart);
		
	

		JButton back = new JButton("BACK");
		back.setForeground(Color.WHITE);
		back.setBackground(new Color(238,106,80));
		back.addMouseListener(new MouseDispatcher(this.logic));
		jpNorth.add(back);
		
		JLabel pustotaRight = new JLabel("   Score: ");
		pustotaRight.setPreferredSize(new Dimension(50, 10));
		jpNorth.add(pustotaRight);


		Font srift = new Font(Font.DIALOG, Font.BOLD, 27);
		labelScore = new JLabel("0");
		labelScore.setFont(srift);
		jpNorth.add(labelScore);
		jpBroardM.add(jpNorth, BorderLayout.NORTH);
		
		JLabel pustotaRightRight = new JLabel("                Record: ");
		pustotaRightRight.setPreferredSize(new Dimension(100, 10));
		jpNorth.add(pustotaRightRight);
		
		Font sriftTop = new Font(Font.DIALOG, Font.BOLD, 27);
		Integer currentTopScore = logic.readerScoreTop();
		labelScoreTop = new JLabel(currentTopScore.toString());
		labelScoreTop.setFont(sriftTop);
		jpNorth.add(labelScoreTop);
		jpBroardM.add(jpNorth, BorderLayout.NORTH);

		JPanel jpSouth = new JPanel();
		jpSouth.setBackground(new Color(191,239	,255));
		
		jpSouth.setPreferredSize(new Dimension(100, 100));
		jpBroardM.add(jpSouth, BorderLayout.SOUTH);
		
		JLabel pustotaSouth = new JLabel("To clear the record, click: \"n\" ");
		jpSouth.add(pustotaSouth);
		
		JPanel jpWest = new JPanel();
		jpWest.setBackground(new Color(191,239	,255));
		jpWest.setPreferredSize(new Dimension(100, 100));
		jpBroardM.add(jpWest, BorderLayout.WEST);
		
		JPanel jpEast = new JPanel();
		jpEast.setBackground(new Color(191,239	,255));
		jpEast.setPreferredSize(new Dimension(100, 100));
		jpBroardM.add(jpEast, BorderLayout.EAST);
		JPanel jpBroard = new JPanel(new GridLayout(Constants.COUNT_CELLS_X, Constants.COUNT_CELLS_Y));
		jpBroard.setSize(500, 500);

		for (int i = 0; i < 4; i++) {
			for (int s = 0; s < 4; s++) {
				JLabel jl = new JLabel(" ");
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				jl.setVerticalAlignment(SwingConstants.CENTER);
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jl.setSize(new Dimension(100, 100));
				jl.setOpaque(true);
				jl.setBackground(Color.GRAY);
				Font font = Constants.FONT_BIG;
				jl.setFont(font);
				gameField[i][s] = jl;
				jpBroard.add(jl);
			}
		}
		jpBroardM.add(jpBroard, BorderLayout.CENTER);
		// jpBroard.add(gameField);


		newJ.add(jpBroardM);
		newJ.setLocation(290, 50);
		newJ.setVisible(true);

	}

	// private static Gamezona gamezona;

	public static void main(String[] args) {
		Gamezona gameZona = new Gamezona();
		gameZona.init();
//		Properties p = System.getProperties();
//		p.list(System.out);
		

	}

	public void refresh(int theField[][], int score, int scoreTop) {
//		System.out.println(scoreTop);
		for (int i = 0; i < 4; i++) {
			for (int s = 0; s < 4; s++) {
				if (theField[i][s] == 0) {
					gameField[i][s].setText(" ");
					setColors(gameField[i][s], 0);
				} else {
					gameField[i][s].setText(Integer.toString(theField[i][s]));
					setColors(gameField[i][s], theField[i][s]);
				}

			}
		}
		labelScore.setText(Integer.toString(score));
		labelScoreTop.setText(Integer.toString(scoreTop));
		// newJ.requestFocus();
	}

	private void setColors(JLabel label, int number) {
		switch (number) {
		case 0:
			label.setForeground(Color.BLACK);
			label.setBackground(Color.GRAY);

			// label.repaint();
			break;
		case 2:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(255, 248, 220));
			// label.repaint();
			break;
		case 4:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(255, 228, 196));
			// label.repaint();
			break;
		case 8:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(255, 222, 173));
			// label.repaint();
			break;
		case 16:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(222, 184, 135));
			// label.repaint();
			break;
		case 32:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(210, 180, 140));
			// label.repaint();
			break;
		case 64:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(188, 143, 143));
			// label.repaint();
			break;
		case 128:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(244, 164, 96));
			// label.repaint();
			break;
		case 256:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(218, 165, 32));
			// label.repaint();
			break;
		case 512:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(205, 133, 63));
			// label.repaint();
			break;
		case 1024:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(210, 105, 30));
			// label.repaint();
			break;
		case 2048:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(139, 69, 19));
			// label.repaint();
			break;
		case 4096:
			label.setForeground(Color.BLACK);
			label.setBackground(new Color(165, 42, 42));
			// label.repaint();
			break;
		default:

			label.setForeground(Color.BLACK);
			label.setBackground(new Color(255, 0, 0));
			// label.repaint();

			break;

		}
		if (number > 8192) {
			label.setFont(Constants.FONT_SMALL_SMALL);
		} else if (number > 512) {
			label.setFont(Constants.FONT_SMALL);

		} else {
			label.setFont(Constants.FONT_BIG);
		}

	}

}
