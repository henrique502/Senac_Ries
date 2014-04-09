package br.com.hrdev.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import br.com.hrdev.Window;

public class AjudaAction implements ActionListener, MenuListener {

	private Window window;
	
	public AjudaAction(Window window){
		this.window = window;
	}
	
	private void openText(){
		JOptionPane.showMessageDialog(window, "By Henrique Rieger\nwww.hrdev.com.br", "Ajuda", JOptionPane.PLAIN_MESSAGE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		openText();
	}

	@Override
	public void menuCanceled(MenuEvent e) {}

	@Override
	public void menuDeselected(MenuEvent e) {}

	@Override
	public void menuSelected(MenuEvent e) {
		openText();
	}

}
