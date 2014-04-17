package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import br.com.hrdev.ucdiagram.Window;
import br.com.hrdev.ucdiagram.components.UIMenuBar;
import br.com.hrdev.ucdiagram.events.CloseEvent;
import br.com.hrdev.ucdiagram.libraries.FileBrowser;
import br.com.hrdev.ucdiagram.libraries.FileManager;
import br.com.hrdev.ucdiagram.models.Projeto;

@SuppressWarnings("serial")
public class CarregarProjetoView extends JPanel {
	
	private Window window;
	private FileBrowser chooser;
	
	private static final String CANCEL_OPTION = "CancelSelection";
	private static final String APPROVE_OPTION = "ApproveSelection";
	
	public CarregarProjetoView(Window window){
		this.window = window;
		this.setup();
	}
	
	public void setup(){
		setLayout(new BorderLayout(10,10));
		setChooser();
		setUpdatePanel();
	}
	
	private void setChooser() {
		chooser = new FileBrowser();
		chooser.addActionListener(new FileChooserAction());
		add(chooser);
	}
	
	private void setMenu() {
		UIMenuBar menubar = (UIMenuBar) window.getJMenuBar();
		menubar.removeAll();
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem("Sair");  
		itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		itemSair.setMnemonic(KeyEvent.VK_Q);
		itemSair.addActionListener(new CloseEvent());

        menuArquivo.add(itemSair);
        
        menubar.add(menuArquivo);
		
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
	
	private void carregarProjeto() {
		FileManager fm = new FileManager();
		File file = chooser.getSelectedFile();
		if(file != null && fm.checkFile(file)){
			Object objeto = fm.load(file);
			if(objeto instanceof Projeto){
				Projeto projeto = (Projeto) objeto;
				window.setProjeto(projeto);
				window.setProjetoArquivo(file);
				window.changeView(Window.Dashboard);
				
				reset();
			} else {
				JOptionPane.showMessageDialog(this, "Arquivo não é um projeto");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Arquivo inválido ou inacessível");
		}
	}
	
	private void reset(){
		setChooser();
	}
	
	private class FileChooserAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(CANCEL_OPTION)){
				if(window.getProjeto() == null){
					window.changeView(Window.Welcome);
				} else {
					window.changeView(Window.Dashboard);
				}
			}
			if (e.getActionCommand().equals(APPROVE_OPTION)){
				carregarProjeto();
			}
		}
	}
}