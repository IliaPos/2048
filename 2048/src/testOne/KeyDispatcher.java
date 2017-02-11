package testOne;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class KeyDispatcher implements KeyEventDispatcher {
	private final Logic logic;
	private final Component comp;

	public KeyDispatcher(Logic logic, Component comp) {
		super();
		this.logic = logic;
		this.comp = comp;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
//		JOptionPane.showMessageDialog(null, e.getComponent().toString(), "G!!" + " " + "y = " + " ",
//				JOptionPane.INFORMATION_MESSAGE);
		if (e.getComponent().equals(this.comp) || 
				((JButton)e.getComponent()).getText().equals("START")|| 
				((JButton)e.getComponent()).getText().equals("Back")) {
			
		
	
			if (e.getID() == KeyEvent.KEY_PRESSED) {

				int keyCode = e.getKeyCode();
				switch (keyCode) {
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
			
		} 
			
		return false;

	}
}
