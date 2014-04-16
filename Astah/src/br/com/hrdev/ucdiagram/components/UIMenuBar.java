package br.com.hrdev.ucdiagram.components;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import br.com.hrdev.ucdiagram.Window;
import br.com.hrdev.ucdiagram.events.AjudaAction;
import br.com.hrdev.ucdiagram.utils.Icons;

@SuppressWarnings("serial")
public class UIMenuBar extends JMenuBar {
	
	private Window window;
	
	public UIMenuBar(Window window){
		super();
	}
	
	public void setHelp(){
		JMenu ajuda = new JMenu();
		ajuda.setIcon(Icons.Help);
		ajuda.addMenuListener(new AjudaAction(window));
		add(Box.createHorizontalGlue());
		add(ajuda);
	}
	
	
}
