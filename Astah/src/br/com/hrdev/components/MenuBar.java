package br.com.hrdev.components;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import br.com.hrdev.Window;
import br.com.hrdev.events.AjudaAction;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	private Window window;
	
	public MenuBar(Window window){
		super();
	}
	
	public void setHelp(){
		JMenu ajuda = new JMenu("Ajuda");
		ajuda.addMenuListener(new AjudaAction(window));
		add(Box.createHorizontalGlue());
		add(ajuda);
	}
	
	
}
