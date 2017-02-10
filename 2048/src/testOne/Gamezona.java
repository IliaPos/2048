package testOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Gamezona {
	private JLabel[][] gameField = new JLabel[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];

	private final Logic logic = new Logic(this);
	
	private void init() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyDispatcher(this.logic));
		JFrame newJ= new JFrame("Game");
		newJ.setSize(700, 700);
		newJ.setResizable(false);


		newJ.setVisible(true);
		newJ.getContentPane().setBackground(Color.BLACK);
        
		newJ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newJ.setLayout(new BorderLayout(1,1));
//		newJ.setLayout(new GridLayout(10,10));
		
		
		
		
		JPanel jpBroardM = new JPanel (new BorderLayout ());
		JPanel jpNorth = new JPanel ();
		jpNorth.setPreferredSize(new Dimension (100,100));
		
		JButton buttonStart = new JButton("(START)");
		buttonStart.addMouseListener(new MouseDispatcher(this.logic));
		jpNorth.add(buttonStart, BorderLayout.PAGE_END);
		jpBroardM.add(jpNorth,BorderLayout.NORTH);
		
		
		JButton back = new JButton("Back");
		back.addMouseListener(new MouseDispatcher(this.logic));
		jpNorth.add(back, BorderLayout.PAGE_START);
		jpBroardM.add(jpNorth,BorderLayout.NORTH);
		
		
		JPanel jpSouth = new JPanel ();
		jpSouth.setPreferredSize(new Dimension (100,100));
		jpBroardM.add(jpSouth,BorderLayout.SOUTH);
		JPanel jpWest = new JPanel ();
		jpWest.setPreferredSize(new Dimension (100,100));
		jpBroardM.add(jpWest,BorderLayout.WEST);
		JPanel jpEast = new JPanel ();
		jpEast.setPreferredSize(new Dimension (100,100));
		jpBroardM.add(jpEast,BorderLayout.EAST);
		JPanel jpBroard = new JPanel(new GridLayout (Constants.COUNT_CELLS_X,Constants.COUNT_CELLS_Y));
		jpBroard.setSize(500,500);
		
		
		
		
		for (int i = 0; i<4; i++){
			for ( int s = 0 ; s<4; s++){
				JLabel jl = new JLabel (" ");
				jl.setHorizontalAlignment(SwingConstants.CENTER);
				jl.setVerticalAlignment(SwingConstants.CENTER);
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jl.setSize(new Dimension (100,100));
				Font font = new Font(Font.DIALOG,Font.BOLD,60);
				jl.setFont(font);
				gameField[i][s] = jl;
				jpBroard.add(jl);
			}
		}
		jpBroardM.add(jpBroard,BorderLayout.CENTER);
//		jpBroard.add(gameField);
	
			
		
//		Canvas canv = new Canvas();
//        newJ.add(canv);		
        newJ.add(jpBroardM);
        newJ.setLocation(290, 50);
        newJ.setVisible(true);
        
       
     
        }
	 public static int score; //Сумма всех чисел на поле
//	 private static Gamezona gamezona;

	public static void main(String[] args) {
		Gamezona gameZona = new Gamezona();
		gameZona.init();
		
		
		
	

	}
	// refresh поля (заполнение его нулями)
	public void refresh(int theField[][]){
		for (int i = 0; i<4; i++){
			for ( int s = 0 ; s<4; s++){
				if (theField[i][s] == 0){
					gameField[i][s].setText(" ");
					
				} else {
					gameField[i][s].setText(Integer.toString(theField[i][s]));
				}
					
				
				
			}
		}
	}

	

}	 


