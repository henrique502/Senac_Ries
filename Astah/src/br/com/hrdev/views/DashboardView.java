package br.com.hrdev.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;

import br.com.hrdev.Window;
import br.com.hrdev.components.MenuBar;
import br.com.hrdev.events.CloseEvent;

@SuppressWarnings("serial")
public class DashboardView extends JPanel {

	private Window window;
	
	private JTree tree;
	private JPanel drawPanel;
	
	public DashboardView(Window window){
		this.window = window;
		this.setup();
	}

	private void setup() {
		setUpdatePanel();
		setPanels();
	}

	private void setMenu() {
		MenuBar menubar = (MenuBar) window.getJMenuBar();
		menubar.removeAll();
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');
		
		/* Item Novo */
		JMenuItem itemNovo = new JMenuItem("Novo");  
		itemNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		itemNovo.setMnemonic(KeyEvent.VK_N);
		
		/* Item Editar */
		JMenuItem itemEditar = new JMenuItem("Editar");  
		itemEditar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		itemEditar.setMnemonic(KeyEvent.VK_E);
		
		/* Item Remover */
		JMenuItem itemRemover = new JMenuItem("Remover");  
		itemRemover.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		itemRemover.setMnemonic(KeyEvent.VK_R);
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem("Sair");  
		itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		itemSair.setMnemonic(KeyEvent.VK_Q);
		itemSair.addActionListener(new CloseEvent());
		
		
        itemEditar.setEnabled(false);
        itemRemover.setEnabled(false);

        menuArquivo.add(itemNovo);
        menuArquivo.add(itemEditar);
        menuArquivo.add(itemRemover);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);
        
        menubar.add(menuArquivo);
		
        menubar.setHelp();
		menubar.revalidate();
	}
	
	private void setPanels(){
		setLayout(new BorderLayout(10,10));
		
		JPanel west = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Projeto");
		tree = new JTree(root);
		
		west.setBackground(Color.white);
		west.add(tree);
		west.setPreferredSize(new Dimension(170, 700));
		
		drawPanel = new JPanel(new BorderLayout(10,10));
		drawPanel.setBackground(Color.gray);
		
		
		
		
		add(west,BorderLayout.WEST);
		add(drawPanel,BorderLayout.CENTER);
		
	}
	
	private void setUpdatePanel(){
		addComponentListener (new ComponentAdapter(){
	        public void componentShown(ComponentEvent e){
	        	setMenu();
	        }

	        public void componentHidden(ComponentEvent e){}
	    });
	}
}
