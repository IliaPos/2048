package testOne;
import java.awt.*;
import java.awt.event.KeyEvent;




public class KeyDispatcher implements KeyEventDispatcher {
	private final Logic logic;
	
	


	public KeyDispatcher(Logic logic) {
		super();
		this.logic = logic;
	}


	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		
		if (e.getID()==KeyEvent.KEY_PRESSED) {
			
			int keyCode = e.getKeyCode();
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		        	logic.shift(Direction.UP);
		            break;
		        case KeyEvent.VK_DOWN:
		        	logic.shift(Direction.DOWN);
		            break;
		        case KeyEvent.VK_LEFT:
		        	logic.shift(Direction.LEFT);		        
		            break;
		        case KeyEvent.VK_RIGHT:
		        	logic.shift(Direction.RIGHT);
		        	
		            break;
		     }
		}
		return false;
	}

}
