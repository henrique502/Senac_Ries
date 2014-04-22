package br.com.hrdev.ucdiagram.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.components.UIMenuBar;
import br.com.hrdev.ucdiagram.components.UIToolBarButton;
import br.com.hrdev.ucdiagram.components.UITree;
import br.com.hrdev.ucdiagram.components.UITreeCellRenderer;
import br.com.hrdev.ucdiagram.events.AdicionarDiagramaEvent;
import br.com.hrdev.ucdiagram.events.CloseEvent;
import br.com.hrdev.ucdiagram.events.NovoProjetoEvent;
import br.com.hrdev.ucdiagram.events.SalvarProjetoEvent;
import br.com.hrdev.ucdiagram.models.Ator;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.utils.Icons;

@SuppressWarnings("serial")
public class DashboardView extends JPanel {

	private UCDiagram window;
	
	private Dimension sidebarSize = new Dimension(180, this.getHeight());
	
	private UITree tree;

	private JPanel diagramArea;
	private Diagrama currentDiagram = null;
	private ArrayList<UIToolBarButton> toolbarButtons;

	public ButtonGroup buttonGroup;
	
	public DashboardView(UCDiagram window){
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
		
		/* Item Carregar Projeto */
		JMenuItem itemCarregar = new JMenuItem("Carregar Projeto");  
		itemCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.changeView(UCDiagram.CarregarProjeto);
			}
		});
		
		/* Item Salvar */
		JMenuItem itemSalvar = new JMenuItem("Salvar");  
		itemSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		itemSalvar.setMnemonic(KeyEvent.VK_S);
		itemSalvar.addActionListener(new SalvarProjetoEvent(window,false));
		
		/* Item Salvar Como */
		JMenuItem itemSalvarComo = new JMenuItem("Salvar Como");  
		itemSalvarComo.addActionListener(new SalvarProjetoEvent(window,true));
		
		/* Item Exportar em PNG */
		JMenuItem itemExportar = new JMenuItem("Exportar em PNG");  
		
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem("Sair");  
		itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		itemSair.setMnemonic(KeyEvent.VK_Q);
		itemSair.addActionListener(new CloseEvent());

        menuArquivo.add(itemNovo);
        menuArquivo.add(itemCarregar);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSalvarComo);

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
		tree = new UITree(this);
		tree.setBorder(new EmptyBorder(4, 4, 4, 4));
		tree.setCellRenderer(new UITreeCellRenderer());
		
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray));
		scrollPane.setBackground(Color.white);
		scrollPane.setPreferredSize(sidebarSize);
		
		west.add(scrollPane,BorderLayout.CENTER);
		
		
		JButton addDiagram = new JButton(Icons.Add);
		addDiagram.setText("Novo Diagrama");
		addDiagram.addActionListener(new AdicionarDiagramaEvent(this));
		west.add(addDiagram,BorderLayout.NORTH);
		
		/* Draw Area */
		JToolBar toolbar = new JToolBar(SwingConstants.HORIZONTAL);
		toolbar.setFloatable(false);
		setToolbar(toolbar);
		center.add(toolbar,BorderLayout.NORTH);
		
		diagramArea = new JPanel(new CardLayout());
		diagramArea.setBackground(Color.gray);
		diagramArea.addMouseListener(new AddDiagramItem());
		diagramArea.setBorder(new EmptyBorder(15,15,15,15));
		JScrollPane diagramScrollPane = new JScrollPane(diagramArea);
		diagramScrollPane.setBorder(new EmptyBorder(0,0,0,0));

		center.add(diagramScrollPane,BorderLayout.CENTER);
		
		add(west,BorderLayout.WEST);
		add(center,BorderLayout.CENTER);
		
	}
	
	private void setToolbar(JToolBar toolbar) {
		buttonGroup = new ButtonGroup();
		toolbarButtons = new ArrayList<UIToolBarButton>();

		UIToolBarButton button = new UIToolBarButton(Icons.Ator,"Criar novo ator",UIToolBarButton.Ator);
		buttonGroup.add(button);
		toolbarButtons.add(button);
		toolbar.add(button);
		



	}

	private void updateDataTree() {
		Projeto projeto = window.getProjeto();
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(projeto);

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
		tree.expandRow(0);
	}
	
	private void updateDiagramAreaData(){
		diagramArea.removeAll();
		currentDiagram = null;
		
		for(Diagrama d : window.getProjeto().getDiagramas()){
			if(currentDiagram == null)
				currentDiagram = d;
			
			diagramArea.add(d,d.getNome());
		}
		updateDiagramArea();
	}
	
	public void updateDiagramArea(){
		SwingUtilities.invokeLater(new Runnable(){
		    public void run() {
		    	diagramArea.revalidate();
		    }
		});
	}

	private void setUpdatePanel(){
		addComponentListener (new ComponentAdapter(){
	        @Override
			public void componentShown(ComponentEvent e){
	        	setMenu();
	        	updateAll();
	        }

	        @Override
			public void componentHidden(ComponentEvent e){}
	    });
	}

	public void updateAll() {
		updateDataTree();
		updateDiagramAreaData();
	}
	
	public UCDiagram getWindow(){
		return this.window;
	}
	
	public void showDiagram(Diagrama diagrama) {
		if(diagrama == null) return;
		currentDiagram = diagrama;
		CardLayout card = (CardLayout) diagramArea.getLayout();
		card.show(diagramArea, currentDiagram.getNome());
		updateDiagramArea();
	}
	
	
	/* TODO: Mudar geito ta Ator.java, melhor resposta da area de desenho */
	/* Inner Class */
	
	private class AddDiagramItem implements MouseListener {

		private void addItem(Point point){
			if(currentDiagram != null){
				for(UIToolBarButton button : toolbarButtons){
					if(button.isSelected()){
						if(button.getTipo() == UIToolBarButton.Ator){
							Ator ator = new Ator("Ator " + window.getProjeto().getAtores().size());

							ator.setPoint(point);
							currentDiagram.add(ator);

							window.getProjeto().getAtores().add(ator);
							updateDataTree();
							updateDiagramArea();
						}
						buttonGroup.clearSelection();
					}
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) { addItem(e.getPoint()); }

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	
}
