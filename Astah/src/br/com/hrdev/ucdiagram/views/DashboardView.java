package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import br.com.hrdev.ucdiagram.Window;
import br.com.hrdev.ucdiagram.components.UIMenuBar;
import br.com.hrdev.ucdiagram.components.UIPaintPanel;
import br.com.hrdev.ucdiagram.components.UIToolBarButton;
import br.com.hrdev.ucdiagram.components.UITree;
import br.com.hrdev.ucdiagram.components.UITreeCellRenderer;
import br.com.hrdev.ucdiagram.events.CloseEvent;
import br.com.hrdev.ucdiagram.events.NovoProjetoEvent;
import br.com.hrdev.ucdiagram.events.SalvarProjetoEvent;
import br.com.hrdev.ucdiagram.models.Ator;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.utils.Icons;

@SuppressWarnings("serial")
public class DashboardView extends JPanel {

	private Window window;
	
	private Dimension sidebarSize = new Dimension(180, this.getHeight());
	
	private UITree tree;

	private UIPaintPanel paintPanel;
	
	public DashboardView(Window window){
		this.window = window;
		this.setup();
	}

	private void setup() {
		setUpdatePanel();
		setPanels();
	}

	private void setMenu() {
		UIMenuBar menubar = (UIMenuBar) window.getJMenuBar();
		menubar.removeAll();
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setMnemonic('A');
		
		/* Item Novo Projeto */
		JMenuItem itemNovo = new JMenuItem("Novo Projeto");  
		itemNovo.addActionListener(new NovoProjetoEvent(window,this));
		
		/* Item Salvar Projeto */
		JMenuItem itemSalvar = new JMenuItem("Salvar Projeto");  
		itemSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.addActionListener(new SalvarProjetoEvent(window));
		
		/* Item Exportar em PNG */
		JMenuItem itemExportar = new JMenuItem("Exportar em PNG");  
		
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem("Sair");  
		itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		itemSair.setMnemonic(KeyEvent.VK_Q);
		itemSair.addActionListener(new CloseEvent());

        menuArquivo.add(itemNovo);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemExportar);
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
		
		/* JTree scrollPane */
		tree = new UITree(window);
		tree.setBorder(new EmptyBorder(4, 4, 4, 4));
		tree.setCellRenderer(new UITreeCellRenderer());
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray));
		scrollPane.setBackground(Color.white);
		scrollPane.setPreferredSize(sidebarSize);
		
		west.add(scrollPane,BorderLayout.CENTER);
		
		/* JTree Options */
		JPanel jTreeOptions = new JPanel(new BorderLayout(5,5));
		
		jTreeOptions.add(new JButton(Icons.Add),BorderLayout.WEST);
		jTreeOptions.add(new JButton(Icons.Edit),BorderLayout.CENTER);
		jTreeOptions.add(new JButton(Icons.Delete),BorderLayout.EAST);
		west.add(jTreeOptions,BorderLayout.NORTH);
		
		/* Draw Area */
		JToolBar toolbar = new JToolBar(SwingConstants.HORIZONTAL);
		toolbar.setFloatable(false);
		setToolbar(toolbar);
		center.add(toolbar,BorderLayout.NORTH);
		
		paintPanel = new UIPaintPanel();
		center.add(paintPanel,BorderLayout.CENTER);
		
		add(west,BorderLayout.WEST);
		add(center,BorderLayout.CENTER);
		
	}
	
	private void setToolbar(JToolBar toolbar) {
		ImageIcon[] icons = {
				Icons.Edit, Icons.Accept, Icons.Add, Icons.Delete, null,
				Icons.Page, Icons.Page_add, Icons.Page_edit, Icons.Page_delete, null,
				Icons.Folder, Icons.Folder_add, Icons.Folder_edit, Icons.Folder_delete
		};
		String[] tooltips = {
				"Editar", "Aceitar", "Adicinar", "Deletar", null,
				"P\u00e1gina", "Adicionar P\u00e1gina", "Editar P\u00e1gina", "Remover P\u00e1gina",null,
				"Pasta", "Adicionar Pasta", "Editar Pasta", "Remover Pasta"
		};
		
		for (int i = 0; i < icons.length; i++) {
			if(icons[i] == null){
				toolbar.addSeparator();
			} else {
				UIToolBarButton button = new UIToolBarButton(icons[i],tooltips[i]);
				toolbar.add(button);
			}
		}
	}

	private void updateDataTree() {
		Projeto projeto = window.getProjeto();
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(projeto.getNome());

		DefaultMutableTreeNode diagramas = new DefaultMutableTreeNode("Diagramas");
		for (Diagrama diagrama : projeto.getDiagramas()) {
			diagramas.add(new DefaultMutableTreeNode(diagrama));
		}
		
		
		DefaultMutableTreeNode atores = new DefaultMutableTreeNode("Atores");
		for (Ator ator : projeto.getAtores()) {
			atores.add(new DefaultMutableTreeNode(ator));
		}

		rootNode.add(diagramas);
		rootNode.add(atores);
		
		tree.updateAll(rootNode);
		
	}

	private void setUpdatePanel(){
		addComponentListener (new ComponentAdapter(){
	        @Override
			public void componentShown(ComponentEvent e){
	        	updateDataTree();
	        	setMenu();
	        }

	        @Override
			public void componentHidden(ComponentEvent e){}
	    });
	}

	public void updateAll() {
		updateDataTree();
		
	}
}
