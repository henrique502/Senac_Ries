package br.com.hrdev.ucdiagram.models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.Serializable;

import br.com.hrdev.ucdiagram.utils.Fonts;

public class Caso extends ComponentItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static Dimension Size = new Dimension(150, 175);
	
	public Caso(String nome) {
		this.nome = nome;
		setup();
	}
	
	private void setup(){
		setSize(Size);
		setPreferredSize(Size);
		setFocusable(true);
	}

	public void setPoint(Point point) {
		setBounds(point.x, point.y, getWidth(), getHeight());
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		g.setColor(Color.yellow);
		g.drawOval(0, 0, getWidth(), getHeight());
		
		
		g.setFont(new Font(Fonts.ShadowsIntoLight.getFamily(), Font.PLAIN, 14));
		FontMetrics fm = getFontMetrics(g.getFont());
		
		int textWidth = fm.stringWidth(this.nome);
		int x = (getWidth()  - textWidth)  / 2;
		int y = (getHeight() / 2) - 14;
		
		g.setColor(Color.black);
		g.drawString(this.nome, x, y);
			
		if(hasFocus()){
			g.setColor(Color.black);
			g.drawLine(0, 0, getWidth(), getHeight());
		}
		
		g.dispose();
	}
}
