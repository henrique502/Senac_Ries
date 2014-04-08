package br.com.hrdev.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import br.com.hrdev.Main;

public class CloseWindow implements ActionListener, WindowListener {

	private Main window;
	
	public CloseWindow(Main window){
		this.window = window;
	}
	
	private void saveAlldata(){
		window.getStorage().saveData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		saveAlldata();
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {
		saveAlldata();
	}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
