package br.com.hrdev.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import br.com.hrdev.Window;
import br.com.hrdev.components.MenuBar;
import br.com.hrdev.events.CloseEvent;
import br.com.hrdev.utils.Icons;

@SuppressWarnings("serial")
public class DashboardView extends JPanel {

	private Window window;
	
	private JTree tree;
	
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
		setLayout(new BorderLayout(10,0));
		
		/* Panels */
		JPanel west = new JPanel(new BorderLayout(0,5));
		JPanel center = new JPanel(new BorderLayout(0,0));
		
		center.setBackground(Color.white);
		center.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		/* JTree */
		tree = new JTree(getDataTree());
		tree.setBorder(new EmptyBorder(4, 4, 4, 4));
		
		/* JTree scrollPane */
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray));
		scrollPane.setBackground(Color.white);
		
		west.add(scrollPane,BorderLayout.CENTER);
		
		/* JTree Options */
		JPanel jTreeOptions = new JPanel(new BorderLayout(5,5));

		jTreeOptions.add(new JButton(Icons.Add),BorderLayout.WEST);
		jTreeOptions.add(new JButton(Icons.Edit),BorderLayout.CENTER);
		jTreeOptions.add(new JButton(Icons.Delete),BorderLayout.EAST);
		west.add(jTreeOptions,BorderLayout.NORTH);
		
		/* Draw Area */
		JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		toolbar.setFloatable(false);
		toolbar.add(new JButton(Icons.Edit));
		toolbar.add(new JButton(Icons.Accept));
		toolbar.add(new JButton(Icons.Add));
		toolbar.add(new JButton(Icons.Delete));
		toolbar.addSeparator();
		toolbar.add(new JButton(Icons.Edit));
		toolbar.add(new JButton(Icons.Accept));
		toolbar.add(new JButton(Icons.Add));
		toolbar.add(new JButton(Icons.Delete));
		
		center.add(toolbar,BorderLayout.NORTH);
		
		add(west,BorderLayout.WEST);
		add(center,BorderLayout.CENTER);
		
	}
	
	private DefaultMutableTreeNode getDataTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Meu Caso de Uso");
		
		DefaultMutableTreeNode node, child;
		
		for (int i = 0; i < 15; i++) {
			node = new DefaultMutableTreeNode("Teste " + i);
			for (int j = 0; j < 15; j++) {
				child = new DefaultMutableTreeNode("Teste " + i + "/" + j);
				node.add(child);
			}
			
			root.add(node);
		}

		return root;
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
