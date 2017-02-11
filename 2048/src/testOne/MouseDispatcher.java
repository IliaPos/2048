package testOne;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;



public class MouseDispatcher extends MouseAdapter {
	private final Logic logic;
	
	
	
	public MouseDispatcher(Logic logic) {
		super();
		this.logic = logic;
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (e.getModifiers()==InputEvent.BUTTON1_MASK){
			JButton b = (JButton)e.getComponent();
			if ("START".equals(b.getText())){
				logic.createInitialCells();
			} else {
				logic.reBack();
			}
		    
//			logic.createCellTest();
		}
		
			
		
			
		
	}
	

}
