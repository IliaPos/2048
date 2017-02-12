package testOne;

import java.awt.Font;

public class Constants {
	
	// Константы
	
     public static final int CELL_SIZE = 64;
	 public static final int COUNT_CELLS_X = 4;
	 public static final int COUNT_CELLS_Y = 4;
	 
	 
		
	 public static final int SCREEN_WIDTH = COUNT_CELLS_X *CELL_SIZE;
	 public static final int SCREEN_HEIGHT = COUNT_CELLS_Y *CELL_SIZE;

	 public static final int CHANCE_OF_LUCKY_SPAWN = 17; //%

	 public static final int LUCKY_INITIAL_CELL_STATE = 4;
	 public static final int INITIAL_CELL_STATE = 2;
	 public static final Font FONT_BIG = new Font(Font.DIALOG, Font.BOLD, 60 );
	 public static final Font FONT_SMALL = new Font(Font.DIALOG, Font.BOLD, 45 );
	 public static final Font FONT_SMALL_SMALL = new Font(Font.DIALOG, Font.BOLD, 35 );

	 public static final int COUNT_INITITAL_CELLS = 2;
	 public static final String SCREEN_NAME = "game2048";
	 

}
