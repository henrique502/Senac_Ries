package br.com.hrdev.views;

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

import br.com.hrdev.Window;
import br.com.hrdev.components.MenuBar;
import br.com.hrdev.events.CloseEvent;
import br.com.hrdev.utils.Fonts;

@SuppressWarnings("serial")
public class WelcomeView extends JPanel {
	
	private Window window;
	
	private JButton ok;
	
	public WelcomeView(Window window){
		this.window = window;
		this.setup();
	}
	
	public void setup(){
		setLayout(new BorderLayout(10,10));
		
		JLabel mensagem = new JLabel();
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		mensagem.setFont(new Font(Fonts.ShadowsIntoLight.getFamily(), Font.PLAIN, 50));
		mensagem.setText("<html><center>Bem-Vindo!<br>ao<br>" + Window.Title + "</center></html>");
		mensagem.setHorizontalAlignment(SwingConstants.CENTER);

		ok = new JButton();
		ok.setText("Ok");
		ok.addActionListener(new ContinueAction());
		ok.setFocusable(true);
		
		JButton sair = new JButton();
		sair.setText("Sair");
		sair.addActionListener(new CloseEvent());
		sair.setFocusable(false);
		
		buttons.add(ok);
		buttons.add(sair);
		
		add(mensagem,BorderLayout.CENTER);
		add(buttons,BorderLayout.SOUTH);
		
		setUpdatePanel();
		setMenu();
	}
	
	private void setMenu(){
		MenuBar menubar = (MenuBar) window.getJMenuBar();
		menubar.removeAll();

		menubar.setHelp();
		menubar.revalidate();
	}
	
	private void setUpdatePanel(){
		addComponentListener (new ComponentAdapter(){
	        public void componentShown(ComponentEvent e){
	        	setMenu();
	        	ok.requestFocus();

	        }

	        public void componentHidden(ComponentEvent e){
	        	
	        }
	    });
	}
	
	private class ContinueAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.changeView(Window.Dashboard);
		}
	}
}
