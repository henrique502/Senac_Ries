package br.com.hrdev.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class UIToolBarButton extends JToggleButton implements KeyListener {
	
	
	public UIToolBarButton(ImageIcon icon, String tooltip){
		super(icon);
		setToolTipText(tooltip);
		setFocusable(true);
		addKeyListener(this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			setSelected(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
