package br.com.hrdev.ucdiagram.models;

import java.awt.Component;
import java.awt.Point;

public abstract class ComponentItem extends Component {

	private static final long serialVersionUID = 1L;
	protected String nome;
	
	public boolean contains(Point p) {
		if(p == null)
			return false;
		
		return contains(p.x, p.y);
	}
	
	public boolean contains(int x, int y) {
		Point location = getLocation();
		
		if(x <= location.x) return false;
		if(y <= location.y) return false;
		
		if(x >= getWidth() + location.x) return false;
		if(y >= getHeight() + location.y) return false;
		
		return true;
	}
	
	public String getName() {
		return this.nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String toString(){
		return this.nome;
	}
}
