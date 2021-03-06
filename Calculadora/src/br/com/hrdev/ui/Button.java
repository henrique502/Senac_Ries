package br.com.hrdev.ui;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton {

	private char value;
	
	public Button(char value){
		super("" + value);
		setFocusable(false);
		this.value = value;
		
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(true);
		setContentAreaFilled(false);
	}
	
	public char getValue(){
		return this.value;
	}
	
}
