package br.com.hrdev.ucdiagram.models;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.Serializable;

import br.com.hrdev.ucdiagram.utils.Fonts;
import br.com.hrdev.ucdiagram.utils.Images;

public class Ator extends Component implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Dimension Size = new Dimension(150, 175);
	private String nome;
	
	public Ator(String nome) {
		this.nome = nome;
		setSize(Size);
		setPreferredSize(Size);
		setFocusable(true);
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

	public void setPoint(Point point) {
		setBounds(point.x, point.y, getWidth(), getHeight());
		
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		Image img = Images.Ator.getImage();
		g.drawImage(img,0,0,null);
		
		g.setFont(new Font(Fonts.ShadowsIntoLight.getFamily(), Font.PLAIN, 14));
		FontMetrics fm = getFontMetrics(g.getFont());
		
		int textWidth = fm.stringWidth(this.nome);
		int x = (getWidth()  - textWidth)  / 2;
		int y = img.getHeight(null) + 20;
		
		g.drawString(this.nome, x, y);
		
		g.dispose();
	}
}
