package br.com.hrdev.ucdiagram;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.ucdiagram.components.UIMenuBar;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.views.CarregarProjetoView;
import br.com.hrdev.ucdiagram.views.DashboardView;
import br.com.hrdev.ucdiagram.views.WelcomeView;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	public static final String Title = "UCDiagram";
	private static final Dimension Size = new Dimension(1024, 700);

	private JPanel painel;
	private CardLayout layout;
	
	private File projetoArquivo;
	private Projeto projeto;
	
	public static final String Welcome = "welcome";
	public static final String Dashboard = "dashboard";
	public static final String CarregarProjeto = "carregar-projeto";
	
	private Window(){}
	
	private void run(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e){}
		
		setAtributos();
		setViews();
		pack();
		setVisible(true);
	}
	
	private void setAtributos() {
		super.setTitle(Title);
		setPreferredSize(Size);
		setMinimumSize(Size);
		setResizable(true);
		setLocationRelativeTo(null);
		setFocusable(true);
		setJMenuBar(new UIMenuBar(this));
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
		painel.add(new CarregarProjetoView(this),CarregarProjeto);
	}
	
	public void changeView(String idView){
		layout.show(painel, idView);
	}
	
	public void setTitle(String title){
		super.setTitle((Title + " " + title).trim());
	}
	
	public Projeto getProjeto(){
		return this.projeto;
	}
	
	public void setProjeto(Projeto projeto){
		if(projeto == null){
			this.projeto = null;
			setTitle("");
			changeView(Welcome);
		} else {
			setTitle(" - " + projeto.getNome());
			this.projeto = projeto;
		}
		this.projeto = projeto;
	}
	
	public File getProjetoArquivo() {
		return projetoArquivo;
	}

	public void setProjetoArquivo(File projetoArquivo) {
		this.projetoArquivo = projetoArquivo;
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Window app = new Window();
				app.run();
			}
	    });
	}
}
