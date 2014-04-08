package br.com.hrdev;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.components.MenuBar;
import br.com.hrdev.utils.Icons;
import br.com.hrdev.views.DashboardView;
import br.com.hrdev.views.WelcomeView;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private static final String Title = "Astha";
	private static final Dimension Size = new Dimension(1024, 768);

	private JPanel painel;
	private CardLayout layout;
	
	public static final String Welcome = "welcome";
	public static final String Dashboard = "dashboard";
	
	private Window(){
		setAtributos();
		setViews();
		pack();
		setVisible(true);
	}
	
	private void setAtributos() {
		setTitle(Title);
		setPreferredSize(Size);
		setMinimumSize(Size);
		setResizable(true);
		setLocationRelativeTo(null);
		setFocusable(true);
		setJMenuBar(new MenuBar(this));
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(Icons.Favicon.getImage());
		layout = new CardLayout();
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(10,10,10,10));
		painel.setLayout(layout);
		
		getContentPane().add(painel,BorderLayout.CENTER);
	}
	
	private void setViews(){
		painel.add(new WelcomeView(this),Welcome);
		painel.add(new DashboardView(this),Dashboard);
	
	}
	
	public void changeView(String idView){
		layout.show(painel, idView);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
	          public void run() {
	        	  new Window();
	          }
	    });
	}
}
