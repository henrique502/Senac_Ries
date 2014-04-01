package br.com.hrdev.ui;

import java.awt.Color;
import java.awt.TextField;

@SuppressWarnings("serial")
public class Visor extends TextField {

	private boolean is_first, is_error;
	
	public Visor(){
		super("0");
		is_first = true;
		is_error = false;
		setEditable(false);
		setFocusable(false);
		setBackground(Color.white);
		
	}
	
	@Override
	public void setText(String text){
		if(is_first){
			is_first = false;
			setForeground(Color.black);
			super.setText(text);
		} else {
			super.setText(getText() + text);
		}
	}
	
	public void clear(){
		is_first = true;
		is_error = false;
		super.setText("0");
	}

	public void setError(String error) {
		setForeground(Color.red);
		is_first = true;
		is_error = true;
		super.setText(error);
	}
	
	public boolean isError(){
		return is_error;
	}
	
}
