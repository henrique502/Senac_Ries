package br.com.hrdev.ucdiagram.models;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

import br.com.hrdev.ucdiagram.utils.Images;

public class Ator extends ComponentItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static Dimension Size = new Dimension(150, 175);
	
	public Ator(String nome) {
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
		
		Image img = Images.Ator.getImage();
		g.drawImage(img,0,0,null);
		
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		FontMetrics fm = getFontMetrics(g.getFont());
		
		int textWidth = fm.stringWidth(this.nome);
		int x = (getWidth()  - textWidth)  / 2;
		int y = img.getHeight(null) + 20;
		
		g.drawString(this.nome, x, y);
		
		if(hasSelected){
			float[] dash = {5.0f};
			g.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
			g.setColor(Color.black);
			g.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 7, 7));
		}
		
		g.dispose();
	}
}
