package br.com.hrdev.ucdiagram.models;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.Serializable;

import br.com.hrdev.ucdiagram.utils.Fonts;

public class CasoDeUso extends Component implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Dimension Size = new Dimension(150, 175);
	private String texto;
	
	public CasoDeUso(String texto) {
		this.texto = texto;
		setSize(Size);
		setPreferredSize(Size);
		setFocusable(true);
	}

	public String getName() {
		return this.texto;
	}
	
	public String getTexto() {
		return this.texto;
	}
	
	public String toString(){
		return this.texto;
	}

	public void setPoint(Point point) {
		setBounds(point.x, point.y, getWidth(), getHeight());
		
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setFont(new Font(Fonts.ShadowsIntoLight.getFamily(), Font.PLAIN, 14));
		FontMetrics fm = getFontMetrics(g.getFont());
		
		int textWidth = fm.stringWidth(this.texto);
		int x = (getWidth()  - textWidth)  / 2;
		int y = 20;
		
		g.drawString(this.texto, x, y);
		
		g.dispose();
	}
}
