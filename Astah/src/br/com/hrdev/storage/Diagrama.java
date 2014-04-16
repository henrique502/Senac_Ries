package br.com.hrdev.storage;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.JPanel;

public class Diagrama extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private Point point;
	
	public Diagrama(String nome){
		this.nome = nome;
		setLayout(null);
		setBackground(Color.white);
		setFocusable(false);
		this.point = new Point(0, 0);
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public Point getPoint(){
		return this.point;
	}
}
