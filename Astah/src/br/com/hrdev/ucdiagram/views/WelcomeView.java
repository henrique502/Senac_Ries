package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.hrdev.ucdiagram.Window;
import br.com.hrdev.ucdiagram.components.UIMenuBar;
import br.com.hrdev.ucdiagram.events.CloseEvent;
import br.com.hrdev.ucdiagram.events.NovoProjetoEvent;
import br.com.hrdev.ucdiagram.utils.Fonts;

@SuppressWarnings("serial")
public class WelcomeView extends JPanel {
	
	private Window window;
	
	public WelcomeView(Window window){
		this.window = window;
		this.setup();
	}
	
	public void setup(){
		setLayout(new BorderLayout(10,10));
		setWelcome();
		setButtons();
		setMenu();
		setUpdatePanel();
	}

	private void setWelcome(){
		JLabel mensagem = new JLabel();
		mensagem.setFont(new Font(Fonts.ShadowsIntoLight.getFamily(), Font.PLAIN, 50));
		mensagem.setText("<html><center>Bem-Vindo!<br>ao<br>" + Window.Title + "</center></html>");
		mensagem.setHorizontalAlignment(SwingConstants.CENTER);
		add(mensagem,BorderLayout.CENTER);
	}
	
	private void setButtons(){
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton novo = new JButton();
		novo.setText("Novo Projeto");
		novo.addActionListener(new NovoProjetoEvent(window));
		buttons.add(novo);
		
		JButton carregar = new JButton();
		carregar.setText("Carregar Projeto");
		carregar.addActionListener(new CarregarProjetoAction());
		buttons.add(carregar);
		
		JButton sair = new JButton();
		sair.setText("Sair");
		sair.addActionListener(new CloseEvent());
		buttons.add(sair);
		
		add(buttons,BorderLayout.SOUTH);
	}
	
	private void setMenu(){
		UIMenuBar menubar = (UIMenuBar) window.getJMenuBar();
		menubar.removeAll();

		menubar.setHelp();
		menubar.revalidate();
	}
	
	private void setUpdatePanel(){
		addComponentListener (new ComponentAdapter(){
	        @Override
			public void componentShown(ComponentEvent e){
	        	setMenu();
	        }

	        @Override
			public void componentHidden(ComponentEvent e){}
	    });
	}
	
	private class CarregarProjetoAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.changeView(Window.CarregarProjeto);
		}
	}
}
