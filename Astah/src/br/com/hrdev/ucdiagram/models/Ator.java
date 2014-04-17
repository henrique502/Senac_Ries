package br.com.hrdev.ucdiagram.models;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.hrdev.ucdiagram.utils.Fonts;
import br.com.hrdev.ucdiagram.utils.Images;

public class Ator extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static Dimension Size = new Dimension(150, 175);
	private JLabel nome, img;
	
	public Ator(String nome) {
		this.nome = new JLabel(nome);
		this.nome.setHorizontalAlignment(SwingConstants.CENTER);
		this.nome.setFont(new Font(Fonts.ShadowsIntoLight.getFamily(), Font.PLAIN, 14));
		this.img = new JLabel(Images.Ator);
		
		setLayout(new BorderLayout());
		setOpaque(false);
		setSize(Size);
		setPreferredSize(Size);
		setFocusable(true);
		setVisible(true);

		add(this.img,BorderLayout.CENTER);
		add(this.nome,BorderLayout.SOUTH);
	}

	public String getName() {
		return this.nome.getText();
	}
	
	public String toString(){
		return this.nome.getText();
	}

	public void setPoint(Point point) {
		setBounds(point.x, point.y, getWidth(), getHeight());
		
	}
}
